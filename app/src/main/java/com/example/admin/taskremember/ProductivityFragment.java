package com.example.admin.taskremember;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ProductivityFragment extends Fragment {

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

}
