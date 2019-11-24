package pl.sda.test.base;

import static com.ninja_squad.dbsetup.Operations.*;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.time.LocalDate;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import pl.sda.utils.JDBCUrlBuilder;

public class DatabaseSetupTest implements BeforeAllCallback, BeforeEachCallback {

  private DbSetupTracker dbSetupTracker = new DbSetupTracker();
  private Config config = ConfigFactory.load();
  private DataSource dataSource = createDataSource();
  private DbSetup dbSetup = createDbSetup();

  private DataSource createDataSource() {
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUser(config.getString("username"));
    dataSource.setPassword(config.getString("password"));
    dataSource.setUrl(
        JDBCUrlBuilder.build(
            config.getString("host"), config.getString("port"), config.getString("database")));
    return dataSource;
  }

  public DataSource getDatasource() {
    return dataSource;
  }

  public void launch() {
    dbSetupTracker.launchIfNecessary(dbSetup);
  }

  private DbSetup createDbSetup() {
    Operation operation =
        sequenceOf(
            deleteAllFrom("LessonTopics"),
            deleteAllFrom("StudentNote"),
            deleteAllFrom("Test"),
            deleteAllFrom("SchoolClassStudent"),
            deleteAllFrom("SchoolClass"),
            deleteAllFrom("Student"),
            deleteAllFrom("Teacher"),
            insertInto("Student")
                .columns("id", "firstName", "lastName", "birthdate")
                .values(1L, "Szymon", "Kowalski", LocalDate.parse("1999-02-03"))
                .values(2L, "Krystian", "Nowak", LocalDate.parse("1999-02-03"))
                .values(3L, "Krystyna", "Kowal", LocalDate.parse("1996-03-11"))
                .values(4L, "Błażej", "Rudnicki", LocalDate.parse("1998-12-03"))
                .build(),
            insertInto("Teacher")
                .columns("id", "firstName", "lastName")
                .values(1L, "Damian", "Lewandowski")
                .values(2L, "Beata", "Woźniak")
                .values(3L, "Artur", "Wójcik")
                .build(),
            insertInto("SchoolClass")
                .columns("id", "teacher_id", "Name")
                .values(1L, 1L, "Matematyka")
                .values(2L, 2L, "Fizyka")
                .values(3L, 3L, "Chemia")
                .build(),
            insertInto("LessonTopics")
                .columns("schoolClass_id", "topic")
                .values(1L, "Ułamki")
                .values(1L, "Funkcja kwadratowa")
                .values(2L, "Optyka")
                .values(2L, "Mechanika")
                .values(3L, "Estry")
                .values(3L, "Kwasy")
                .build(),
            insertInto("SchoolClassStudent")
                .columns("schoolClass_id", "student_id")
                .values(1L, 1L)
                .values(2L, 1L)
                .values(2L, 2L)
                .values(3L, 3L)
                .values(2L, 3L)
                .values(3L, 4L)
                .build(),
            insertInto("Test")
                .columns(
                    "id", "type", "subject", "isPresentation", "duration", "schoolClass_id", "date")
                .values(1L, "verbal", "Ułamki", false, null, 1, LocalDate.parse("2019-02-03"))
                .values(
                    2L, "verbal", "Funkcje liniowe", false, null, 1, LocalDate.parse("2019-02-03"))
                .values(
                    3L,
                    "verbal",
                    "Prezentacja o polimerach",
                    true,
                    null,
                    3,
                    LocalDate.parse("2019-03-11"))
                .values(4L, "verbal", "Ruch liniowy", false, null, 2, LocalDate.parse("2019-12-03"))
                .values(
                    5L,
                    "verbal",
                    "Prezentacja z optyki",
                    true,
                    null,
                    2,
                    LocalDate.parse("2019-02-03"))
                .values(
                    6L, "written", "Funkcje kwadratowe", null, 60, 1, LocalDate.parse("2019-02-03"))
                .values(7L, "written", "Mechanika", null, 60, 2, LocalDate.parse("2019-03-11"))
                .values(
                    8L, "written", "Chemia organiczna", null, 120, 3, LocalDate.parse("2019-12-03"))
                .build(),
            insertInto("StudentNote")
                .columns("id", "value", "weight", "schoolClass_id", "student_id", "createdAt")
                .values(1L, 1, 1, 1, 1, LocalDate.parse("2019-02-03"))
                .values(2L, 2, 1, 2, 2, LocalDate.parse("2019-02-03"))
                .values(3L, 5, 2, 1, 3, LocalDate.parse("2019-03-11"))
                .values(4L, 5, 2, 3, 1, LocalDate.parse("2019-12-03"))
                .values(5L, 4, 1, 2, 2, LocalDate.parse("2019-02-03"))
                .values(6L, 3, 1, 3, 3, LocalDate.parse("2019-02-03"))
                .values(7L, 6, 1, 1, 1, LocalDate.parse("2019-03-11"))
                .values(8L, 5, 2, 1, 2, LocalDate.parse("2019-12-03"))
                .build());

    return new DbSetup(new DataSourceDestination(dataSource), operation);
  }

  @Override
  public void beforeAll(ExtensionContext extensionContext) throws Exception {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
  }

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    launch();
  }
}
