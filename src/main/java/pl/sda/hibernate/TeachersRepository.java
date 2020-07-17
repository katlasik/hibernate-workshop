package pl.sda.hibernate;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import pl.sda.hibernate.model.Teacher;

public class TeachersRepository {

  private EntityManager entityManager;

  public TeachersRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<String> getTeachersNames() {
    TypedQuery<String> query =
        entityManager.createQuery(
            "select concat(t.fullName.firstName, ' ', t.fullName.lastName) from Teacher t",
            String.class);
    return query.getResultList();
  }

  public List<Teacher> getTeachersPaging(int page, int pageSize) {
    TypedQuery<Teacher> query =
        entityManager.createQuery("from Teacher order by lastName, firstName", Teacher.class);
    query.setFirstResult(pageSize * page);
    query.setMaxResults(pageSize);
    return query.getResultList();
  }

  public String getTeacherInitials() {
    Query query =
        entityManager.createNativeQuery(
            "SELECT GROUP_CONCAT(CONCAT(SUBSTRING(firstName, 1, 1), '.', SUBSTRING(lastName, 1, 1), '.')) FROM Teacher");
    return (String) query.getSingleResult();
  }
}
