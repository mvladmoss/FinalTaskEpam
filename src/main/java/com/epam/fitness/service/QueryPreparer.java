package com.epam.fitness.service;

import com.epam.fitness.connection.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class QueryPreparer {


    public static void prepare(PreparedStatement preparedStatement, String... params) throws SQLException {

        int length = params.length;
        for (int i = 0; i < length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }

    }

}
