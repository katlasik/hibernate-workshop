package pl.sda.hibernate.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateBootstraper {

  private HibernateBootstraper() {}

  public static EntityManagerFactory createEntityManagerFactory() {
    return Persistence.createEntityManagerFactory("school");
  }
}
