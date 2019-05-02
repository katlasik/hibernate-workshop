package pl.sda.hibernate;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pl.sda.hibernate.model.SchoolClass;
import pl.sda.hibernate.model.Student;

public class StudentsRepository {

  private EntityManager entityManager;

  public StudentsRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public Optional<Student> findStudentById(Long id) {
    return Optional.ofNullable(entityManager.find(Student.class, id));
  }

  public Student findStudentByIdLazily(Long id) {
    return entityManager.getReference(Student.class, id);
  }

  public Student createStudent(Student student) {
    entityManager.persist(student);
    return student;
  }

  public void updateStudent(Student student) {
    entityManager.merge(student);
  }

  public void deleteStudent(Student student) {
    entityManager.remove(student);
  }

  public void refreshStudent(Student student) {
    entityManager.refresh(student);
  }

  public List<Student> getStudents() {
    return entityManager.createQuery("from Student", Student.class).getResultList();
  }

  public long getStudentsCount() {
    return entityManager
        .createQuery("select count(s) from Student s", Long.class)
        .getSingleResult();
  }

  public List<Student> findStudentsByName(String name) {
    TypedQuery<Student> query =
        entityManager.createQuery(
            "from Student s where lower(s.firstName) like lower(:name) or lower(s.lastName) like lower(:name)",
            Student.class);
    query.setParameter("name", name + "%");
    return query.getResultList();
  }

  public List<SchoolClass> getClassesByStudentId(long id) {
    TypedQuery<SchoolClass> query =
        entityManager.createQuery(
            "select sc from SchoolClass sc join sc.students s where s.id = :id", SchoolClass.class);
    query.setParameter("id", id);
    return query.getResultList();
  }

  public List<Student> getAllFriendStudentsByStudentId(long id) {
    return entityManager
        .createQuery(
            "select distinct st from Student s join s.schoolClasses sc join sc.students st join fetch st.schoolClasses where s.id = :id",
            Student.class)
        .setParameter("id", id)
        .getResultList();
  }
}
