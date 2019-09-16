package pl.sda.hibernate;

import static java.util.Arrays.*;
import static java.util.Collections.*;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.sda.hibernate.model.SchoolClass;
import pl.sda.hibernate.model.Student;
import pl.sda.hibernate.utils.HibernateBootstraper;
import pl.sda.test.base.DatabaseSetup;

public class StudentsRepositoryTest {

  @RegisterExtension static DatabaseSetup db = new DatabaseSetup();

  private EntityManagerFactory factory = HibernateBootstraper.createEntityManagerFactory();
  private EntityManager entityManager = factory.createEntityManager();

  private StudentsRepository studentsRepository = new StudentsRepository(entityManager);

  @Test
  @DisplayName("When correct id is passed, student entry should be returned")
  void testFindStudentById() {
    Optional<Student> result = studentsRepository.findStudentById(1L);

    assertThat(result)
        .contains(new Student(1L, "Szymon", "Kowalski", LocalDate.parse("1999-02-03")));
  }

  @Test
  @DisplayName("When incorrect id is passed, no student entry should be returned")
  void testFindStudentByIdWitIncorrectId() {
    Optional<Student> result = studentsRepository.findStudentById(10L);
    assertThat(result).isEmpty();
  }

  @Test
  @DisplayName("When fetched lazily, database request should be done only if necessary")
  void testFindStudentByIdLazily() throws NoSuchFieldException, IllegalAccessException {
    Field field = Student.class.getDeclaredField("firstName");
    field.setAccessible(true);

    Student reference = studentsRepository.findStudentByIdLazily(1L);

    assertThat(reference.getId()).isEqualTo(1L);
    assertThat(field.get(reference)).isNull();

    // Thread.sleep(5000);

    assertThat(reference.getFirstName()).isEqualTo("Szymon");

    Student reference2 = studentsRepository.findStudentByIdLazily(1L);

    assertThat(reference2.getFirstName()).isEqualTo("Szymon");
  }

  @Test
  @DisplayName("When property is lazy, should do database request only if necessary")
  void testFindStudentByIdLazilyWithInorrectId()
      throws NoSuchFieldException, IllegalAccessException {

    Student reference = studentsRepository.findStudentByIdLazily(99L);

    assertThat(reference.getId()).isEqualTo(99L);

    assertThrows(EntityNotFoundException.class, reference::getFirstName);
  }

  @Test
  @DisplayName("When correct student object is passed, then entry should be created")
  void testCreateStudent() {
    Student result =
        studentsRepository.createStudent(
            new Student(null, "Henryk", "Nowakowski", LocalDate.parse("2000-02-03")));
    Optional<Student> persisted = studentsRepository.findStudentById(result.getId());

    Optional<String> firstName =
        persisted.map(
            p ->
                new JdbcTemplate(db.getDatasource())
                    .queryForObject(
                        "SELECT firstName FROM Student WHERE id = ?", String.class, p.getId()));

    assertThat(persisted).contains(result);

    assertThat(persisted.map(Student::getFirstName)).isEqualTo(firstName);
  }

  @Test
  @DisplayName("When  student object is passed with id, then exception should be thrown")
  void testCreateStudentWithId() {

    assertThrows(
        PersistenceException.class,
        () ->
            studentsRepository.createStudent(
                new Student(10L, "Henryk", "Nowakowski", LocalDate.parse("2000-02-03"))));
  }

  @Test
  @DisplayName("When correct student object is passed, then entry should be updated")
  void testUpdateStudent() {
    Student student = new Student(4L, "Szymon", "Urbanski", LocalDate.parse("2000-11-11"));

    studentsRepository.updateStudent(student);

    String firstName =
        new JdbcTemplate(db.getDatasource())
            .queryForObject("SELECT firstName FROM Student WHERE id = 4", String.class);

    Optional<Student> persisted = studentsRepository.findStudentById(4L);

    assertThat(persisted).contains(student);
    assertThat(firstName).isEqualTo(student.getFirstName());
  }

