package pl.sda.hibernate;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.sda.hibernate.model.*;
import pl.sda.hibernate.utils.HibernateBootstraper;
import pl.sda.test.base.DatabaseSetupTest;

class SchoolClassRepositoryTest {

  @RegisterExtension static DatabaseSetupTest db = new DatabaseSetupTest();

  private EntityManagerFactory factory = HibernateBootstraper.createEntityManagerFactory();
  private EntityManager entityManager = factory.createEntityManager();

  private SchoolClassRepository schoolClassRepository = new SchoolClassRepository(entityManager);

  @Test
  void testGetAllTestsBySchoolClassId() {
    assertThat(schoolClassRepository.getAllTestsBySchoolClassId(1L))
        .containsExactlyInAnyOrder(
            new WrittenTest(6L, "Funkcje kwadratowe", LocalDate.parse("2019-02-03"), 60L),
            new VerbalTest(1L, "Ułamki", LocalDate.parse("2019-02-03"), false),
            new VerbalTest(2L, "Funkcje liniowe", LocalDate.parse("2019-02-03"), false));
  }

  @Test
  void testGetAllVerbalTests() {
    assertThat(schoolClassRepository.getAllVerbalTestsBySchoolClassId(1L))
        .containsExactlyInAnyOrder(
            new VerbalTest(1L, "Ułamki", LocalDate.parse("2019-02-03"), false),
            new VerbalTest(2L, "Funkcje liniowe", LocalDate.parse("2019-02-03"), false));
  }

  @Test
  void testGetTestsByType() {
    assertThat(schoolClassRepository.getTestsByType(WrittenTest.class))
        .containsExactlyInAnyOrder(
            new WrittenTest(6L, "Funkcje kwadratowe", LocalDate.parse("2019-02-03"), 60L),
            new WrittenTest(7L, "Mechanika", LocalDate.parse("2019-03-11"), 60L),
            new WrittenTest(8L, "Chemia organiczna", LocalDate.parse("2019-12-03"), 120L));

    assertThat(schoolClassRepository.getTestsByType(VerbalTest.class))
        .containsExactlyInAnyOrder(
            new VerbalTest(1L, "Ułamki", LocalDate.parse("2019-02-03"), false),
            new VerbalTest(2L, "Funkcje liniowe", LocalDate.parse("2019-02-03"), false),
            new VerbalTest(3L, "Prezentacja o polimerach", LocalDate.parse("2019-03-11"), true),
            new VerbalTest(4L, "Ruch liniowy", LocalDate.parse("2019-12-03"), false),
            new VerbalTest(5L, "Prezentacja z optyki", LocalDate.parse("2019-02-03"), true));
  }

  @Test
  void getSchoolClassByNameTest() {
    assertThat(schoolClassRepository.getSchoolClassByName("Matematyka"))
        .contains(new SchoolClass(1L, "Matematyka"));
  }

  @Test
  void getSchoolClassByNameTestFailure() {
    assertThat(schoolClassRepository.getSchoolClassByName("Rosyjski")).isEmpty();
  }

  @Test
  void testAddTopic() {

    entityManager.getTransaction().begin();

    entityManager.find(SchoolClass.class, 1L).addLessonTopic("Dzielenie");

    entityManager.getTransaction().commit();

    assertThat(
            new JdbcTemplate(db.getDatasource())
                .queryForObject(
                    "SELECT topic FROM LessonTopics WHERE topic = ?", String.class, "Dzielenie"))
        .isEqualTo("Dzielenie");
  }

  @Test
  void testGetTopics() {

    assertThat(schoolClassRepository.getTopics(Arrays.asList(1L, 2L, 3L)))
        .containsExactlyInAnyOrder(
            "Ułamki", "Funkcja kwadratowa", "Optyka", "Mechanika", "Estry", "Kwasy");
  }

  @Test
  void testGetStudentsBySchoolClassName() {

    assertThat(schoolClassRepository.getStudentsBySchoolClassName("Chemia"))
        .contains(
            new Student(4L, "Błażej", "Rudnicki", LocalDate.parse("1998-12-03")),
            new Student(3L, "Krystyna", "Kowal", LocalDate.parse("1996-03-11")));
  }

  @Test
  void testGetStudentsNotesBySchoolClassId() {

    assertThat(
            schoolClassRepository
                .getStudentsNotesBySchoolClassId(
                    1L, LocalDate.parse("2019-02-01"), LocalDate.parse("2019-02-10"))
                .stream()
                .map(StudentNote::getId))
        .containsOnly(1L);
  }
}
