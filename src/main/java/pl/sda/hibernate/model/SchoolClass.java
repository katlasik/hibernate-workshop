package pl.sda.hibernate.model;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
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
    if (o == null || getClass() != o.getClass()) return false;
    SchoolClass that = (SchoolClass) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SchoolClass.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("name='" + name + "'")
        .toString();
  }
}
