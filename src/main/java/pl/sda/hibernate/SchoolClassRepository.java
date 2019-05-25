package pl.sda.hibernate;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import pl.sda.hibernate.model.*;

public class SchoolClassRepository {

  private EntityManager entityManager;

  public SchoolClassRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<Test> getAllTestsBySchoolClassId(long id) {
    return entityManager.find(SchoolClass.class, id).getTests();
  }

  public List<VerbalTest> getAllVerbalTestsBySchoolClassId(long id) {
   throw new UnsupportedOperationException("Not yet implemented");
  }

  public <T extends Test> List<T> getTestsByType(Class<T> type) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
