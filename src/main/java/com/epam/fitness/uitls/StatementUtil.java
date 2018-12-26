package com.epam.fitness.uitls;

import com.epam.fitness.connection.ProxyConnection;
import com.sun.xml.internal.ws.wsdl.writer.document.Types;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class StatementUtil {
//перенести
    public static void prepare(PreparedStatement preparedStatement, List<Object> parameters) throws SQLException {
        int length = parameters.size();
        for (int i = 0; i < length; i++) {
            if (parameters.get(i) == null) {
                preparedStatement.setNull(i + 1, getType(parameters.get(i)));
            } else {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
        }
    }

    private static Integer getType(Object object){
        if(object instanceof Integer){
            return java.sql.Types.INTEGER;
        }
        if (object instanceof Float){
            return java.sql.Types.FLOAT;
        }
        if(object instanceof String){
            return java.sql.Types.VARCHAR;
        }
        else{
            return java.sql.Types.NULL;
        }
    }

}
