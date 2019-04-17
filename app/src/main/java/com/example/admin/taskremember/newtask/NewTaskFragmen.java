package com.example.admin.taskremember.newtask;


import android.app.Application;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.taskremember.App;
import com.example.admin.taskremember.database.AppDatabase;
import com.example.admin.taskremember.R;
import com.example.admin.taskremember.database.Task;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewTaskFragmen extends Fragment {

    EditText editText;
    ImageButton imageButton;
    TextView tvPriority;
    private int priprity;

    public NewTaskFragmen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragmen_new_task, container, false);
        imageButton = root.findViewById(R.id.imageButton);
        editText = root.findViewById(R.id.new_task_et);
        tvPriority = root.findViewById(R.id.tv_priority);
        imageButton.setEnabled(false);
        tvPriority.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Toast.makeText(getContext(), "Priority click", Toast.LENGTH_SHORT).show();
                                              PriorityDialogFragmenManager priorityDialogFragmenManager = PriorityDialogFragmenManager.newInstance();
                                              priorityDialogFragmenManager.show(getChildFragmentManager(), PriorityDialogFragmenManager.TAG);
                                          }
                                      }
        );

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "New task activity click", Toast.LENGTH_LONG).show();
                FragmentActivity activity = (NewTaskActivity) getActivity();
                priprity = ((NewTaskActivity) activity).getPriorityInAtivity();
                Task task = new Task(editText.getText().toString(), getResources().getColor(priprity));

                if (activity != null) {
                    Application application =(App) activity.getApplication();
                    AppDatabase db = ((App) application).getDb();
                    db.taskDao().insert(task);

                  /*  final AppDatabase db = Room.databaseBuilder(activity, AppDatabase.class, "databse-name").allowMainThreadQueries().build();
                    db.taskDao().insert(task);*/

                }

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
        return root;
    }


    public static NewTaskFragmen newInstance() {
        NewTaskFragmen fragment = new NewTaskFragmen();
        return fragment;
    }

}
