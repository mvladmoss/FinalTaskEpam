package com.epam.fitness.repository.creator;


import com.epam.fitness.connection.ConnectionPool;
import com.epam.fitness.connection.ProxyConnection;
import com.epam.fitness.repository.*;

import java.sql.Connection;


public class RepositoryCreator implements AutoCloseable {
    private ConnectionPool connectionPool;
    private Connection connection;

    public RepositoryCreator() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
    }

    public ClientRepository getClientRepository() {
        return new ClientRepository(connection);
    }


    public CoachRepository getCoachRepository() {
        return new CoachRepository(connection);
    }


    public OrderInformationRepository getOrderInformationRepository() {
        return new OrderInformationRepository(connection);
    }

    public ProgramRepository getProgramRepository(){
        return new ProgramRepository(connection);
    }

    public ExerciseRepository getExerciseRepository(){
        return new ExerciseRepository(connection);
    }

    public ExerciseDtoRepository getExerciseDtoRepository(){ return  new ExerciseDtoRepository(connection);}

    public NutritionRepository getNutritionRepository(){ return  new NutritionRepository(connection);}

    @Override
    public void close() {
        connectionPool.releaseConnection((ProxyConnection) connection);
    }
}
