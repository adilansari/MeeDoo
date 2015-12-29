package com.adil.meedoo.models;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adil on 12/29/15.
 */

public enum Priority {
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    private int priorityCode;
    private static final Map<Integer, Priority> lookup = new HashMap<Integer, Priority>();
    static {
        for(Priority p: EnumSet.allOf(Priority.class))
            lookup.put(p.getPriorityCode(), p);
    }

    Priority(int priorityCode){
        this.priorityCode = priorityCode;
    }

    public int getPriorityCode(){
        return this.priorityCode;
    }

    public static Priority getPriority(int priorityCode){
        return lookup.get(priorityCode);
    }

}
