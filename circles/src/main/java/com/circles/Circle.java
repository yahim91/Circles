package com.circles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Circle {
    private int x, y;
    private int radius;
    private Paint paint;
    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }
}
