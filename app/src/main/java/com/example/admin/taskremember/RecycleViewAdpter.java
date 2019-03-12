package com.example.admin.taskremember;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class RecycleViewAdpter extends RecyclerView.Adapter <ViewHolder> {
    Context context;
    List <Task> taskList = new ArrayList <>();


    public RecycleViewAdpter(Context context, List <Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());


        final View view = inflater.inflate(R.layout.recycle_conteiner, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "RecycleClick" + holder.getAdapterPosition(),  Toast.LENGTH_LONG).show();
            }
        });
        Log.i("RecycleViewLearn", "onCrHolder in RecycleViewAdpter");

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setTask(taskList.get(i));
        Log.i("RecycleViewLearn", "onBindHolder in RecycleViewAdpter");
    }

    @Override
    public int getItemCount() {
        Log.i("RecycleViewLearn", "getCount in RecycleViewAdpter");
        return taskList.size();
    }

    public void setTaskList (List<Task> tasks){
        this.taskList=tasks;
        notifyDataSetChanged();
    }
}
