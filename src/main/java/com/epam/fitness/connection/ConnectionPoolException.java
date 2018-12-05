package com.epam.fitness.connection;


public class ConnectionPoolException extends Exception {

    public ConnectionPoolException(String message){
        super(message);
    }

    public ConnectionPoolException(){ }

    public ConnectionPoolException(Throwable th){
        super(th);
    }

    public ConnectionPoolException(String m, Throwable th){
        super(m,th);
    }
}
