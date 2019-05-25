package pl.sda.hibernate.model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Test {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  protected String subject;
  protected LocalDate date;

  @ManyToOne protected SchoolClass schoolClass;

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
