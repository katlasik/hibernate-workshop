package pl.sda.hibernate.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;
import javax.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class StudentNote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  private Integer value;

  @ColumnDefault("1")
  private Integer weight;

  private LocalDate createdAt;

  @ManyToOne private SchoolClass schoolClass;

  @ManyToOne private Student student;

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
    if (o == null || getClass() != o.getClass()) return false;
    StudentNote that = (StudentNote) o;
    return Objects.equals(id, that.id)
        && Objects.equals(value, that.value)
        && Objects.equals(weight, that.weight)
        && Objects.equals(createdAt, that.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, value, weight, createdAt);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", StudentNote.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("value=" + value)
        .add("weight=" + weight)
        .add("createdAt=" + createdAt)
        .toString();
  }
}
