package com.blueweird.bubblesparty.controller;

import android.content.Context;

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
    private GameLoopThread gameLoopThread;

    public GameController(Context context) {
        gameModel = new GameModel();
        gameView = new GameView(context, this);
    }

    public void startThread() {
        gameLoopThread = new GameLoopThread(gameModel, gameView);
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    public void stopThread() {
        boolean retry = true;
        gameLoopThread.setRunning(false);
        while (retry) {
            try {
                gameLoopThread.join();
                retry = false;
            } catch (InterruptedException e) {}
        }
    }

//    public void setRunning(boolean run) {
//        running = run;
//    }

    public void spriteTouched(int num) {
        gameView.removeSprite(num);
        gameModel.removeBubble(num);
    }

    public GameView getGameView() {
        return gameView;
    }
}
