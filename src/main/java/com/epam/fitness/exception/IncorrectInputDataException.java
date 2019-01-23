package com.epam.fitness.exception;

public class IncorrectInputDataException extends Exception {
    public IncorrectInputDataException(String message){
        super(message);
    }

    public IncorrectInputDataException(){ }

    public IncorrectInputDataException(Throwable th){
        super(th);
    }

    public IncorrectInputDataException(String m, Throwable th){
        super(m,th);
    }
}
