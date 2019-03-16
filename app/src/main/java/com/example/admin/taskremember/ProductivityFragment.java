package com.example.admin.taskremember;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProductivityFragment extends Fragment implements IMainActivityListner {
    final static public String TAG="ProductivityFragment";
    TextView tvQuntity;
    SharedPreferences sharedPreferences;

    @Override
    public void onMainQuantitychange() {
        FragmentActivity activity =(MainActivity) getActivity();
        tvQuntity.setText("задач завершенно "+ ((MainActivity) activity).quantityEnd);
    }


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
        return inflater.inflate(R.layout.fragment_productivity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvQuntity = view.findViewById(R.id.tv_tasks_end);
        tvQuntity.setText("12121212");
        FragmentActivity activity =(MainActivity) getActivity();
        tvQuntity.setText("задач завершенно "+ ((MainActivity) activity).quantityEnd);


    }

    public void change() {
        FragmentActivity activity =(MainActivity) getActivity();
        tvQuntity.setText("задач завершенно "+ ((MainActivity) activity).quantityEnd);
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
