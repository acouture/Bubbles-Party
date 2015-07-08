package com.blueweird.bubblesparty.controller;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.blueweird.bubblesparty.model.Bubble;
import com.blueweird.bubblesparty.model.GameModel;
import com.blueweird.bubblesparty.view.UserInterface;
import com.blueweird.bubblesparty.view.GameView;

import java.util.Random;

/**
 * Created by blueweird on 06/05/2015.
 */
public class GameController extends MainLoopThread {
    private LinearLayout layout;

    private GameModel gameModel;
    private GameView gameView;
    private UserInterface userInterface;

    private Random rnd;
    private int spawnRate;
    private int spawnRateInc;
    private int time;

    public GameController(Context context, GameModel gm) {
        super();
        gameModel = gm;
        gameView = new GameView(context, this);
        userInterface = new UserInterface(context, this);

        paused = gameModel.isPaused();
        System.out.println("Start controller with pause = " + paused);

        layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(userInterface);
        layout.addView(gameView);

        rnd = new Random();
        spawnRate = 0;
        spawnRateInc = 30;
        time = 0;

        userInterface.setScore(gameModel.getScore().toString());
        userInterface.updateBmpPause();
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

        if (time % (30 * FPS) == 0) {
            gameModel.setMalusColor(rnd.nextInt(4));
            userInterface.setMalus(Bubble.colorToResource(gameModel.getMalusColor()));
            time = 0;
        }
        if (time % (10 * FPS) == 0) {
            while(gameModel.setBonusColor(rnd.nextInt(4)) == gameModel.getMalusColor());
            userInterface.setBonus(Bubble.colorToResource(gameModel.getBonusColor()));
        }
        gameModel.removeDeadBubbles();
        time ++;
    }

    @Override
    public void draw() {
        Canvas c = null;
        // Repaint the view with the canvas
        try {
            c = gameView.getHolder().lockCanvas();
            // TODO: c == null au lancement du thread, vérification avant draw() ??
            if(c != null)
                synchronized (gameView.getHolder()) {
                    gameView.draw(c);
                }
        } finally {
            if (c != null) {
                gameView.getHolder().unlockCanvasAndPost(c);
            }
        }
        c = null;
        // Repaint the view with the canvas
        try {
            c = userInterface.getHolder().lockCanvas();
            // TODO: c == null au lancement du thread, vérification avant draw() ??
            if(c != null)
                synchronized (userInterface.getHolder()) {
                    userInterface.draw(c);
                }
        } finally {
            if (c != null) {
                userInterface.getHolder().unlockCanvasAndPost(c);
            }
        }

    }

    public void playGame() {
        paused = false;
        gameModel.setPaused(paused);
    }

    public void pauseGame() {
        paused = true;
        gameModel.setPaused(paused);
    }

    public void stopGame() {
        boolean retry = true;
        running = false;
        while (retry) {
            try {
                join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Exit controller with pause = " + paused);

    }

    private boolean createBubble(int bubble_color) {
        Bubble bubble = new Bubble(bubble_color, gameView, 5 * FPS);
        gameModel.addBubble(bubble);
        return true;
    }

    public void togglePauseButton() {
        if(paused)
            playGame();
        else
            pauseGame();
    }

    public boolean isPaused() {
        return paused;
    }

    public void onTouchEvent(MotionEvent event) {
        if(paused)
            return;
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
        int color = gameModel.getBubbles().get(num).getColor();
        gameModel.removeBubble(num);
        if(color == gameModel.getBonusColor())
            gameModel.incScore(gameModel.getBonusPoint());
        else if(color == gameModel.getMalusColor())
            gameModel.decScore(gameModel.getMalusPoint());
        else
            gameModel.incScore(gameModel.getStandardPoint());
        userInterface.setScore(gameModel.getScore().toString());
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public LinearLayout getLayout() {
        return layout;
    }
}
