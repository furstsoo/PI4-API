package com.repository;

import com.conexao.ConnectionPI4;
import com.entity.User;
import lombok.extern.slf4j.Slf4j;
import java.sql.*;

import static com.util.Sql.*;

@Slf4j
public class UserRepository {

  ConnectionPI4 connectionPI4 = new ConnectionPI4();
  Connection con = null;
  PreparedStatement preparedStatement;
  User user = new User();

  public boolean insertUser(User user) throws SQLException {
    con = connectionPI4.connection();
    log.info(">>> Inserting some data into table. <<<");
    int nRowsInserted = 0;
    preparedStatement = con.prepareStatement(INSERT_USER);

    preparedStatement.setString(1, user.getName());
    preparedStatement.setString(2, user.getEmail());
    preparedStatement.setString(3, user.getPassword());
    preparedStatement.setString(4, user.getApartment());
    preparedStatement.setString(5, user.getBlock());
    preparedStatement.setString(6, user.getTypeUser());
    log.info("User {}", user.toString());
    nRowsInserted += preparedStatement.executeUpdate();

    return nRowsInserted != 0;
  }

  public boolean updateUser(User user) throws SQLException {
    con = connectionPI4.connection();
    log.info(">>> Modifying some data in table. <<<");
    int nRowsUpdated = 0;

    preparedStatement = con.prepareStatement(UPDATE_USER);

    preparedStatement.setString(1, user.getName());
    preparedStatement.setString(2, user.getEmail());
    preparedStatement.setString(3, user.getPassword());
    preparedStatement.setString(4, user.getApartment());
    preparedStatement.setString(5, user.getBlock());
    preparedStatement.setString(6, user.getTypeUser());
    preparedStatement.setInt(7, user.getId());
    nRowsUpdated += preparedStatement.executeUpdate();

    return nRowsUpdated != 0;
  }

  public User login(String email, String password) throws SQLException {
    log.info(">>> Fetching data for login. <<<");
    con = connectionPI4.connection();
    preparedStatement = con.prepareStatement(LOGIN);
    preparedStatement.setString(1, email);
    preparedStatement.setString(2, password);
    ResultSet rs = preparedStatement.executeQuery();

    while (rs != null && rs.next()) {
      user.setEmail(rs.getString("email"));
      user.setPassword(rs.getString("password"));
      user.setId(rs.getInt("id"));
    }
    return user;
  }

  public User findUser(int idUser) throws SQLException {
    con = connectionPI4.connection();
    log.info(">>> Searching for user. <<<");
    preparedStatement = con.prepareStatement(FIND_USER);
    preparedStatement.setInt(1, idUser);
    ResultSet rs = preparedStatement.executeQuery();

    while (rs != null && rs.next()) {
      user.setId(rs.getInt("id"));
      user.setName(rs.getString("name"));
      user.setEmail(rs.getString("email"));
      user.setPassword(rs.getString("password"));
      user.setApartment(rs.getString("apartment"));
      user.setBlock(rs.getString("block"));
      user.setTypeUser(rs.getString("typeUser"));
    }
    return user;
  }

  public boolean deleteUser(int idUser) throws SQLException {
    con = connectionPI4.connection();

    log.info(">>> Deleting some data in table. <<<");
    preparedStatement = con.prepareStatement(DELETE_USER);
    preparedStatement.setInt(1, idUser);

    preparedStatement.executeUpdate();
    return true;
  }
}