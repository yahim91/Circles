package com.circles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder surfaceHolder;
    private boolean runFlag;
    private Thread thread;
    Circle circle = new Circle(50, 50, 100);
    public GameSurface(Context context) {
        super(context);
        runFlag = true;
        getHolder().addCallback(this);
        thread = new Thread(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        try {
            runFlag = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);
        circle.draw(canvas);
    }

    @Override
    public void run() {
        Canvas canvas = null;
        while(runFlag) {
            canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                this.onDraw(canvas);
            }
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.d("Gestures", "SurfaceView");
        return super.onTouchEvent(event);
    }
}
