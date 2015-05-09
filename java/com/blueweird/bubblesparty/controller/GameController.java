package com.blueweird.bubblesparty.controller;

import android.content.Context;
import android.view.MotionEvent;

import com.blueweird.bubblesparty.model.Bubble;
import com.blueweird.bubblesparty.model.GameModel;
import com.blueweird.bubblesparty.view.GameView;

/**
 * Created by blueweird on 06/05/2015.
 */
public class GameController {
    private static final long FPS = 20;
    private boolean running = false;

    private GameModel gameModel;
    private GameView gameView;
    private MainLoopThread mainLoopThread;

    public GameController(Context context) {
        gameModel = new GameModel();
        gameView = new GameView(context, this);
    }

    public void startThread() {
        mainLoopThread = new GameThread(gameModel, gameView);
        mainLoopThread.setRunning(true);
        mainLoopThread.start();
    }

    public void stopThread() {
        boolean retry = true;
        mainLoopThread.setRunning(false);
        while (retry) {
            try {
                mainLoopThread.join();
                retry = false;
            } catch (InterruptedException e) {}
        }
    }

    public void onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();

            synchronized (gameView.getHolder()) {
                for (int i = gameModel.getBubbles().size() - 1; i >= 0; i--) {
                    Bubble bubble = gameModel.getBubbles().get(i);
                    if (bubble.isCollision(x, y)) {
                        spriteTouched(i);
//                        sprites.remove(sprite);
//                        temps.add(new TempSprite(temps, this, x, y, bmpBlood));
//                        score++;
                        break;
                    }
                }
            }
        }
    }

    public void spriteTouched(int num) {
        gameView.removeSprite(num);
        gameModel.removeBubble(num);
        gameModel.incScore(1);
    }

    public GameView getGameView() {
        return gameView;
    }
}
