package com.blueweird.bubblesparty.controller;

import android.graphics.Canvas;

import com.blueweird.bubblesparty.R;
import com.blueweird.bubblesparty.model.GameModel;
import com.blueweird.bubblesparty.view.GameView;

import java.util.Random;

/**
 * Created by blueweird on 06/05/2015.
 */
public class GameLoopThread extends Thread {
    private static final long FPS = 20;
    private boolean running = false;

    private GameModel gameModel;
    private GameView gameView;

    public GameLoopThread(GameModel model, GameView view) {
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

        //
        Random rnd = new Random();
        long nextSpawnTime = System.currentTimeMillis() + rnd.nextLong() % 2000;

        while (running) {

            Canvas c = null;
            startTime = System.currentTimeMillis();

            // Create a new bubble
            if(startTime > nextSpawnTime) {
                createBubble(rnd.nextInt(4));

                // TODO: Improve the random generation of bubbles
                nextSpawnTime = System.currentTimeMillis()+ 500 + rnd.nextLong() % 1500;
            }

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

    private boolean createBubble(int bubble_color) {
        switch(bubble_color) {
            case 0:
                gameView.addSprite(R.drawable.bubble_blue);
                break;
            case 1:
                gameView.addSprite(R.drawable.bubble_green);
                break;
            case 2:
                gameView.addSprite(R.drawable.bubble_red);
                break;
            case 3:
                gameView.addSprite(R.drawable.bubble_yellow);
                break;
            default:
                return false;
        }
        gameModel.addBubble(bubble_color);
        return true;
    }
}
