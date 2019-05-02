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

  public List<VerbalTest> getAllVerbalTests(long id) {
    return entityManager
        .createQuery(
            "select t from SchoolClass sc join sc.tests t where t.class = VerbalTest and sc.id = :id",
            Test.class)
        .setParameter("id", id)
        .getResultStream()
        .map(s -> (VerbalTest) s)
        .collect(Collectors.toList());
  }

  public List<WrittenTest> getAllWrittenTests(long id) {
    return entityManager
        .createQuery(
            "select t from SchoolClass sc join sc.tests t where type(t) = WrittenTest and sc.id = :id",
            Test.class)
        .setParameter("id", id)
        .getResultStream()
        .map(s -> (WrittenTest) s)
        .collect(Collectors.toList());
  }
}
