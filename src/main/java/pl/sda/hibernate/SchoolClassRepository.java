package pl.sda.hibernate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import pl.sda.hibernate.model.*;

public class SchoolClassRepository {

  private EntityManager entityManager;

  public SchoolClassRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<Test> getAllTestsBySchoolClassId(long id) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<VerbalTest> getAllVerbalTestsBySchoolClassId(long id) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public <T extends Test> List<T> getTestsByType(Class<T> type) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public Optional<SchoolClass> getSchoolClassByName(String name) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<String> getTopics(List<Long> ids) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<Student> getStudentsBySchoolClassName(String name) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<StudentNote> getStudentsNotesBySchoolClassId(
      Long schoolClassId, LocalDate from, LocalDate to) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
