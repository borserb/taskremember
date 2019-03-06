package com.example.admin.taskremember;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TasksFragment extends Fragment {
    ImageButton imageButton;
    public final static int ACTIVITY_CODE = 101;
    List<Task> tasks = new ArrayList();


    public TasksFragment() {
        // Required empty public constructor
    }

    public static TasksFragment newInstance() {
        TasksFragment fragment = new TasksFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_old, container, false);

        RecyclerView rv = view.findViewById(R.id.recycleViewMainActivytyId);
        RecycleViewAdpter adapter = new RecycleViewAdpter(getContext(), tasks);

        rv.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                linearLayoutManager.getOrientation());
        rv.addItemDecoration(mDividerItemDecoration);

        imageButton = view.findViewById(R.id.imageButtonAddNewTascActivity);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Click", Toast.LENGTH_LONG).show();
/*                Intent intent = new Intent(getContext(), NewTaskActivity.class);
                *//*startActivityForResult(intent, ACTIVITY_CODE);*/
            }
        });
        tasks.add(new Task("Name", Color.GREEN));

        return view;
    }

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_CODE && resultCode==RESULT_OK && data!=null){
            Task task;
            task =(Task) data.getParcelableExtra(NewTaskActivity.NEW_TASK_KEY);
            tasks.add(task);
            Toast.makeText(this, task.getName() , Toast.LENGTH_SHORT).show();
        }
    }*/
}

