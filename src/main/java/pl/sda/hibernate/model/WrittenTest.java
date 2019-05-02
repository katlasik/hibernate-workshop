package pl.sda.hibernate.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("written")
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
    return new StringJoiner(", ", WrittenTest.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("subject='" + subject + "'")
        .add("date=" + date)
        .add("duration=" + duration)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WrittenTest test = (WrittenTest) o;
    return Objects.equals(id, test.id)
        && Objects.equals(subject, test.subject)
        && Objects.equals(date, test.date)
        && Objects.equals(duration, test.duration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, subject, date, duration);
  }
}
