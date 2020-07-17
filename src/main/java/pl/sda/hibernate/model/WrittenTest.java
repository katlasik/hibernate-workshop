package pl.sda.hibernate.model;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

public class WrittenTest extends Test {

  private Long duration;

  public WrittenTest() {}

  public WrittenTest(Long id, String subject, LocalDate date, Long duration) {
    this.id = id;
    this.subject = Objects.requireNonNull(subject);
    this.date = Objects.requireNonNull(date);
    this.duration = Objects.requireNonNull(duration);
  }

  public Long getDuration() {
    return duration;
  }

  @Override
  public String toString() {
    return WrittenTest.class.getSimpleName() + "[" + "id=" + id + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (getClass() != o.getClass()) return false;
    WrittenTest other = (WrittenTest) o;
    return id != null && id.equals(other.getId());
  }

  @Override
  public int hashCode() {
    return 121;
  }
}
