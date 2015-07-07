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
//    private MainLoopThread mainLoopThread;

    private Random rnd;
    private int spawnRate;
    private int spawnRateInc;
    private int time;

    public GameController(Context context, GameModel gm) {
        super();
//        gameModel = new GameModel();
        gameModel = gm;
        gameView = new GameView(context, this);
        userInterface = new UserInterface(context, this);

        paused = gameModel.isPaused();
        System.out.println("Start controller with pause = " + paused);

        layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(userInterface);
        layout.addView(gameView);

        userInterface.printScore(gameModel.getScore().toString());

        rnd = new Random();
        spawnRate = 0;
        spawnRateInc = 30;
        time = 0;
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
            while(gameModel.setBonusColor(rnd.nextInt(4)) == gameModel.getMalusColor());
            System.out.println("Bonus = " + gameModel.getBonusColor());
            userInterface.printBonus(Bubble.colorToDrawable(gameModel.getBonusColor()));
        }
        if (time % (60 * FPS) == 0) {
            gameModel.setMalusColor(rnd.nextInt(4));
            time = 0;
            System.out.println("Malus = " + gameModel.getMalusColor());
        }
        gameModel.removeDeadBubbles();
        time ++;
    }

    @Override
    protected void draw() {
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
    }

    public void playGame() {
//        mainLoopThread = new GameThread(gameModel, gameView);
//        mainLoopThread.setRunning(true);
        paused = false;
        gameModel.setPaused(paused);
        userInterface.toggleIsPaused(paused);
//        mainLoopThread.start();
    }

    public void pauseGame() {
        paused = true;
        gameModel.setPaused(paused);
        userInterface.toggleIsPaused(paused);
    }

    public void stopGame() {
        boolean retry = true;
//        mainLoopThread.setRunning(false);
        running = false;
        while (retry) {
            try {
                join();
                retry = false;
            } catch (InterruptedException e) {}
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
        userInterface.printScore(gameModel.getScore().toString());
    }

    public GameModel getGameModel() {
        return gameModel;
    }

//    public GameView getGameView() {
//        return gameView;
//    }

    public LinearLayout getLayout() {
        return layout;
    }
}
