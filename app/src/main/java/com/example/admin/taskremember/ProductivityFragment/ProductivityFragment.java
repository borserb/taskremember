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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.taskremember.ProductivityFragment.CustomView.DaysOfWeek;
import com.example.admin.taskremember.R;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

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


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentWeek = 0;
                int currentYear = 0;
                int dayOfWeekInt = 0;


               Long days[] = new Long[7];
               days = beginingOfDaysCurrentWeek();

                for (int i=0; i<days.length; i++){
                    Log.i("MYtetewtweet", "day #" + i+"="+days[i].toString() );
                }
            }
        });




/*        tvQuntity = view.findViewById(R.id.tv_tasks_end);
        FragmentActivity activity = (MainActivity) getActivity();

        sharedPreferences = activity.getSharedPreferences(TasksFragment.APP_PREFERENCES, activity.MODE_PRIVATE);
        tasksEnd = sharedPreferences.getInt(TasksFragment.APP_PREFERENCES_TASKS_END, 0);
        tvQuntity.setText("задач завершенно " + tasksEnd);

        listener = getListener();
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);*/


    }

    private Long[] beginingOfDaysCurrentWeek() {
        int dayOfWeekInt;
        int currentWeek;
        int currentYear;Calendar targetCalendar = Calendar.getInstance();

        Calendar firsrDayOfWeek = targetCalendar;
        Log.i("MYtetewtweet", "curent time =" + Long.toString(targetCalendar.getTimeInMillis()));

        dayOfWeekInt = targetCalendar.get(Calendar.DAY_OF_WEEK);
        currentWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        currentYear = targetCalendar.get(Calendar.YEAR);

        String input;
        String format;

        if (dayOfWeekInt == 1) {
            input = Integer.toString(currentYear) + (currentWeek - 1) + "Monday";
            format = "yyyywwEEEE";
        } else {
            input = Integer.toString(currentYear) + (currentWeek) + "Monday";
            format = "yyyywwEEEE";
        }

        SimpleDateFormat df = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = df.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        firsrDayOfWeek.setTime(date);
        Long firstDayOfWeekInMs = firsrDayOfWeek.getTimeInMillis();

        /*Log.i("MYtetewtweet", "first day of week =" + Long.toString(firstDayOfWeekInMs) );*/

        Long begingOfDays [] = new Long[7];
        begingOfDays[0]=firstDayOfWeekInMs;

        for (int i=1; i<begingOfDays.length; i++){
            begingOfDays[i]=firstDayOfWeekInMs+(i*86400000);
            /*Log.i("MYtetewtweet", "day #" + i+"="+begingOfDays[i].toString() );*/
        }
        return begingOfDays;
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
