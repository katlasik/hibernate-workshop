package pl.sda.hibernate.model;

import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import javax.persistence.*;

public class Teacher {

  private Long id;

  private String firstName;
  private String lastName;

  private Set<SchoolClass> schoolClasses;

  public Teacher() {}

  public Teacher(Long id, String firstName, String lastName, Set<SchoolClass> schoolClasses) {
    this.schoolClasses = schoolClasses;
    Objects.requireNonNull(firstName);
    Objects.requireNonNull(lastName);

    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Set<SchoolClass> getSchoolClasses() {
    return schoolClasses;
  }

  public void assignToSchoolClass(SchoolClass schoolClass) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  void preRemove() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Teacher.class.getSimpleName() + "[", "]")
        .add("id=" + getId())
        .add("firstName='" + getFirstName() + "'")
        .add("lastName='" + getLastName() + "'")
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Teacher student = (Teacher) o;
    return Objects.equals(id, student.getId())
        && firstName.equals(student.getFirstName())
        && lastName.equals(student.getLastName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getFirstName(), getLastName());
  }
}
