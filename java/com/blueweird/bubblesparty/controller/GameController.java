package com.blueweird.bubblesparty.controller;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blueweird.bubblesparty.model.Bubble;
import com.blueweird.bubblesparty.model.GameModel;
import com.blueweird.bubblesparty.view.GameUI;
import com.blueweird.bubblesparty.view.GameView;

/**
 * Created by blueweird on 06/05/2015.
 */
public class GameController {
    private LinearLayout layout;

    private GameModel gameModel;
    private GameView gameView;
    private GameUI userInterface;
    private MainLoopThread mainLoopThread;

    public GameController(Context context) {
        gameModel = new GameModel();
        gameView = new GameView(context, this);
        userInterface = new GameUI(context);

        layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        int uiSize = (int) (0.1 * context.getResources().getDisplayMetrics().heightPixels);

        userInterface.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, uiSize));
        gameView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        layout.addView(userInterface);
        layout.addView(gameView);

        userInterface.setScore(gameModel.getScore().toString());
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
        userInterface.setScore(gameModel.getScore().toString());
    }

    public GameView getGameView() {
        return gameView;
    }

    public LinearLayout getLayout() {
        return layout;
    }
}
