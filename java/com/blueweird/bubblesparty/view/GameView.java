package com.blueweird.bubblesparty.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;

import com.blueweird.bubblesparty.R;
import com.blueweird.bubblesparty.controller.GameController;
import com.blueweird.bubblesparty.model.Bubble;

/**
 * Created by blueweird on 06/05/2015.
 */
public class GameView extends SurfaceView {
    private Bitmap background;
    private Rect bgRect;
    private GameController controller;

    public GameView(Context context, GameController gc) {
        super(context);
        controller = gc;

        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        // Set the background
        int screen_width = context.getResources().getDisplayMetrics().widthPixels;
        int screen_height = context.getResources().getDisplayMetrics().heightPixels;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        bgRect = new Rect(0, 0, screen_width, screen_height);

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

    public void draw(Canvas canvas) {
        canvas.drawBitmap(background, null, bgRect, null);

        for(Bubble bubble: controller.getGameModel().getBubbles()) {
            bubble.getSprite().draw(canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        controller.onTouchEvent(event);
        return true;
    }
}
