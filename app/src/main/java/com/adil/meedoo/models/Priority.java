package com.adil.meedoo.models;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adil on 12/29/15.
 */

public enum Priority {
    LOW(1, "LOW"),
    MEDIUM(2, "MEDIUM"),
    HIGH(3, "HIGH");

    private int priorityCode;
    private String priorityString;

    private static final Map<Integer, Priority> lookup = new HashMap<Integer, Priority>();
    static {
        for(Priority p: EnumSet.allOf(Priority.class))
            lookup.put(p.getPriorityCode(), p);
    }

    Priority(int priorityCode, String priorityString){
        this.priorityCode = priorityCode;
        this.priorityString = priorityString;
    }

    public int getPriorityCode(){
        return this.priorityCode;
    }

    public String getPriorityString(){
        return this.priorityString;
    }

    public static Priority getPriority(int priorityCode){
        return lookup.get(priorityCode);
    }

}
