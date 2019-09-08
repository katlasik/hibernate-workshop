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
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public Student findStudentByIdLazily(Long id) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public Student createStudent(Student student) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public void updateStudent(Student student) {
    throw new UnsupportedOperationException("Not yet implemented");

  }

  public void deleteStudent(Student student) {
    throw new UnsupportedOperationException("Not yet implemented");

  }

  public void refreshStudent(Student student) {
    throw new UnsupportedOperationException("Not yet implemented");

  }

  public List<Student> getStudents() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public long getStudentsCount() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public Optional<Student> getStudentByName(String name) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<Student> findStudentsByName(String name) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<SchoolClass> getClassesByStudentId(long id) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<Student> getAllFriendStudentsByStudentId(long id) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
