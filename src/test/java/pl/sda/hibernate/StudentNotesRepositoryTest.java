package pl.sda.hibernate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import pl.sda.hibernate.model.SchoolClass;
import pl.sda.hibernate.model.Student;
import pl.sda.hibernate.model.StudentNote;
import pl.sda.hibernate.model.dto.NoteWithClassName;
import pl.sda.hibernate.utils.HibernateBootstraper;
import pl.sda.test.base.DatabaseSetup;

public class StudentNotesRepositoryTest {

  @RegisterExtension static DatabaseSetup db = new DatabaseSetup();

  private EntityManagerFactory factory = HibernateBootstraper.createEntityManagerFactory();
  private EntityManager entityManager = factory.createEntityManager();

  private StudentNotesRepository studentNotesRepository = new StudentNotesRepository(entityManager);

  @Test
  @DisplayName("All students notes should be fetched.")
  void testGetNotesByStudentId() {
    List<StudentNote> notes = studentNotesRepository.getNotesByStudentId(3L);
    entityManager.close();

    assertThat(notes.get(0).getValue()).isEqualTo(5);
    assertThat(notes.get(0).getSchoolClass().getName()).isEqualTo("Matematyka");

    assertThat(notes.get(1).getValue()).isEqualTo(3);
    assertThat(notes.get(1).getSchoolClass().getName()).isEqualTo("Chemia");
  }

  @Test
  @DisplayName("Should return right average for school classl.")
  void testGetAverageBySchoolClass() {
    Double average = studentNotesRepository.getAverageBySchoolClass(1L);

    assertThat(average).isEqualTo(4.25);
  }

  @Test
  @DisplayName("NoteWithClassName should be returned.")
  void testGetNotesWithClassNameByStudentId() {

    assertThat(studentNotesRepository.getNotesWithClassNameByStudentId(1L))
        .containsExactlyInAnyOrder(
            new NoteWithClassName(1, "Matematyka"),
            new NoteWithClassName(5, "Chemia"),
            new NoteWithClassName(6, "Matematyka"));
  }

  @Test
  @DisplayName("Student note should be created with createdAt date.")
  void testCreateStudentNote() {
    entityManager.getTransaction().begin();
    StudentNote sn =
        new StudentNote(
            5,
            1,
            entityManager.getReference(SchoolClass.class, 1L),
            entityManager.getReference(Student.class, 1L));
    entityManager.persist(sn);
    entityManager.getTransaction().commit();
    assertThat(entityManager.find(StudentNote.class, sn.getId()).getCreatedAt()).isNotNull();
  }
}
