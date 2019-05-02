package com.example.admin.taskremember.ProductivityFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.taskremember.ProductivityFragment.CustomView.DaysOfWeek;
import com.example.admin.taskremember.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ProductivityFragment extends Fragment {
    final static public String TAG = "ProductivityFragment";
    TextView tvQuntity;
    SharedPreferences sharedPreferences;
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    int tasksEnd;

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
        /*return inflater.inflate(R.layout.fragment_productivity, container, false);*/
        return new DaysOfWeek(inflater.getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/*        tvQuntity = view.findViewById(R.id.tv_tasks_end);
        FragmentActivity activity = (MainActivity) getActivity();

        sharedPreferences = activity.getSharedPreferences(TasksFragment.APP_PREFERENCES, activity.MODE_PRIVATE);
        tasksEnd = sharedPreferences.getInt(TasksFragment.APP_PREFERENCES_TASKS_END, 0);
        tvQuntity.setText("задач завершенно " + tasksEnd);

        listener = getListener();
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);*/


    }

/*    @NonNull
    private SharedPreferences.OnSharedPreferenceChangeListener getListener() {
        return new SharedPreferences.OnSharedPreferenceChangeListener(){
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(TasksFragment.APP_PREFERENCES_TASKS_END)) {
                    tasksEnd = sharedPreferences.getInt(TasksFragment.APP_PREFERENCES_TASKS_END, 0);
                    tvQuntity.setText("задач завершенно " + tasksEnd);
                }
            }
        };
    }



    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }*/


    public static class GraphView extends View {
        private final List <Integer> taskDonePeDay = Arrays.asList(1, 20, 30, 28, 50, 60, 70);
        int measureHight = 0;
        int measureWidth = 0;
        private int hightCharDevision;
        private int widthCharDevision;
        private Path graphPath;
        private Path graphPathLiners;
        private Path graphPathCircles;
        private Path graphPathMainLine;
        private Paint paint = new Paint();
        private Paint paintLines = new Paint();
        private Paint paintCircles = new Paint();


        /* Конструктор без атрибутов, значит нельзя использовать xml!*/
        public GraphView(Context context) {
            super(context);
        }


        /*        Этот метод означает, что наш Custom View-компонент находится на стадии определения собственного размера.
                Это очень важный метод, так как в большинстве случаев вам нужно определить специфичный размер для вашего
                View-компонента, чтобы поместиться на вашем макете.*/
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            measureHight = MeasureSpec.getSize(heightMeasureSpec);
            measureWidth = MeasureSpec.getSize(widthMeasureSpec);
            setMeasuredDimension(measureWidth, measureHight);

            init();
        }


/*        Вот здесь происходит магия. Два объекта, Canvas и Paint, позволяют вам нарисовать всё что вам нужно.
        Экземпляр объекта Canvas приходит в качестве параметра для метода onDraw, и по существу отвечает
        за рисование различных фигур, в то время как объект Paint отвечает за цвет этой фигуры. Простыми словами,
        Canvas отвечает за рисование объекта, а Paint за его стилизацию. И используется он в основном везде,
        где будет линия, круг или прямоугольник.
                Метод в основном потоке - использвать по МИНИМУМ*/

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            paint.setColor(getContext().getResources().getColor(R.color.priprity_diagram));

            paintCircles.setColor(getContext().getResources().getColor(R.color.circle_lines_french_blue));

            paintLines.setColor(getContext().getResources().getColor(R.color.circle_lines_french_blue));
            paintLines.setStrokeWidth(3);
            paintLines.setStyle(Paint.Style.STROKE);

            canvas.drawPath(graphPath, paint);
            canvas.drawPath(graphPathLiners, paintLines);
            paintLines.setStrokeWidth(6);
            canvas.drawPath(graphPathMainLine, paintLines);
            canvas.drawPath(graphPathCircles, paintCircles);

        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

            super.onLayout(changed, left, top, right, bottom);
        }

        private void init() {
            graphPath = new Path();
            graphPath.reset();

            graphPathCircles = new Path();
            graphPathCircles.reset();

            graphPathLiners = new Path();
            graphPathLiners.reset();

            graphPathMainLine = new Path();
            graphPathMainLine.reset();


            Integer max = Collections.max(taskDonePeDay); //максимально значение вып. заданий за неделю
            hightCharDevision = measureHight / max; //находим кратность высоты
            widthCharDevision = measureWidth / 6; //находим кратность ширины

            for (int i = 0; i < 7; i++) {
                Integer taskToDay = taskDonePeDay.get(i);
                graphPath.lineTo(i * widthCharDevision, measureHight - (taskToDay * hightCharDevision));

                graphPathMainLine.lineTo(i * widthCharDevision, measureHight - (taskToDay * hightCharDevision));

                graphPathCircles.addCircle(i * widthCharDevision, measureHight - (taskToDay * hightCharDevision), 15, Path.Direction.CCW);

                graphPathLiners.moveTo(i * widthCharDevision, measureHight);
                graphPathLiners.lineTo(i * widthCharDevision, measureHight - (taskToDay * hightCharDevision));


            }
            /*graphPath.lineTo(widthCharDevision*6,measureHight);*/
            graphPath.lineTo(measureWidth, measureHight);
            graphPath.lineTo(0, measureHight);
            graphPathLiners.close();

        }


    }
}
