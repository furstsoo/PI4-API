package com.repository;

import com.conexao.ConnectionPI4;
import com.entity.Order;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.util.Sql.INSERT_ORDER;

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
    preparedStatement.setString(2,order.getStatus());
    preparedStatement.setString(3, order.getApartment());
    preparedStatement.setString(4, order.getBlock());
    preparedStatement.setString(5, order.getDt_delivery());
    preparedStatement.setInt(6, order.getCd_user());
    log.info("order >>> {} ",order.toString());
    nRowsInserted += preparedStatement.executeUpdate();

    return nRowsInserted != 0;
  }
}