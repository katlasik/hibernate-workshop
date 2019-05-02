package pl.sda.hibernate.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("verbal")
public class VerbalTest extends Test {

  private Boolean isPresentation;

  public VerbalTest() {}

  public VerbalTest(Long id, String subject, LocalDate date, Boolean isPresentation) {
    this.id = id;
    this.subject = Objects.requireNonNull(subject);
    this.date = Objects.requireNonNull(date);
    this.isPresentation = Objects.requireNonNull(isPresentation);
  }

  public boolean isPresentation() {
    return isPresentation;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", VerbalTest.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("subject='" + subject + "'")
        .add("date=" + date)
        .add("isPresentation=" + isPresentation)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    VerbalTest test = (VerbalTest) o;
    return Objects.equals(id, test.id)
        && Objects.equals(subject, test.subject)
        && Objects.equals(date, test.date)
        && Objects.equals(isPresentation, test.isPresentation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, subject, date, isPresentation);
  }
}
