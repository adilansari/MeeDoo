package com.adil.meedoo.models;

import com.adil.meedoo.helpers.DateHelper;

import java.io.Serializable;
import java.util.Date;


public class ToDo implements Serializable{
    private int id;
    private String text;
    private Date dueDate;
    private Priority priority;

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
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
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
