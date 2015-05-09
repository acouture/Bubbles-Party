package com.blueweird.bubblesparty.controller;

import android.graphics.Canvas;

import com.blueweird.bubblesparty.model.GameModel;
import com.blueweird.bubblesparty.view.GameView;

import java.util.Random;

/**
 * Created by blueweird on 06/05/2015.
 */
public abstract class MainLoopThread extends Thread {
    protected static final long FPS = 20;
    protected boolean running = false;

    protected GameModel gameModel;
    protected GameView gameView;

    public MainLoopThread(GameModel model, GameView view) {
        gameModel = model;
        gameView = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        while (running) {
            startTime = System.currentTimeMillis();
            Canvas c = null;

            // Does what the game has to do
            loop();

            // Repaint the view with the canvas
            try {
                c = gameView.getHolder().lockCanvas();
                synchronized (gameView.getHolder()) {
                    gameView.draw(c);
                }
            } finally {
                if (c != null) {
                    gameView.getHolder().unlockCanvasAndPost(c);
                }
            }

            // Sleep until the next frame to get the right FPS
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {}
        }
    }

    protected abstract void loop();
}
