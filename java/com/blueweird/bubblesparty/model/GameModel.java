package com.blueweird.bubblesparty.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blueweird on 06/05/2015.
 */
public class GameModel {
    private List<Bubble> bubbles;

    public GameModel() {
        bubbles = new ArrayList<>();
    }

    public void addBubble(int color) {
        bubbles.add(new Bubble(color));
    }

    public void removeBubble(int num) {
        bubbles.remove(num);
    }
}
