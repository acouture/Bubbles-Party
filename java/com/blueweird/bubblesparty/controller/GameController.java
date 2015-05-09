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

//    public void setRunning(boolean run) {
//        running = run;
//    }

    public void spriteTouched(int num) {
        gameView.removeSprite(num);
        gameModel.removeBubble(num);
        gameModel.incScore(1);
    }

    public GameView getGameView() {
        return gameView;
    }
}
