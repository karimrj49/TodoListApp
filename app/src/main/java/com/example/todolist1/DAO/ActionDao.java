package com.example.todolist1.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.todolist1.Entities.Action;
import com.example.todolist1.Entities.Status;

import java.util.List;

@Dao
public interface ActionDao {

    @Insert
    void insert(Action action);

    @Delete
    void delete(Action action);

    @Query("SELECT * FROM action_table")
    LiveData<List<Action>> getAllAction();

}
