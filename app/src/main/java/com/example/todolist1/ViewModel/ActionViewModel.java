package com.example.todolist1.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolist1.Entities.Action;
import com.example.todolist1.Repository.ActionRepository;

import java.util.List;

public class ActionViewModel extends AndroidViewModel {

    private ActionRepository actionRepository;
    private LiveData<List<Action>>allAction;
    public ActionViewModel(@NonNull Application application) {
        super(application);

        actionRepository = new ActionRepository(application);
        allAction = actionRepository.getAllAction();
    }

    public void insert(Action action){
        actionRepository.insert(action);
    }

    public void delete(Action action){
        actionRepository.delete(action);
    }

    public LiveData<List<Action>> getAllAction() {
        return allAction;
    }
}
