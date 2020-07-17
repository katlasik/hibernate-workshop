package pl.sda.hibernate.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import pl.sda.hibernate.model.dto.FullName;

@Entity
public class Teacher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Embedded private FullName fullName;

  @OneToMany(mappedBy = "teacher")
  private Set<SchoolClass> schoolClasses;

  public Teacher() {}

  public Teacher(Long id, String firstName, String lastName, Set<SchoolClass> schoolClasses) {
    this.schoolClasses = schoolClasses;
    Objects.requireNonNull(firstName);
    Objects.requireNonNull(lastName);

    this.id = id;
    this.fullName = new FullName(firstName, lastName);
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return fullName.getFirstName();
  }

  public void setFirstName(String firstName) {
    this.fullName.setFirstName(firstName);
  }

  public String getLastName() {
    return fullName.getLastName();
  }

  public void setLastName(String lastName) {
    this.fullName.setLastName(lastName);
  }

  public Set<SchoolClass> getSchoolClasses() {
    return schoolClasses;
  }

  public void assignToSchoolClass(SchoolClass schoolClass) {
    schoolClasses.add(schoolClass);
    schoolClass.setTeacher(this);
  }

  @PreRemove
  void preRemove() {
    if (!schoolClasses.isEmpty()) {
      throw new IllegalStateException("Teacher is teaching classes");
    }
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
