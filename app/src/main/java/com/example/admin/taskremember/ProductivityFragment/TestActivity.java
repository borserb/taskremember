package com.example.admin.taskremember.ProductivityFragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().
                    beginTransaction().add(android.R.id.content, ProductivityFragment.newInstance()).commit();
        }

    }
}
