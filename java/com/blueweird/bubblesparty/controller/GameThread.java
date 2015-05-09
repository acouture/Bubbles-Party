package com.blueweird.bubblesparty.controller;

import com.blueweird.bubblesparty.model.Bubble;
import com.blueweird.bubblesparty.model.GameModel;
import com.blueweird.bubblesparty.view.GameView;

import java.util.Random;

/**
 * Created by blueweird on 09/05/2015.
 */
public class GameThread extends MainLoopThread {
    Random rnd;
    int spawnRate;
    int spawnRateInc;

    public GameThread(GameModel gameModel, GameView gameView) {
        super(gameModel, gameView);
        rnd = new Random();
        spawnRate = 0;
        spawnRateInc = 10;
    }

    @Override
    protected void loop() {
        if (rnd.nextInt(1000) < spawnRate) {
            // Create a new bubble
            createBubble(rnd.nextInt(4));
            spawnRate = 0;
        }
        spawnRate += spawnRateInc;
        gameModel.update();
    }

    private boolean createBubble(int bubble_color) {
        Bubble bubble = new Bubble(bubble_color, gameView);
        gameModel.addBubble(bubble);
        gameView.addSprite(bubble.getSprite());
        return true;
    }
}
