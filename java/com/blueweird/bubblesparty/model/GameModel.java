package com.blueweird.bubblesparty.model;

import android.content.Context;
import android.view.View;

import com.blueweird.bubblesparty.controller.GameController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by blueweird on 06/05/2015.
 */
public class GameModel {
    private GameController controller;
    private Context context;

    private List<Bubble> bubbles;
    private List<Integer> deadBubblesIndex;
    private Integer score;
    private int bonusColor;
    private int malusColor;
    private int bonusPoint;
    private int malusPoint;
    private int stdPoint;

    private boolean paused;

    public GameModel(Context context) {
        this.context = context;
        bubbles = new ArrayList<>();
        deadBubblesIndex = new ArrayList<>();
        score = 0;
        bonusPoint = 2;
        malusPoint = 5;
        stdPoint = 1;

        paused = false;
    }

    public void initGame() {
        controller = new GameController(context, this);
        controller.start();
    }

    public void playGame() {
        controller.playGame();
    }

    public void  pauseGame() {
        controller.pauseGame();
    }

    public void stopGame() {
        controller.stopGame();
        controller = null;
    }

    public View getView() {
        return controller.getLayout();
    }

    public boolean isInGame() {
        if(controller == null)
            return false;
        return true;
    }

    public void removeDeadBubbles() {
        for(int i = 0 ; i < bubbles.size() ; i++) {
            if(!bubbles.get(i).update())
                deadBubblesIndex.add(i);
        }
        for(int index: deadBubblesIndex) {
            bubbles.remove(index);
        }
        deadBubblesIndex.clear();
    }

    public void addBubble(Bubble bubble) {
        bubbles.add(bubble);
    }

    public void removeBubble(int index) {
        bubbles.remove(index);
    }

    public void incScore(int points) {
        score += points;
    }

    public void decScore(int points) {
        score -= points;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getBonusColor() {
        return bonusColor;
    }

    public int setBonusColor(int b) {
        return bonusColor = b;
    }

    public Integer getMalusColor() {
        return malusColor;
    }

    public int setMalusColor(int m) {
        return malusColor = m;
    }

    public int getBonusPoint() {
        return bonusPoint;
    }

    public int getMalusPoint() {
        return malusPoint;
    }

    public int getStandardPoint() {
        return stdPoint;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean p) {
        paused = p;
    }

    public List<Bubble> getBubbles() {
        return Collections.unmodifiableList(bubbles);
    }
}
