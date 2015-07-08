package com.blueweird.bubblesparty.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blueweird.bubblesparty.controller.GameController;

/**
 * Created by blueweird on 12/05/2015.
 */
public class UserInterface extends SurfaceView {
    private GameController controller;

    private String strScore;
    private Bitmap bmpBonus;
    private Bitmap bmpMalus;
    private Bitmap bmpPause;
    private Paint paint;
    private float uiHeight;

    public UserInterface(Context context, GameController ctrl) {
        super(context);

        controller = ctrl;

        uiHeight = (int) (0.1 * context.getResources().getDisplayMetrics().heightPixels);
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) uiHeight));

        strScore = new String();
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(80);

        // The callback for the holder to lock the surface
        getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                controller.pauseGame();
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                controller.playGame();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
    }

    public void updateBmpPause() {
        if (controller.isPaused())
            bmpPause = BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_media_play);
        else
            bmpPause = BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_media_pause);
        float scale = uiHeight / bmpPause.getHeight();
        bmpPause = rescaleBitmap(bmpPause, scale, scale);
    }

    public void setScore(String newScore) {
        strScore = newScore;
    }

    public void setBonus(int res) {
        bmpBonus = BitmapFactory.decodeResource(getResources(), res);
        float scale = uiHeight / bmpBonus.getHeight();
        bmpBonus = rescaleBitmap(bmpBonus, scale, scale);
    }

    public void setMalus(int res) {
        bmpMalus = BitmapFactory.decodeResource(getResources(), res);
        float scale = uiHeight / bmpMalus.getHeight();
        bmpMalus = rescaleBitmap(bmpMalus, scale, scale);
    }

    private Bitmap rescaleBitmap(Bitmap bmp, float sx, float sy) {
        return Bitmap.createScaledBitmap(bmp, (int) (sx * bmp.getWidth()), (int) (sy * bmp.getHeight()), false);
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.LTGRAY);

        paint.setTextSize(40);
        canvas.drawBitmap(bmpBonus, 0, 0, null);
        canvas.drawText("Bonus", 0, paint.getTextSize(), paint);

        canvas.drawBitmap(bmpMalus, bmpBonus.getWidth(), 0, null);
        canvas.drawText("Malus", bmpBonus.getWidth(), paint.getTextSize(), paint);

        canvas.drawBitmap(bmpPause, getWidth() - bmpPause.getWidth(), 0, null);

        paint.setTextSize(80);
        canvas.drawText(strScore, getWidth() / 2, paint.getTextSize(), paint);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            if (x > getWidth() - bmpPause.getWidth()) {
                controller.togglePauseButton();
                updateBmpPause();
                controller.draw();
            }
        }
        return true;
    }
}
