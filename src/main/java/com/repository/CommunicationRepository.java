package com.repository;

import com.conexao.ConnectionPI4;
import com.entity.Communication;
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
public class CommunicationRepository {
    ConnectionPI4 connectionPI4 = new ConnectionPI4();
    Connection con = null;
    PreparedStatement preparedStatement;

    public boolean insertCommunication(Communication cm) throws SQLException {
        con = connectionPI4.connection();
        log.info(">>> Inserting some data into table. <<<");
        int nRowsInserted = 0;
        preparedStatement = con.prepareStatement(ADD_AVISO);

        preparedStatement.setString(1, cm.getTitle());
        preparedStatement.setString(2, cm.getMessage());
        preparedStatement.setInt(3, cm.getCd_user());
        log.info("order >>> {} ", cm.toString());
        nRowsInserted += preparedStatement.executeUpdate();

        return nRowsInserted != 0;
    }

    public List<Communication> viewCommunications() throws SQLException {
        con = connectionPI4.connection();
        List<Communication> communications = new ArrayList<>();
        log.info(">>> Searching for communications. <<<");
        preparedStatement = con.prepareStatement(SELECT_AVISOS);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs != null && rs.next()) {
            Communication communication = new Communication();
            communication.setId(rs.getInt("id"));
            communication.setTitle(rs.getString("title"));
            communication.setCd_user(rs.getInt("cd_user"));
            communication.setMessage(rs.getString("message"));
            communications.add(communication);
        }
        return communications;
    }
}
