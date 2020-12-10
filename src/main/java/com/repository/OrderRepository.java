package com.repository;

import com.conexao.ConnectionPI4;
import com.entity.Order;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.util.Sql.*;

@Slf4j
public class OrderRepository {
  ConnectionPI4 connectionPI4 = new ConnectionPI4();
  Connection con = null;
  PreparedStatement preparedStatement;
  Order order = new Order();

  public boolean insertOrder(Order order) throws SQLException {
    con = connectionPI4.connection();
    log.info(">>> Inserting some data into table. <<<");
    int nRowsInserted = 0;
    preparedStatement = con.prepareStatement(INSERT_ORDER);

    preparedStatement.setString(1, order.getAddressee());
    preparedStatement.setString(2, order.getStatus());
    preparedStatement.setString(3, order.getApartment());
    preparedStatement.setString(4, order.getBlock());
    preparedStatement.setString(5, order.getDt_delivery());
    preparedStatement.setInt(6, order.getCd_user());
    preparedStatement.setString(7, order.getRemetente());
    preparedStatement.setString(8, order.getDescricao());
    log.info("order >>> {} ",order.toString());
    nRowsInserted += preparedStatement.executeUpdate();

    return nRowsInserted != 0;
  }

  public boolean updateOrder(Order od) throws SQLException {
    con = connectionPI4.connection();
    log.info(">>> Modifying some data in table. <<<");
    int nRowsUpdated = 0;
    preparedStatement = con.prepareStatement(UPDATE_ORDER);

    preparedStatement.setString(1, od.getStatus());
    preparedStatement.setString(2, od.getDt_pickup());
    preparedStatement.setInt(3, od.getCd_user());
    preparedStatement.setInt(4, od.getId_order());

    nRowsUpdated += preparedStatement.executeUpdate();

    return nRowsUpdated != 0;
  }

  /*FindOrders para visualização do condomino*/
  public List<Order> findOrdersCondomino(String block, String apartment) throws SQLException {
    con = connectionPI4.connection();
    List<Order> orders = new ArrayList<>();
    log.info(">>> Searching for user. <<<");
    preparedStatement = con.prepareStatement(FIND_ORDER);
    preparedStatement.setString(1, block);
    preparedStatement.setString(2, apartment);
    ResultSet rs = preparedStatement.executeQuery();

    while (rs != null && rs.next()) {
      Order orderView = new Order();
      orderView.setId_order(rs.getInt("id_order"));
      orderView.setAddressee(rs.getString("addressee"));
      orderView.setStatus(rs.getString("status"));
      orderView.setApartment(rs.getString("apartment"));
      orderView.setBlock(rs.getString("block"));
      orderView.setDt_delivery(rs.getString("dt_delivery"));
      orderView.setDt_pickup(rs.getString("dt_pickup"));
      orderView.setCd_user(rs.getInt("cd_user"));
      orderView.setRemetente(rs.getString("remetente"));
      orderView.setDescricao(rs.getString("descricao"));
      orders.add(orderView);
    }
    return orders;
  }

  public List<Order> findOrdersOperador(String name, String block, String apto) throws SQLException {
    con = connectionPI4.connection();
    List<Order> orders = new ArrayList<>();
    log.info(">>> Searching for user. <<<");
    preparedStatement = con.prepareStatement(FIND_ORDER_OPERADOR);
    preparedStatement.setString(1, name);
    preparedStatement.setString(2, apto);
    preparedStatement.setString(3, block);

    ResultSet rs = preparedStatement.executeQuery();

    while (rs != null && rs.next()) {
      Order order1 = new Order();
      order1.setId_order(rs.getInt("id_order"));
      order1.setAddressee(rs.getString("addressee"));
      order1.setStatus(rs.getString("status"));
      order1.setApartment(rs.getString("apartment"));
      order1.setBlock(rs.getString("block"));
      order1.setDt_delivery(rs.getString("dt_delivery"));
      order1.setDt_pickup(rs.getString("dt_pickup"));
      order1.setCd_user(rs.getInt("cd_user"));
      order1.setRemetente(rs.getString("remetente"));
      order1.setDescricao(rs.getString("descricao"));
      orders.add(order1);
    }
    return orders;
  }
}