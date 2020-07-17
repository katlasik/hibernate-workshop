package pl.sda.hibernate.model;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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
    return VerbalTest.class.getSimpleName() + "[" + "id=" + id + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (getClass() != o.getClass()) return false;
    VerbalTest other = (VerbalTest) o;
    return id != null && id.equals(other.getId());
  }

  @Override
  public int hashCode() {
    return 120;
  }
}
