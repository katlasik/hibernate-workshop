package pl.sda.hibernate.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Teacher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;

  @OneToMany(mappedBy = "teacher")
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
    return Teacher.class.getSimpleName() + "[" + "id=" + id + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (getClass() != o.getClass()) return false;
    Teacher other = (Teacher) o;
    return id != null && id.equals(other.getId());
  }

  @Override
  public int hashCode() {
    return 113;
  }
}
