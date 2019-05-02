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

  public List<StudentNote> getNotesByStudentId(long id) {
    return entityManager
        .createQuery(
            "select sn from Student s JOIN StudentNote sn ON sn.student = s WHERE s.id = :id ",
            StudentNote.class)
        .setParameter("id", id)
        .getResultList();
  }

  public List<NoteWithClassName> getNotesWithClassNameByStudentId(long id) {
    return entityManager
        .createQuery(
            "select new pl.sda.hibernate.model.dto.NoteWithClassName(sn.value, sc.name) from StudentNote sn join SchoolClass sc on sn.schoolClass = sc where sn.student.id = :id ",
            NoteWithClassName.class)
        .setParameter("id", id)
        .getResultList();
  }

  public Double getAverageBySchoolClass(long id) {
    return entityManager
        .createQuery(
            "select avg(sn.value) from StudentNote sn where sn.schoolClass.id = :id", Double.class)
        .setParameter("id", id)
        .getSingleResult();
  }
}
