package pl.sda.hibernate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import pl.sda.hibernate.model.SchoolClass;
import pl.sda.hibernate.model.Teacher;
import pl.sda.hibernate.utils.HibernateBootstraper;
import pl.sda.test.base.DatabaseSetup;

public class TeachersRepositoryTest {

  @RegisterExtension static DatabaseSetup db = new DatabaseSetup();

  private EntityManagerFactory factory = HibernateBootstraper.createEntityManagerFactory();

  private EntityManager entityManager = factory.createEntityManager();

  private TeachersRepository teachersRepository = new TeachersRepository(entityManager);

  @Test
  @DisplayName("All name details should be returned")
  void testGetTeachersNameDetails() {
    assertThat(teachersRepository.getTeachersNames())
        .containsExactlyInAnyOrder("Damian Lewandowski", "Beata Woźniak", "Artur Wójcik");
  }

  @Test
  @DisplayName("When pagination is provided, correct results should be returned")
  void testTeachersPaging() {
    System.out.println(teachersRepository.getTeachersPaging(0, 2));
    assertThat(teachersRepository.getTeachersPaging(0, 2))
        .containsExactlyInAnyOrder(
            new Teacher(1L, "Damian", "Lewandowski", Collections.emptySet()),
            new Teacher(3L, "Artur", "Wójcik", Collections.emptySet()));

    assertThat(teachersRepository.getTeachersPaging(1, 2))
        .containsExactlyInAnyOrder(new Teacher(2L, "Beata", "Woźniak", Collections.emptySet()));
  }

  @Test
  @DisplayName("Teacher should be able to be assigned to class.")
  void testAssignToSchoolClass() {

    Teacher teacher = entityManager.find(Teacher.class, 1L);
    SchoolClass schoolClass = entityManager.find(SchoolClass.class, 2L);

    teacher.assignToSchoolClass(schoolClass);

    assertThat(entityManager.find(Teacher.class, 1L).getSchoolClasses()).contains(schoolClass);
    assertThat(entityManager.find(SchoolClass.class, 2L).getTeacher()).isEqualTo(teacher);
  }
}
