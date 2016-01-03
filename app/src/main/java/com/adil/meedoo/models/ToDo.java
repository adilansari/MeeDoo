package com.adil.meedoo.models;

import com.adil.meedoo.helpers.DateHelper;

import java.io.Serializable;
import java.util.Date;

public class ToDo implements Serializable{
    private int id;
    private String text;
    private Date dueDate;
    private Priority priority;

    private static final String DEFAULT_TEXT = "...";
    private static final Date DEFAULT_DATE = new Date();
    private static final Priority DEFAULT_PRIORITY = Priority.LOW;

    public ToDo(){
        this(DEFAULT_TEXT, DEFAULT_DATE, DEFAULT_PRIORITY);
    }

    public ToDo(int id, String text, Date dueDate, Priority priority){
        this.id = id;
        this.text = text;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public ToDo(String text, Date dueDate, Priority priority){
        this.text = text;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public int getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String toString(){
        String str = "[id: " + Integer.toString(getId()) +
                "text: " + getText() +
                "due_date: " + DateHelper.getDateAsString(getDueDate()) +
                "priority: " + getPriority().toString();

        return str;
    }
}
