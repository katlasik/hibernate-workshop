package pl.sda.hibernate;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pl.sda.hibernate.model.Teacher;

public class TeachersRepository {

  private EntityManager entityManager;

  public TeachersRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<String> getTeachersNames() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<Teacher> getTeachersPaging(int page, int pageSize) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
