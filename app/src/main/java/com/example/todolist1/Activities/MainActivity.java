package com.example.todolist1.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.todolist1.ViewModel.ActionViewModel;
import com.example.todolist1.Entities.Action;
import com.example.todolist1.R;
import com.example.todolist1.RecyclerView.RecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST = 1;
    private ActionViewModel actionViewModel;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.inbox);
        FloatingActionButton floatbutton = (FloatingActionButton) findViewById(R.id.fab);

        floatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, AddActionActivity.class);
                startActivityForResult(intent,REQUEST);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        actionViewModel = ViewModelProviders.of(this).get(ActionViewModel.class);
        actionViewModel.getAllAction().observe(this, new Observer<List<Action>>() {
            @Override
            public void onChanged(List<Action> actions) {
                // Update RecyclerView
                adapter.setList(actions);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                String t = adapter.getAction(viewHolder.getAdapterPosition()).getDescription();
                actionViewModel.delete(adapter.getAction(viewHolder.getAdapterPosition()));
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Toast.makeText(getApplicationContext(),"The task "+t+" is done",Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST && resultCode == RESULT_OK){

            String title = data.getStringExtra("Insert title");
            String desc = data.getStringExtra("Insert description");
            int idstatus = data.getIntExtra("Choose the status",-1);

            Action action = new Action(title,desc,idstatus);
            actionViewModel.insert(action);

            Toast.makeText(this,"A task has been added to your agenda",Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this,"The creation of the task is canceled",Toast.LENGTH_SHORT).show();
        }
    }
}
