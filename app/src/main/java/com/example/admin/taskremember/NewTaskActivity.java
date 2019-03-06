package com.example.admin.taskremember;

import android.content.Intent;
import android.graphics.Color;
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



public class NewTaskActivity extends AppCompatActivity {

    EditText editText;
    ImageButton imageButton;
    public static final String NEW_TASK_KEY = "102";


    List<Task> tasks = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        imageButton = findViewById(R.id.imageButton);
        editText = findViewById(R.id.new_task_et);
        imageButton.setEnabled(false);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewTaskActivity.this, "New task activity click", Toast.LENGTH_LONG).show();
                Intent data = new Intent();
                Task value = new Task(editText.getText().toString(), Color.RED );
                data.putExtra(NEW_TASK_KEY, value);
                NewTaskActivity.this.setResult(RESULT_OK, data);
                NewTaskActivity.this.finish();

            }
        });


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                imageButton.setEnabled(true);

            }
        });




/*        int color;
        color = Color.RED;
        for(int i =0;i<50;i++){
            tasks.add(new Task("Name " + i , color ));
        }*/




        RecyclerView rv = findViewById(R.id.recycleViewId);
        RecycleViewAdpter adapter = new RecycleViewAdpter(this, tasks);
        rv.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                linearLayoutManager.getOrientation());
        rv.addItemDecoration(mDividerItemDecoration);
        Log.i("RecycleViewLearn" , "адаптер и менеджер оозданы newTask");





    }
}
