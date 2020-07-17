package pl.sda.hibernate;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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
        entityManager.getTransaction().begin();
        System.out.println(student.getId());
        entityManager.persist(student);
        System.out.println(student.getId());
        entityManager.getTransaction().commit();
        return student;
    }

    public void updateStudent(Student student) {
        entityManager.getTransaction().begin();
        System.out.println(student.getId());
        student.setId(1L);
        student.setFirstName("Kunegunda");
        entityManager.merge(student);
        System.out.println(student.getId());
        entityManager.getTransaction().commit();
    }

    public void deleteStudent(Student student) {
        entityManager.getTransaction().begin();
        ;
        entityManager.remove(student);
        entityManager.getTransaction().commit();
    }

    public void refreshStudent(Student student) {
        entityManager.getTransaction().begin();
        ;
        entityManager.refresh(student);
        entityManager.getTransaction().commit();
    }

    public List<Student> getStudents() {
        return entityManager
                .createQuery("from Student", Student.class)
                .getResultList();
    }

    public long getStudentsCount() {
        return entityManager
                .createQuery("select count(*) from Student", Long.class)
                .getSingleResult();
    }

    public Optional<Student> getStudentByName(String name) {
        try {
            Student s = entityManager.createQuery(
                    "from Student s where s.firstName = :name or s.lastName = :name",
                    Student.class
            )
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.of(s);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public List<Student> findStudentsByName(String name) {
        return entityManager.createQuery(
                "from Student s where s.firstName like :name" +
                        " or s.lastName like :name",
                Student.class
        ).setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public List<SchoolClass> getClassesByStudentId(long id) {
        return entityManager.createQuery(
                "select sc from SchoolClass sc join sc.students s where s.id = :id",
                SchoolClass.class
        ).setParameter("id", id)
                .getResultList();
    }

    public List<Student> getAllFriendStudentsByStudentId(long id) {
        return entityManager.createQuery(
                "select distinct f from Student s "
                        + "join s.schoolClasses sc "
                        + "join sc.students f "
                        + "join fetch f.schoolClasses where s.id = :id and f.id != :id",
                Student.class
        ).setParameter("id", id)
                .getResultList();
    }
}
