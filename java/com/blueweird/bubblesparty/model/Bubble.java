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
    private static final int MAX_SPEED = 12;
    private static final int MIN_SPEED = 4;

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

        Random rnd = new Random();
        // Get random speed
        int majorSpeed = rnd.nextInt(MAX_SPEED - MIN_SPEED + 1) + MIN_SPEED + 1;
        int minorSpeed = rnd.nextInt(majorSpeed - MIN_SPEED) + MIN_SPEED;
        if(rnd.nextInt(2) == 0)
            speedX = -speedX;
        if(rnd.nextInt(2) == 0)
            speedY = -speedY;

        // Get random position
        int pos1 = rnd.nextInt(screenWidth + screenHeight);
        boolean alpha = rnd.nextBoolean(); // To choose top or bot OR left or right
        // top or bot
        if(pos1 < screenWidth) {
            posX = pos1;
            speedY = majorSpeed;
            speedX = minorSpeed;
            if(alpha)
                posY = -height;
            else
                posY = screenHeight;
        }
        // left or right
        else {
            posY = pos1 - screenWidth;
            speedX = majorSpeed;
            speedY = minorSpeed;
            if(alpha)
                posX = -width;
            else
                posX = screenWidth;
        }
    }

    public void update() {
        if(posX + speedX <= 0)
            speedX = Math.abs(speedX);
        if (posX >= screenWidth - width - speedX) {
            speedX = -Math.abs(speedX);
        }
        posX = posX + speedX;
        if(posY + speedY <= 0)
            speedY = Math.abs(speedY);
        if (posY >= screenHeight - height - speedY) {
            speedY = -Math.abs(speedY);
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
