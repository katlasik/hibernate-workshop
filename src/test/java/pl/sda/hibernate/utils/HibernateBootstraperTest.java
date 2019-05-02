package pl.sda.hibernate.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.sda.test.base.DatabaseSetup;

class HibernateBootstraperTest {

  @Test
  @DisplayName("Connection should be opened")
  void createEntityManagerFactory() {
    DatabaseSetup db = new DatabaseSetup();

    assertTrue(HibernateBootstraper.createEntityManagerFactory().isOpen());
    db.launch();
  }
}
