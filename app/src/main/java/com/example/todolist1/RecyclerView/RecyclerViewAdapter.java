package com.example.todolist1.RecyclerView;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist1.Entities.Action;
import com.example.todolist1.Entities.Status;
import com.example.todolist1.R;
import com.example.todolist1.ViewModel.StatusViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder> {
    private List<Action>actions = new ArrayList<>();
    //private List<Status>statuses = new ArrayList<>();
    private Context context;
    StatusViewModel statusViewModel;
    private final LayoutInflater layoutInflater;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        layoutInflater =LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = layoutInflater.inflate(R.layout.singel_row,parent,false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            //try{

                holder.actiontitle.setText(actions.get(position).getTitle());
                holder.actiondescription.setText(actions.get(position).getDescription());
                int t = actions.get(position).getIdstatus();
                String t3 = "";
                try {
                    t3 = statusViewModel.getStatusById(t);

                }catch (Exception e){
                   e.printStackTrace();
                }
                 holder.statustitle.setText(t3);


    }

    @Override
    public int getItemCount() {
        return actions.size();
    }

    public void setList(List<Action>actions){
        this.actions = actions;
        notifyDataSetChanged();
    }

    public Action getAction(int position){
        return actions.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView actiontitle,actiondescription,statustitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            statustitle = itemView.findViewById(R.id.sid);
            actiontitle = itemView.findViewById(R.id.actiontitle);
            actiondescription = itemView.findViewById(R.id.actiondescription);
        }
    }
}
