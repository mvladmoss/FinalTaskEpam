
package com.epam.fitness.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Scanner;

public class DataReader {

    private final static Logger LOGGER = LogManager.getLogger(DataReader.class);

    public String read(String path) {
        StringBuilder resultStringJson = new StringBuilder();
        InputStream file;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            file = classLoader.getResourceAsStream(path);
        }catch (NullPointerException exception){
            LOGGER.fatal("There is no such file on path:" + path);
            throw  new RuntimeException("There is no such file on path:" + path);
        }
        Scanner scanner;
        scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            resultStringJson.append(scanner.nextLine()).append("\n");
        }
        return resultStringJson.toString();
    }
}