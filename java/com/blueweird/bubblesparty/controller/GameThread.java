package com.blueweird.bubblesparty.controller;

import com.blueweird.bubblesparty.R;
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
        if(rnd.nextInt(1000) < spawnRate) {
            // Create a new bubble
            createBubble(rnd.nextInt(4));
            spawnRate = 0;
        }
        spawnRate += spawnRateInc;
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

