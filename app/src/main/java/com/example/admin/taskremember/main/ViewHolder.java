package com.example.admin.taskremember.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.admin.taskremember.R;
import com.example.admin.taskremember.database.Task;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    TextView textViewPoint;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tvRecycle);
        textViewPoint = itemView.findViewById(R.id.point);
        Log.i("RecycleViewLearn" , "ViewHolder in ViewHolder");
    }

    public void setTask(Task task){
        textView.setText(task.getName());
        textViewPoint.setTextColor(task.getPriprity());
        Log.i("RecycleViewLearn" , "setTask in ViewHolder");
    }
}
