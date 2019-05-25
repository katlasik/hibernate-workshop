package pl.sda.hibernate.model;

import java.time.LocalDate;
import javax.persistence.*;

public class Test {

  protected Long id;

  protected String subject;
  protected LocalDate date;

  protected SchoolClass schoolClass;

  public Long getId() {
    return id;
  }

  public String getSubject() {
    return subject;
  }

  public LocalDate getDate() {
    return date;
  }

  public SchoolClass getSchoolClass() {
    return schoolClass;
  }
}
