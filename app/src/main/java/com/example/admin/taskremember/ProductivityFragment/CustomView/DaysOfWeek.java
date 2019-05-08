package com.example.admin.taskremember.ProductivityFragment.CustomView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class DaysOfWeek extends ViewGroup {
    private final List <String> days = Arrays.asList("пн", "вт", "ср", "чт", "пт", "сб", "вс");
    private int weidth = 0;
    private int height = 0;
    private int heightForSecondView;

    public DaysOfWeek(Context context) {
        super(context);
        init();
        /*setBackgroundResource(R.color.dark_grey);*/
    }

    private void init() {

        GraphView graphView = new GraphView(getContext());

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
        /* получаем все свободное место для контейнера, высоту определяем как половину всего экрана*/
        int measurewidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureheight = MeasureSpec.getSize(heightMeasureSpec) / 2;

        if (getChildCount() > 0) {
        /*    находим две из 8и view, 0я - это график, и 1я это понедельник
                    они нам нужны для подсчета итоговой ширины и высоты всего контейнера*/
            View childView = getChildAt(0);



          /*  задаем размер графика:
          ширина равна любому рамеру в предела родителя, если больше родителя уменшаем (measurewidth, MeasureSpec.AT_MOST)
          высота равна высота родителя минус высота второй View ведь для нее еще нужно оставить место, в случае большого child его нужно уменьшить*/
            childView.measure(MeasureSpec.makeMeasureSpec(measurewidth, MeasureSpec.AT_MOST),
                    MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(measureheight - heightForSecondView), MeasureSpec.AT_MOST));

            View childView1 = getChildAt(1);
            heightForSecondView = childView1.getMeasuredHeight();//переменная высоты TextView понедельника

            for (int i = 1; i < 8; i++) {
                /*задаем размере ViewText, пн, вт и тд*/
                View childDays = getChildAt(i);
                childDays.measure(MeasureSpec.makeMeasureSpec(measurewidth, MeasureSpec.AT_MOST),
                        MeasureSpec.makeMeasureSpec(measureheight, MeasureSpec.AT_MOST));

            }



            weidth = measurewidth; // итоговая ширина контейнера

            height = childView.getMeasuredHeight() + heightForSecondView; //итоговая высота контейнера = сумма высоты двух view
        }

        setMeasuredDimension(weidth, height);
    }


    /*метод позиционирования childView в нашей группе*/
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        /*первым размещаем график, он занимае стандартную позицию в грппе*/
        View view0 =(GraphView) getChildAt(0);
        view0.layout(l, t, r, b);

        int xCoordinat [] = new int[7];
        xCoordinat = ((GraphView) view0).getDaysOfWeekCoodrinatWidth();



        /*т.к. дней недели 7ь делим общую ширину на 7ь и находим через какое растояние необходимо размещать вью */
        /*int space = weidth / 8;*/
        /*в цикле с 1го по 7 размещаем вью, 0й это график его не трогаем*/
        int width;
        View view;
        for (int i = 0; i < 7; i++) {
             view = getChildAt(i+1);
            /*l-лево t-вверху r-справа b-внизу
                    в цикле выставлем текст вью внизу в промежутке высоты от конца графика до конца вью групп*/


            width = view.getMeasuredWidth()/2;// находим средину текствью по ширене

            view.layout(xCoordinat[i]-width, height - heightForSecondView, r, height);//распологаем текствью на экране
        }

    }
}
