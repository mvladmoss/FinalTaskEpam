package com.epam.fitness.utils;

import java.util.Calendar;
import java.util.Date;

public class DateProducer {
    public static  Date getNecessaryDate(Date dateToAdd, Integer periodExtension){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateToAdd);
        calendar.add(Calendar.DAY_OF_YEAR,periodExtension);
        dateToAdd = calendar.getTime();
        return new java.sql.Date(dateToAdd.getTime());
    }
}
