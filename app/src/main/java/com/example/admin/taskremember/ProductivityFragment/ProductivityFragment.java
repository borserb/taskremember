package com.example.admin.taskremember.ProductivityFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.taskremember.ProductivityFragment.CustomView.DaysOfWeek;
import com.example.admin.taskremember.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ProductivityFragment extends Fragment {
    final static public String TAG = "ProductivityFragment";
    TextView tvQuntity;
    SharedPreferences sharedPreferences;
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    int tasksEnd;

    public ProductivityFragment() {
        // Required empty public constructor
    }

    public static ProductivityFragment newInstance() {
        ProductivityFragment fragment = new ProductivityFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*return inflater.inflate(R.layout.fragment_productivity, container, false);*/
        return new DaysOfWeek(inflater.getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/*        tvQuntity = view.findViewById(R.id.tv_tasks_end);
        FragmentActivity activity = (MainActivity) getActivity();

        sharedPreferences = activity.getSharedPreferences(TasksFragment.APP_PREFERENCES, activity.MODE_PRIVATE);
        tasksEnd = sharedPreferences.getInt(TasksFragment.APP_PREFERENCES_TASKS_END, 0);
        tvQuntity.setText("задач завершенно " + tasksEnd);

        listener = getListener();
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);*/


    }

/*    @NonNull
    private SharedPreferences.OnSharedPreferenceChangeListener getListener() {
        return new SharedPreferences.OnSharedPreferenceChangeListener(){
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(TasksFragment.APP_PREFERENCES_TASKS_END)) {
                    tasksEnd = sharedPreferences.getInt(TasksFragment.APP_PREFERENCES_TASKS_END, 0);
                    tvQuntity.setText("задач завершенно " + tasksEnd);
                }
            }
        };
    }



    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }*/


}
