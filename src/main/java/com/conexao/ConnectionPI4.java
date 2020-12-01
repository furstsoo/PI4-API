package com.conexao;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Properties;

@Slf4j
public class ConnectionPI4 {

  public Connection connection() throws SQLException {
    log.info(">>> Initialize connection variables. <<<");
    String user = "admin";
    String password = "BTx0LWTLsLoD9alDVaxF";

    Connection connection;

    log.info(">>> Initialize connection object <<<");
    try {
      String url = "jdbc:sqlserver://db-project-4.cai4hwcqjmce.sa-east-1.rds.amazonaws.com:1433";
      log.info(">>> Set connection properties. <<<");
      Properties properties = new Properties();
      properties.setProperty("verifyServerCertificate", "true");
      properties.setProperty("useSSL", "true");
      properties.setProperty("user", user);
      properties.setProperty("password", password);

      log.info(">>> get connection <<<");
      connection = DriverManager.getConnection(url, properties);

    } catch (SQLException e) {
      throw new SQLException("Failed to create connection to database.", e);
    }
    if (connection != null) {
      log.info(">>> Successfully created connection to database. <<<");
      return connection;
    } else {
      log.info(">>> Failed to create connection to database. <<<");
      return null;
    }
  }
}

