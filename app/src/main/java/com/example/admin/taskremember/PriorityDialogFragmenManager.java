package com.example.admin.taskremember;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class PriorityDialogFragmenManager extends DialogFragment {
    public static final String TAG = "PriorityDialogFragmenManager";
    IPriorityDialogListner iPriorityDialogListner;

    @Override
    public void onAttach(Context context) {
        if (context instanceof Activity) {
            iPriorityDialogListner = ((IPriorityDialogListner) context);
            if (context instanceof IPriorityDialogListner){
                iPriorityDialogListner = ((IPriorityDialogListner) context);
            } else {
                throw new UnsupportedOperationException("Активити должно реализововать интерфейс IPriorityDialogListner");
            }
        }

        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chose_priority, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button button = (Button) view.findViewById(R.id.bt_choose_priority_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Выбор приоритета", Toast.LENGTH_SHORT).show();
                iPriorityDialogListner.onPriorityChoose(10);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    public static PriorityDialogFragmenManager newInstance() {
        PriorityDialogFragmenManager fragment = new PriorityDialogFragmenManager();
        return fragment;
    }
}
