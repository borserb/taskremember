package com.example.admin.taskremember;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PriorityDialogFragmenManager extends DialogFragment {
    public static final String TAG = "PriorityDialogFragmenManager";
    IPriorityDialogListner iPriorityDialogListner;

    @Override
    public void onAttach(Context context) {
        if (context instanceof Activity) {
            iPriorityDialogListner = ((IPriorityDialogListner) context);
            if (context instanceof IPriorityDialogListner) {
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
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.dialog_fragment);
        final TextView textView1 = (TextView) view.findViewById(R.id.bt_choose_priority_1);
        final TextView textView2 = (TextView) view.findViewById(R.id.bt_choose_priority_2);
        final TextView textView3 = (TextView) view.findViewById(R.id.bt_choose_priority_3);
        final TextView textView4 = (TextView) view.findViewById(R.id.bt_choose_priority_4);
        final TextView textViewCancel = (TextView) view.findViewById(R.id.bt_cancel);


        View.OnClickListener onClickListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_cancel:
                        /*Toast.makeText(getContext(), "Выбор приоритета " + "отмена", Toast.LENGTH_SHORT).show();*/
                        /*iPriorityDialogListner.onPriorityChoose(0);*/
                        break;
                    case R.id.bt_choose_priority_1:
                        /*Toast.makeText(getContext(), "Выбор приоритета " + "1", Toast.LENGTH_SHORT).show();*/
                        iPriorityDialogListner.onPriorityChoose(R.color.orang_red_1);
                        break;
                    case R.id.bt_choose_priority_2:
                        /*Toast.makeText(getContext(), "Выбор приоритета " + "2", Toast.LENGTH_SHORT).show();*/
                        iPriorityDialogListner.onPriorityChoose(R.color.sun_yellow_2);
                        break;
                    case R.id.bt_choose_priority_3:
                        /*Toast.makeText(getContext(), "Выбор приоритета " + "3", Toast.LENGTH_SHORT).show();*/
                        iPriorityDialogListner.onPriorityChoose(R.color.viridian_3);
                        break;
                    case R.id.bt_choose_priority_4:
                        /*Toast.makeText(getContext(), "Выбор приоритета " + "4", Toast.LENGTH_SHORT).show();*/
                        iPriorityDialogListner.onPriorityChoose(R.color.clear_blue_4);
                        break;
                }
            }
        };

        textView1.setOnClickListener(onClickListner);
        textView2.setOnClickListener(onClickListner);
        textView3.setOnClickListener(onClickListner);
        textView4.setOnClickListener(onClickListner);
        textViewCancel.setOnClickListener(onClickListner);


        super.onViewCreated(view, savedInstanceState);
    }

    public static PriorityDialogFragmenManager newInstance() {
        PriorityDialogFragmenManager fragment = new PriorityDialogFragmenManager();
        return fragment;
    }
}
