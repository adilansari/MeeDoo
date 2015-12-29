package com.adil.meedoo.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adil on 12/29/15.
 */
public class DateHelper {

    // Date formatter
    private static final DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

    public static String getDateAsString(Date date){
        return dateFormatter.format(date);
    }

    public static Date getStringAsDate(String date) throws ParseException {
        return dateFormatter.parse(date);
    }
}
