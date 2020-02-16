package com.example.todolist1.Repository;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.example.todolist1.ActionDatabase;
import com.example.todolist1.DAO.ActionDao;
import com.example.todolist1.Entities.Action;

import java.util.List;

public class ActionRepository {

    private ActionDao actionDao;
    private LiveData<List<Action>>allAction;

    public ActionRepository(Application application){

        ActionDatabase database = ActionDatabase.getInstance(application);
        actionDao = database.actionDao();
        allAction = actionDao.getAllAction();
    }

    public void insert(Action action){

        new InsertAsyncTask(actionDao).execute(action);
    }

    public void delete (Action action){

        new DeleteAsyncTask(actionDao).execute(action);
    }

    public LiveData<List<Action>> getAllAction() {

        return allAction;
    }

    private static class InsertAsyncTask extends AsyncTask<Action , Void , Void >{

        private ActionDao actionDao;
        private InsertAsyncTask (ActionDao actionDao){
            this.actionDao = actionDao;
        }
        @Override
        protected Void doInBackground(Action... actions) {
            actionDao.insert(actions[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Action , Void , Void >{

        private ActionDao actionDao;
        private DeleteAsyncTask (ActionDao actionDao){
            this.actionDao = actionDao;
        }
        @Override
        protected Void doInBackground(Action... actions) {
            actionDao.delete(actions[0]);
            return null;
        }
    }
}
