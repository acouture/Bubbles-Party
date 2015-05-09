package com.blueweird.bubblesparty.model;

import com.blueweird.bubblesparty.R;
import com.blueweird.bubblesparty.view.GameView;
import com.blueweird.bubblesparty.view.Sprite;

import java.util.Random;

/**
 * Created by blueweird on 06/05/2015.
 */
public class Bubble {
    // direction = 0 up, 1 left, 2 down, 3 right,
    private static final int MAX_SPEED = 8;
    private static final int MIN_SPEED = 3;

    private int color;
    private Sprite sprite;

    private int posX;
    private int posY;
    private int width;
    private int height;
    private int speedX;
    private int speedY;

    private int screenWidth;
    private int screenHeight;

    public Bubble(int c, GameView gameView) {
        color = c;
        screenWidth = gameView.getWidth();
        screenHeight = gameView.getHeight();

        switch(color) {
            case 0:
                sprite = new Sprite(R.drawable.bubble_blue, gameView);
                break;
            case 1:
                sprite = new Sprite(R.drawable.bubble_green, gameView);
                break;
            case 2:
                sprite = new Sprite(R.drawable.bubble_red, gameView);
                break;
            case 3:
                sprite = new Sprite(R.drawable.bubble_yellow, gameView);
                break;
            default:
                break;
        }
        width = sprite.getWidth();
        height = sprite.getHeight();

        // Get random position and speed
        Random rnd = new Random();
        posX = rnd.nextInt(gameView.getWidth() - width);
        posY = rnd.nextInt(gameView.getHeight() - height);
        speedX = rnd.nextInt(MAX_SPEED - MIN_SPEED) + MIN_SPEED;
        speedY = rnd.nextInt(MAX_SPEED - MIN_SPEED) + MIN_SPEED;
        if(rnd.nextInt(2) == 0)
            speedX = -speedX;
        if(rnd.nextInt(2) == 0)
            speedY = -speedY;

    }

    public void update() {
        if (posX >= screenWidth - width - speedX || posX + speedX <= 0) {
            speedX = -speedX;
        }
        posX = posX + speedX;
        if (posY >= screenHeight - height - speedY || posY + speedY <= 0) {
            speedY = -speedY;
        }
        posY = posY + speedY;
        sprite.setPos(posX, posY);
    }

    public boolean isCollision(float x2, float y2) {
        return x2 > posX && x2 < posX + width && y2 > posY && y2 < posY + height;
    }

    public int getColor() {
        return color;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
