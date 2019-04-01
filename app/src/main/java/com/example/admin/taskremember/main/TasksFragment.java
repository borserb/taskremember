package com.example.admin.taskremember.main;

import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.taskremember.R;
import com.example.admin.taskremember.database.AppDatabase;
import com.example.admin.taskremember.database.Task;

import java.util.ArrayList;
import java.util.List;

public class TasksFragment extends Fragment {
    public static final String APP_PREFERENCES = "my_shared_pref";
    public static final String APP_PREFERENCES_TASKS_END = "tasks_end";


    private SwipeRefreshLayout swipeRefreshLayout;

    private List <Task> tasks = new ArrayList();
    private ConstraintLayout backgorund;
    private RecycleViewAdpter adapter;
    private SharedPreferences sharedPreferences;


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
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentActivity activity = getActivity();

        sharedPreferences = activity.getSharedPreferences(APP_PREFERENCES, activity.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        final RecyclerView rv = view.findViewById(R.id.recycleViewMainActivytyId);
        adapter = new RecycleViewAdpter(getContext(), tasks);
        backgorund = view.findViewById(R.id.backgroun_off);
        backgorund.setVisibility(View.INVISIBLE);


        rv.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                linearLayoutManager.getOrientation());
        rv.addItemDecoration(mDividerItemDecoration);


        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadAllTasksFromDB();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });


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

                    tasksIsEmpty();

                }
            }
        }).attachToRecyclerView(rv);


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
        loadAllTasksFromDB();
    }

    private void loadAllTasksFromDB() {
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

