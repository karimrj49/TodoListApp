package com.example.todolist1.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolist1.Entities.Status;
import com.example.todolist1.R;
import com.example.todolist1.RecyclerView.StatusRecyclerViewAdapter;
import com.example.todolist1.ViewModel.StatusViewModel;

import java.util.List;

public class AddActionActivity extends AppCompatActivity implements StatusRecyclerViewAdapter.OnStatusListener {

    private EditText edititle , editdesc;
    private Button buttonsave;
    private StatusViewModel statusViewModel;
    private RecyclerView recyclerView;
    private StatusRecyclerViewAdapter adapter;
    private RecyclerView.ViewHolder viewHolder;
    int rpos = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_action);
        //AddActionActivity.this.setTitle(R.string.add_task);
        getSupportActionBar().setTitle(R.string.add_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        adapter = new StatusRecyclerViewAdapter(this,this);
        recyclerView.setAdapter(adapter);
        statusViewModel = ViewModelProviders.of(this).get(StatusViewModel.class);

        statusViewModel.getAllStatus().observe(this, new Observer<List<Status>>() {
            @Override
            public void onChanged(List<Status> status) {
                // Update RecyclerView
                adapter.setList(status);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addaction,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add :
                add();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void add (){
        edititle = (EditText) findViewById(R.id.action_title);
        editdesc = (EditText) findViewById(R.id.action_description);
        String title = edititle.getText().toString();
        String desc = editdesc.getText().toString();

        Intent data = new Intent();
        if(title.trim().isEmpty() || desc.trim().isEmpty()){
            setResult(RESULT_CANCELED,data);
            Toast.makeText(getApplicationContext(),"Please insert a title and description",Toast.LENGTH_SHORT).show();
        }else if (rpos==-1){
            setResult(RESULT_CANCELED,data);
            Toast.makeText(getApplicationContext(),"Please choose a status",Toast.LENGTH_SHORT).show();
        }
        else {
            data.putExtra("Insert title", title);
            data.putExtra("Insert description", desc);
            data.putExtra("Choose the status",rpos);
            setResult(RESULT_OK, data);
            finish();
        }
    }
    @Override
    public int onStatusClick(int position) {

        String title = adapter.getStatusTitle(position);
        rpos = position+1;
        Toast.makeText(this,"You have selected the status"+title+" ",Toast.LENGTH_SHORT).show();
        return rpos;

    }
}
