package com.util;

public class Sql {
  /* USER */
  public static final String INSERT_USER = "INSERT INTO projectiv.dbo.app_user(name, email, password, apartment, block, typeUser) VALUES (?, ?, ?, ?, ?, ?)";

  public static final String UPDATE_USER =
    "UPDATE projectiv.dbo.app_user SET " +
      "  name  = ?,        " +
      "  email = ?,        " +
      "  password = ?,     " +
      "  apartment  = ?,   " +
      "  block = ?,        " +
      "  typeUser = ?      " +
      "WHERE               " +
      "  id = ?;           ";

  public static final String LOGIN = "SELECT id, email, password FROM projectiv.dbo.app_user WHERE email = ? and password = ? ";
  public static final String FIND_USER = "SELECT * FROM projectiv.dbo.app_user WHERE id = ?";
  public static final String DELETE_USER = "DELETE FROM projectiv.dbo.app_user WHERE id = ?";

  /* ORDER */
  public static final String INSERT_ORDER = "INSERT INTO projectiv.dbo.app_order (addressee, status, apartment, block, dt_delivery, cd_user) VALUES (?, ?, ?, ?, ?, ?)";

  public static final String UPDATE_ORDER =
    "UPDATE projectiv.dbo.app_order SET" +
      " status = ?," +
      " dt_pickup = ? " +
      "WHERE cd_user = ? and id_order = ?";

  public static final String FIND_ORDER = "SELECT * FROM projectiv.dbo.app_order WHERE cd_user = ?";
  public static final String FIND_ORDER_OPERADOR = "select * from projectiv.dbo.app_order where addressee = ? and apartment = ? and block = ? ";
}