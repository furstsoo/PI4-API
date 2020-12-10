package com.repository;

import com.conexao.ConnectionPI4;
import com.entity.Order;
import com.entity.Register;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.util.Sql.*;
import static com.util.configDateUtil.configuraData;

@Slf4j
public class RegisterRepository {
    ConnectionPI4 connectionPI4 = new ConnectionPI4();
    Connection con = null;
    PreparedStatement preparedStatement;

    public boolean insertRegister(Register register) throws SQLException {
        con = connectionPI4.connection();
        log.info(">>> Inserting some data into table. <<<");
        register.setRegister_day(configuraData(register.getRegister_day()));
        int nRowsInserted = 0;
        preparedStatement = con.prepareStatement(ADD_REGISTER);

        preparedStatement.setString(1, register.getRegister_day());
        preparedStatement.setInt(2, register.getCd_user());

        log.info("register >>> {} ", register.toString());
        nRowsInserted += preparedStatement.executeUpdate();

        return nRowsInserted != 0;
    }

    public boolean viewRegister(String data) throws SQLException {
        Boolean retorno = false;
        con = connectionPI4.connection();
        List<Order> orders = new ArrayList<>();
        log.info(">>> Searching for Register. <<<");
        preparedStatement = con.prepareStatement(FIND_REGISTER);
        preparedStatement.setString(1, data);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs != null && rs.next()) {
            retorno = true;
        }

        return retorno;
    }

}
