package com.epam.fitness.utils;

import java.util.Calendar;
import java.util.Date;

public class DateProducer {
    public static  Date getNecessaryDate(Date dateToAdd, PeriodCost period){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateToAdd);
        switch (period){
            case for_30_days:{
                calendar.add(Calendar.DAY_OF_YEAR,30);
                dateToAdd = calendar.getTime();
                break;
            }
            case for_60_days:{
                calendar.add(Calendar.DAY_OF_YEAR,60);
                dateToAdd=calendar.getTime();
                break;
            }
            case for_90_days:{
                calendar.add(Calendar.DAY_OF_YEAR,90);
                dateToAdd=calendar.getTime();
                break;
            }
            case for_14_days:{
                calendar.add(Calendar.DAY_OF_YEAR,14);
                dateToAdd=calendar.getTime();
                break;
            }
            default:{
                throw  new IllegalArgumentException("No such membership period");
            }
        }
        return new java.sql.Date(dateToAdd.getTime());
    }
}
