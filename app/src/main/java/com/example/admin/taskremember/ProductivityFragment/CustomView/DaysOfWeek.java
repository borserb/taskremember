package com.example.admin.taskremember.ProductivityFragment.CustomView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.taskremember.ProductivityFragment.ProductivityFragment;

import java.util.Arrays;
import java.util.List;

public class DaysOfWeek extends ViewGroup {
    private final List<String> days = Arrays.asList("m", "t", "w", "th", "fr", "st", "sn");
    private int weidth;

    public DaysOfWeek(Context context) {
        super(context);
        init();
        /*setBackgroundResource(R.color.dark_grey);*/
    }

    private void init() {

        ProductivityFragment.GraphView graphView = new ProductivityFragment.GraphView(getContext());
        graphView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(graphView);

        for (String day : days) {
            TextView tv = new TextView(getContext());
            tv.setText(day);
            tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
            addView(tv);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measurewidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureheight = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;
        if (getChildCount() > 0) {
            View childView = getChildAt(0);
            childView.measure(MeasureSpec.makeMeasureSpec(measurewidth, MeasureSpec.AT_MOST),
                    MeasureSpec.makeMeasureSpec(measureheight, MeasureSpec.AT_MOST));
            weidth = measurewidth;
            height = childView.getMeasuredHeight();
        }

        setMeasuredDimension(weidth, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int space = weidth / getChildCount();

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.layout(space * i, t, space * (i + 1), b);
        }

    }
}
