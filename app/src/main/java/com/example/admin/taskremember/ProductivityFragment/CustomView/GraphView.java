package com.example.admin.taskremember.ProductivityFragment.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import com.example.admin.taskremember.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GraphView extends View {
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
    private int daysOfWeekCoodrinatWidth[] = new int[7]; //массив в который сохраняем координаты 7и текствью по ширене что бы потом крассиво раставить их под граффиком

    /* Конструктор без атрибутов, значит нельзя использовать xml!*/
    public GraphView(Context context) {
        super(context);
    }

    public int[] getDaysOfWeekCoodrinatWidth() {
        return daysOfWeekCoodrinatWidth;
    }


    /*        Этот метод означает, что наш Custom View-компонент находится на стадии определения собственного размера.
            Это очень важный метод, так как в большинстве случаев вам нужно определить специфичный размер для вашего
            View-компонента, чтобы поместиться на вашем макете.*/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureHight = MeasureSpec.getSize(heightMeasureSpec);
        measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(measureWidth, measureHight);//обязательный метод возвращает размер вью после пересчета

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
        paintLines.setStrokeWidth(getPx(1));
        paintLines.setAlpha(128); // прозрачность от 0 до 255
        paintLines.setStyle(Paint.Style.STROKE);

        canvas.drawPath(graphPath, paint);
        canvas.drawPath(graphPathLiners, paintLines);
        paintLines.setStrokeWidth(getPx(3));
        canvas.drawPath(graphPathMainLine, paintLines);
        canvas.drawPath(graphPathCircles, paintCircles);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    private void init() {
        Integer max = Collections.max(taskDonePeDay); //максимально значение вып. заданий за неделю
        hightCharDevision = measureHight / max; //находим кратность высоты
        widthCharDevision = (measureWidth - getPx(50)) / 6; //находим кратность ширины
        int startWidth = getPx(25); //стартовая точка по ширине
        int xCoordinat = 0;


        graphPath = new Path();
        graphPath.moveTo(startWidth, measureHight);
        graphPath.reset();

        graphPathCircles = new Path();
        graphPathCircles.moveTo(startWidth, measureHight);
        graphPathCircles.reset();

        graphPathLiners = new Path();
        graphPathLiners.reset();

        graphPathMainLine = new Path();
        graphPathMainLine.reset();
        graphPathMainLine.moveTo(startWidth, measureHight);


        for (int i = 0; i < 7; i++) {
            Integer taskToDay = taskDonePeDay.get(i);
            xCoordinat = i * widthCharDevision + startWidth;
            daysOfWeekCoodrinatWidth[i] = xCoordinat;

            graphPath.lineTo(xCoordinat, measureHight - (taskToDay * hightCharDevision));

            graphPathMainLine.lineTo(xCoordinat, measureHight - (taskToDay * hightCharDevision));

            graphPathLiners.moveTo(xCoordinat, measureHight);
            graphPathLiners.lineTo(xCoordinat, measureHight - (taskToDay * hightCharDevision));

            graphPathCircles.addCircle(xCoordinat, measureHight - (taskToDay * hightCharDevision), 15, Path.Direction.CCW);

        }

        graphPath.lineTo(measureWidth - startWidth, measureHight);
        graphPath.lineTo(startWidth, measureHight);
        graphPath.close();

    }

    public int getPx(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        return ((int) (dp * scale + 0.5f));
    }


}
