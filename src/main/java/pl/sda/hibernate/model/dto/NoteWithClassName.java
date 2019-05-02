package pl.sda.hibernate.model.dto;

import java.util.Objects;
import java.util.StringJoiner;

public class NoteWithClassName {

  private final Integer note;
  private final String className;

  public NoteWithClassName(Integer note, String className) {
    this.note = Objects.requireNonNull(note);
    this.className = Objects.requireNonNull(className);
  }

  public Integer getNote() {
    return note;
  }

  public String getClassName() {
    return className;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NoteWithClassName that = (NoteWithClassName) o;
    return Objects.equals(note, that.note) && Objects.equals(className, that.className);
  }

  @Override
  public int hashCode() {
    return Objects.hash(note, className);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", NoteWithClassName.class.getSimpleName() + "[", "]")
        .add("note=" + note)
        .add("className='" + className + "'")
        .toString();
  }
}
