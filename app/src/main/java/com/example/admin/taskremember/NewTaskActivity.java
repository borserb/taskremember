package com.example.admin.taskremember;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class NewTaskActivity extends AppCompatActivity implements IPriorityDialogListner {

    public static final String NEW_TASK_KEY = "102";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        if (savedInstanceState == null) {
            FragmentManager supportFragmentManagerrtFM = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = supportFragmentManagerrtFM.beginTransaction();
            fragmentTransaction.add(R.id.flContainer, NewTaskFragmen.newInstance());
            fragmentTransaction.commit();

        }

    }

    @Override
    public void onPriorityChoose(int priority) {
        Toast.makeText(this, "Приоритет пришел, значение = "+priority, Toast.LENGTH_SHORT).show();

    }
}
