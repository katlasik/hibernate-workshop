package pl.sda.hibernate.utils;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class SnakeCasePhysicalNamingStrategy implements PhysicalNamingStrategy {

  private static final String REGEX = "([a-z])([A-Z])";
  private static final String REPLACEMENT = "$1_$2";

  @Override
  public Identifier toPhysicalCatalogName(
      final Identifier identifier, final JdbcEnvironment jdbcEnv) {
    return convertToSnakeCase(identifier);
  }

  @Override
  public Identifier toPhysicalColumnName(
      final Identifier identifier, final JdbcEnvironment jdbcEnv) {
    return convertToSnakeCase(identifier);
  }

  @Override
  public Identifier toPhysicalSchemaName(
      final Identifier identifier, final JdbcEnvironment jdbcEnv) {
    return convertToSnakeCase(identifier);
  }

  @Override
  public Identifier toPhysicalSequenceName(
      final Identifier identifier, final JdbcEnvironment jdbcEnv) {
    return convertToSnakeCase(identifier);
  }

  @Override
  public Identifier toPhysicalTableName(
      final Identifier identifier, final JdbcEnvironment jdbcEnv) {
    return convertToSnakeCase(identifier);
  }

  private Identifier convertToSnakeCase(Identifier identifier) {

    if (identifier != null) {
      String newName = identifier.getText().replaceAll(REGEX, REPLACEMENT).toLowerCase();
      return Identifier.toIdentifier(newName);
    } else {
      return null;
    }
  }
}
