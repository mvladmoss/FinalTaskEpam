package com.epam.fitness.uitls;

import com.epam.fitness.connection.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class StatemenUtil {
//перенести
    public static void prepare(PreparedStatement preparedStatement, List<Object> parameters) throws SQLException {
        int length = parameters.size();
        for (int i = 0; i < length; i++) {
            preparedStatement.setObject(i + 1, parameters.get(i));
        }
    }

}
