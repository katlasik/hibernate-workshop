package pl.sda.hibernate;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import pl.sda.hibernate.model.VerbalTest;
import pl.sda.hibernate.model.WrittenTest;
import pl.sda.hibernate.utils.HibernateBootstraper;
import pl.sda.test.base.DatabaseSetup;

class SchoolClassRepositoryTest {

  @RegisterExtension static DatabaseSetup db = new DatabaseSetup();

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
    assertThat(schoolClassRepository.getAllVerbalTests(1L))
        .containsExactlyInAnyOrder(
            new VerbalTest(1L, "Ułamki", LocalDate.parse("2019-02-03"), false),
            new VerbalTest(2L, "Funkcje liniowe", LocalDate.parse("2019-02-03"), false));
  }

  @Test
  void testGetAllWrittenTests() {
    assertThat(schoolClassRepository.getAllWrittenTests(1L))
        .containsExactlyInAnyOrder(
            new WrittenTest(6L, "Funkcje kwadratowe", LocalDate.parse("2019-02-03"), 60L));
  }
}
