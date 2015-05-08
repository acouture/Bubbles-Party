package com.blueweird.bubblesparty.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by blueweird on 06/05/2015.
 */
public class GameModel {
    private List<Bubble> bubbles;
    private int score;

    public GameModel() {
        bubbles = new ArrayList<>();
        score = 0;
    }

    public void addBubble(int color) {
        bubbles.add(new Bubble(color));
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

    public List<Bubble> getBubbles() {
        return Collections.unmodifiableList(bubbles);
    }
}
