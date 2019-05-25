package pl.sda.hibernate.model;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import javax.persistence.*;

public class SchoolClass {

  private Long id;

  private String name;

  private Teacher teacher;

  private List<Student> students;

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
    this.teacher = teacher;
  }

  public List<Student> getStudents() {
    return students;
  }

  public List<Test> getTests() {
    return tests;
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
