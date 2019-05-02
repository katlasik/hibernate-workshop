package pl.sda.utils;

public class JDBCUrlBuilder {

  public static String build(String host, String port, String database) {
    return String.format(
        "jdbc:mysql://%s:%s/%s?serverTimezone=UTC&characterEncoding=UTF-8", host, port, database);
  }
}
