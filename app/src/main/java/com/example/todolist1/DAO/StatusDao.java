package com.example.todolist1.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.todolist1.Entities.Status;

import java.util.List;

@Dao
public interface StatusDao  {

    @Query("SELECT * FROM status_table")
    LiveData<List<Status>> getAllStatus();

    @Insert
    void inserts(Status status);

    @Query("Select title from status_table WHERE statusid = :id")
    String getStatusById(int id);

    @Query("DELETE FROM status_table")
    void deleteAll();


}
