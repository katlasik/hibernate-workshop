package pl.sda.hibernate.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.sda.test.base.DatabaseSetupTest;

class HibernateBootstraperTest {

  @Test
  @DisplayName("Connection should be opened")
  void createEntityManagerFactory() {
    DatabaseSetupTest db = new DatabaseSetupTest();

    assertTrue(HibernateBootstraper.createEntityManagerFactory().isOpen());
    db.launch();
  }
}
