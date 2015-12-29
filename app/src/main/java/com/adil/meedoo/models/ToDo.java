package com.adil.meedoo.models;

import java.util.Date;


public class ToDo {
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

    public void setId(int id) {
        this.id = id;
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
}
