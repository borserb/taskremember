package com.example.admin.taskremember;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class TasksFragment extends Fragment {
    ImageButton imageButton;
    public final static int ACTIVITY_CODE = 101;
    List <Task> tasks = new ArrayList();
    ConstraintLayout backgorund;
    RecycleViewAdpter adapter;


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
        adapter = new RecycleViewAdpter(getContext(), tasks);
        backgorund = (ConstraintLayout) view.findViewById(R.id.backgroun_off);
        backgorund.setVisibility(View.INVISIBLE);


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
                Intent intent = new Intent(getContext(), NewTaskActivity.class);
                startActivityForResult(intent, ACTIVITY_CODE);
            }
        });


        return view;
    }





    private void tasksIsEmpty() {
        if (tasks.isEmpty()) {
            backgorund.setVisibility(View.VISIBLE);
        } else {
            backgorund.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        FragmentActivity activity = getActivity();

        if (activity != null) {

            final AppDatabase db = Room.databaseBuilder(activity, AppDatabase.class, "databse-name").allowMainThreadQueries().build();
            db.taskDao();
            this.tasks = db.taskDao().getAll();
            adapter.setTaskList(tasks);
            tasksIsEmpty();

        }

    }
}

