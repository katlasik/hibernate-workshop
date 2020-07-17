package pl.sda.hibernate.model;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;

public class StudentNote {

  Long id;

  private Integer value;

  private Integer weight;

  private LocalDate createdAt;

  private SchoolClass schoolClass;

  private Student student;

  public StudentNote(Integer value, Integer weight, SchoolClass schoolClass, Student student) {
    this.value = Objects.requireNonNull(value);
    this.weight = Objects.requireNonNull(weight);
    this.schoolClass = Objects.requireNonNull(schoolClass);
    this.student = Objects.requireNonNull(student);
  }

  public StudentNote() {}

  public Long getId() {
    return id;
  }

  public Integer getValue() {
    return value;
  }

  public Integer getWeight() {
    return weight;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public SchoolClass getSchoolClass() {
    return schoolClass;
  }

  public Student getStudent() {
    return student;
  }

  @PrePersist
  void prePersist() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (getClass() != o.getClass()) return false;
    StudentNote other = (StudentNote) o;
    return id != null && id.equals(other.getId());
  }

  @Override
  public int hashCode() {
    return 112;
  }

  @Override
  public String toString() {
    return StudentNote.class.getSimpleName() + "[" + "id=" + id + "]";
  }
}
