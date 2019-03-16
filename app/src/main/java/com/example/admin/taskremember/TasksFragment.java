package com.example.admin.taskremember;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
    public static final String APP_PREFERENCES = "my_shared_pref";
    public static final String APP_PREFERENCES_TASKS_END = "tasks_end";
    IQuantityTasksEndListner iQuantityTasksEndListner;


    ImageButton imageButton;
    public final static int ACTIVITY_CODE = 101;
    List <Task> tasks = new ArrayList();
    ConstraintLayout backgorund;
    RecycleViewAdpter adapter;
    SharedPreferences sharedPreferences;


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


        FragmentActivity activity = getActivity();
        iQuantityTasksEndListner = (IQuantityTasksEndListner) activity;

        sharedPreferences = activity.getSharedPreferences(APP_PREFERENCES, activity.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        final RecyclerView rv = view.findViewById(R.id.recycleViewMainActivytyId);
        adapter = new RecycleViewAdpter(getContext(), tasks);
        backgorund = (ConstraintLayout) view.findViewById(R.id.backgroun_off);
        backgorund.setVisibility(View.INVISIBLE);


        rv.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                linearLayoutManager.getOrientation());
        rv.addItemDecoration(mDividerItemDecoration);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final FragmentActivity activity = getActivity();

                if (activity != null) {

                    final AppDatabase db = Room.databaseBuilder(activity, AppDatabase.class, "databse-name").allowMainThreadQueries().build();
                    int position = viewHolder.getPosition();
                    db.taskDao().del(tasks.get(position));
                    tasks.remove(tasks.get(position));
                    adapter.notifyItemRemoved(position);
                    int anInt = sharedPreferences.getInt(APP_PREFERENCES_TASKS_END, 0);
                    anInt++;
                    editor.putInt(APP_PREFERENCES_TASKS_END, anInt);
                    editor.apply();
                    iQuantityTasksEndListner.onQuantityTasksChange(anInt);

                }
            }
        }).attachToRecyclerView(rv);


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

