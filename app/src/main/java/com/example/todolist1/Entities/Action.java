package com.example.todolist1.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "action_table")
public class Action {

    @PrimaryKey(autoGenerate = true)
    private int id ;
    private String title ;
    private String description ;
    private int idstatus ;
    @Ignore
    Status s;

    public Action ( String title , String description ,int idstatus){

        this.title = title;
        this.description = description;
        this.idstatus = idstatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(int idstatus) {
        this.idstatus = idstatus;
    }
}
