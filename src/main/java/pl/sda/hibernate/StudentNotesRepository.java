package pl.sda.hibernate;

import java.util.List;
import javax.persistence.EntityManager;
import pl.sda.hibernate.model.StudentNote;
import pl.sda.hibernate.model.dto.NoteWithClassName;

public class StudentNotesRepository {

  private EntityManager entityManager;

  public StudentNotesRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public Double getAverageBySchoolClass(long id) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<StudentNote> getNotesByStudentId(long id) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<NoteWithClassName> getNotesWithClassNameByStudentId(long id) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

}
