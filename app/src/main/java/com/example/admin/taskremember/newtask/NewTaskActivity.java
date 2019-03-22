package com.example.admin.taskremember.newtask;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.taskremember.R;


public class NewTaskActivity extends AppCompatActivity implements IPriorityDialogListner {

    public static final String NEW_TASK_KEY = "102";
    private int priorityInActivity = R.color.orang_red_1;


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
        Toast.makeText(this, "Приоритет пришел, значение = " + priority, Toast.LENGTH_SHORT).show();
        priorityInActivity = priority;
        TextView textView = findViewById(R.id.tv_priority);
        textView.setTextColor(getResources().getColor(priorityInActivity));
    }

    public int getPriorityInAtivity() {
        return priorityInActivity;
    }


}