  @Test
  @DisplayName("When correct id is passed, entry should be deleted")
  void testDeleteStudent() {
    Student student = studentsRepository.findStudentById(4L).get();
    studentsRepository.deleteStudent(student);

    int count =
        new JdbcTemplate(db.getDatasource())
            .queryForList("SELECT * FROM Student WHERE id = 4")
            .size();

    assertThat(studentsRepository.findStudentById(4L)).isEmpty();

    assertThat(count).isEqualTo(0);
  }

  @Test
  @DisplayName("Student object should be refreshed")
  void testRefreshStudent() {

    Student student = studentsRepository.findStudentById(1L).get();

    new JdbcTemplate(db.getDatasource())
        .execute("UPDATE Student SET firstName = 'Stefan' WHERE id = 1");

    studentsRepository.refreshStudent(student);

    assertThat(student.getFirstName()).isEqualTo("Stefan");
  }

  @Test
  @DisplayName("All student entries should be returned")
  void testGetStudents() {
    List<Student> result = studentsRepository.getStudents();
    assertThat(result)
        .containsExactlyInAnyOrder(
            new Student(1L, "Szymon", "Kowalski", LocalDate.parse("1999-02-03")),
            new Student(2L, "Krystian", "Nowak", LocalDate.parse("1999-02-03")),
            new Student(3L, "Krystyna", "Kowal", LocalDate.parse("1996-03-11")),
            new Student(4L, "Błażej", "Rudnicki", LocalDate.parse("1998-12-03")));
  }

  @Test
  @DisplayName("All students should be counted")
  void testGetStudentsCount() {
    long result = studentsRepository.getStudentsCount();
    assertThat(result).isEqualTo(4);
  }

  @Test
  @DisplayName("When correct name is passed, correct result should be returned")
  void testFindStudentByName() {
    List<Student> startingWithKry = studentsRepository.findStudentsByName("kRy");

    assertThat(startingWithKry)
        .containsExactlyInAnyOrder(
            new Student(2L, "Krystian", "Nowak", LocalDate.parse("1999-02-03")),
            new Student(3L, "Krystyna", "Kowal", LocalDate.parse("1996-03-11")));

    List<Student> startingWithKow = studentsRepository.findStudentsByName("kow");

    assertThat(startingWithKow)
        .containsExactlyInAnyOrder(
            new Student(1L, "Szymon", "Kowalski", LocalDate.parse("1999-02-03")),
            new Student(3L, "Krystyna", "Kowal", LocalDate.parse("1996-03-11")));
  }

  @Test
  @DisplayName("All student entries should be returned")
  void testFindStudentByLastName() {
    Optional<Student> student = studentsRepository.getStudentByName("Rudnicki");
    assertThat(student)
        .contains(new Student(4L, "Błażej", "Rudnicki", LocalDate.parse("1998-12-03")));
  }

  @Test
  @DisplayName("All student entries should be returned")
  void testFindStudentByLastNameFailure() {
    Optional<Student> student = studentsRepository.getStudentByName("Campanella");
    assertThat(student).isEmpty();
  }

  @Test
  @DisplayName("All student classes should be fetched")
  void testGetClassesByStudentId() {
    List<SchoolClass> result = studentsRepository.getClassesByStudentId(1L);

    assertThat(result)
        .containsExactlyInAnyOrder(
            new SchoolClass(1L, "Matematyka"), new SchoolClass(2L, "Fizyka"));
  }

  @Test
  @DisplayName("Get all students which study in same classes")
  void testGetAllFriendStudents() {
    List<Student> students = studentsRepository.getAllFriendStudentsByStudentId(3L);
    entityManager.close();

    assertThat(students.size()).isEqualTo(3);

    Map<String, List<SchoolClass>> classes =
        students.stream().collect(toMap(Student::getFirstName, Student::getSchoolClasses));

    assertThat(classes.get("Błażej"))
        .hasSameElementsAs(singletonList(new SchoolClass(3L, "Chemia")));

    assertThat(classes.get("Szymon"))
        .hasSameElementsAs(
            asList(new SchoolClass(1L, "Matematyka"), new SchoolClass(2L, "Fizyka")));

    assertThat(classes.get("Krystian"))
        .hasSameElementsAs(singletonList(new SchoolClass(2L, "Fizyka")));
  }
}
