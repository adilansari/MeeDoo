package com.adil.meedoo.models;

import android.graphics.Color;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adil on 12/29/15.
 */

public enum Priority {
    LOW(1, "LOW", Color.parseColor("#018210")),
    MEDIUM(2, "MEDIUM", Color.parseColor("#ffaa02")),
    HIGH(3, "HIGH", Color.RED);

    private int priorityCode;
    private String priorityString;
    private int color;

    private static final Map<Integer, Priority> lookup = new HashMap<Integer, Priority>();

    static {
        for(Priority p: EnumSet.allOf(Priority.class))
            lookup.put(p.getPriorityCode(), p);
    }

    Priority(int priorityCode, String priorityString, int color){
        this.priorityCode = priorityCode;
        this.priorityString = priorityString;
        this.color = color;
    }

    public int getPriorityCode(){
        return this.priorityCode;
    }

    public static Priority getPriority(int priorityCode){
        return lookup.get(priorityCode);
    }

    public int getColor() {
        return this.color;
    }

    @Override
    public String toString(){
        return this.priorityString;
    }
}
