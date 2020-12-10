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

  public static final String LOGIN = "SELECT * FROM projectiv.dbo.app_user WHERE lower(email) = lower(?) and password = ? ";
  public static final String FIND_USER = "SELECT * FROM projectiv.dbo.app_user WHERE id = ?";
  public static final String DELETE_USER = "DELETE FROM projectiv.dbo.app_user WHERE id = ?";

  /* ORDER */
  public static final String INSERT_ORDER = "INSERT INTO projectiv.dbo.app_order (addressee, status, apartment, block, dt_delivery, cd_user, remetente, descricao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

  public static final String UPDATE_ORDER =
    "UPDATE projectiv.dbo.app_order SET" +
      " status = ?," +
      " dt_pickup = ? " +
      "WHERE cd_user = ? and id_order = ?";

  public static final String FIND_ORDER = "SELECT * FROM projectiv.dbo.app_order WHERE block = ? and apartment = ?";
  public static final String FIND_ORDER_OPERADOR = "select * from projectiv.dbo.app_order where lower(addressee) = lower(?) and apartment = ? and block = ? ";

  /* REGISTER */
  public static final String FIND_REGISTER = "SELECT * FROM projectiv.dbo.app_register WHERE register_day = ?";

  public static final String ADD_REGISTER = "INSERT INTO projectiv.dbo.app_register(register_day, cd_user) VALUES(?, ?)";


  /*Communication*/

  public static final String ADD_AVISO = "INSERT INTO projectiv.dbo.app_communications (title, message, cd_user) VALUES(?, ?, ?)";
  public static final String SELECT_AVISOS = "SELECT * FROM projectiv.dbo.app_communications";



}