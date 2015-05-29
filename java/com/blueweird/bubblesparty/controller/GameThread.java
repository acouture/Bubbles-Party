package com.blueweird.bubblesparty.controller;

import android.graphics.Canvas;

import com.blueweird.bubblesparty.model.Bubble;
import com.blueweird.bubblesparty.model.GameModel;
import com.blueweird.bubblesparty.view.GameView;

import java.util.Random;

/**
 * Created by blueweird on 09/05/2015.
 */
public class GameThread extends MainLoopThread {
    private GameModel gameModel;
    private GameView gameView;

    Random rnd;
    int spawnRate;
    int spawnRateInc;

    public GameThread(GameModel gameModel, GameView gameView) {
        super();
        this.gameModel = gameModel;
        this.gameView = gameView;
        rnd = new Random();
        spawnRate = 0;
        spawnRateInc = 30;
    }

    @Override
    protected void loop() {
        if (rnd.nextInt(1000) < spawnRate) {
            // Create a new bubble
            createBubble(rnd.nextInt(4));
            spawnRate = 0;
        }
        else
            spawnRate += spawnRateInc;
        gameModel.update();
    }

    @Override
    protected void draw() {
        Canvas c = null;
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
    }
    private boolean createBubble(int bubble_color) {
        Bubble bubble = new Bubble(bubble_color, gameView);
        gameModel.addBubble(bubble);
        gameView.addSprite(bubble.getSprite());
        return true;
    }
}
