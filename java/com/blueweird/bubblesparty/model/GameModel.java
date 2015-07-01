package com.blueweird.bubblesparty.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by blueweird on 06/05/2015.
 */
public class GameModel {
    private List<Bubble> bubbles;
    private List<Integer> deadBubblesIndex;
    private Integer score;
    private int bonus;
    private int malus;

    public GameModel() {
        bubbles = new ArrayList<>();
        deadBubblesIndex = new ArrayList<>();
        score = 0;
    }

    public void update() {
        for(Bubble bubble: bubbles) {
            if(!bubble.update())
                deadBubblesIndex.add(bubbles.indexOf(bubble));
        }
        for(int bubble: deadBubblesIndex) {
            removeBubble(bubble);
        }
        deadBubblesIndex.clear();
    }

    public void addBubble(Bubble bubble) {
        bubbles.add(bubble);
    }

    public void removeBubble(int num) {
        bubbles.remove(num);
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

    public int getBonus() {
        return bonus;
    }

    public int setBonus(int b) {
        return bonus = b;
    }

    public int getMalus() {
        return malus;
    }

    public int setMalus(int m) {
        return malus = m;
    }

    public List<Bubble> getBubbles() {
        return Collections.unmodifiableList(bubbles);
    }
}
