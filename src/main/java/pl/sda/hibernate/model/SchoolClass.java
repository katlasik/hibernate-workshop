package pl.sda.hibernate.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class SchoolClass {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne private Teacher teacher;

  @ManyToMany(mappedBy = "schoolClasses")
  private List<Student> students;

  @OneToMany(mappedBy = "schoolClass")
  private List<Test> tests;

  public SchoolClass() {}

  public SchoolClass(Long id, String name) {
    Objects.requireNonNull(name);

    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public List<Student> getStudents() {
    return students;
  }

  public List<Test> getTests() {
    return tests;
  }

  public List<String> getLessonTopics() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public void addLessonTopic(String topic) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (getClass() != o.getClass()) return false;
    SchoolClass other = (SchoolClass) o;
    return id != null && id.equals(other.getId());
  }

  @Override
  public int hashCode() {
    return 110;
  }

  @Override
  public String toString() {
    return SchoolClass.class.getSimpleName() + "[" + "id=" + id + "]";
  }
}
