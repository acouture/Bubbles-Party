package com.blueweird.bubblesparty.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by blueweird on 06/05/2015.
 */
public class Sprite {
    // direction = 0 up, 1 left, 2 down, 3 right,
    private static final int MAX_SPEED = 8;
    private static final int MIN_SPEED = 3;

    private GameView gameView;
    private Bitmap bmp;

    private int posX;
    private int posY;
    private int speedX;
    private int speedY;
    private int width;
    private int height;

    public Sprite(GameView gameView, Bitmap bmp) {
        width = bmp.getWidth();
        height = bmp.getHeight();
        this.gameView = gameView;
        this.bmp = bmp;

        Random rnd = new Random();
        posX = rnd.nextInt(gameView.getWidth() - width);
        posY = rnd.nextInt(gameView.getHeight() - height);
        speedX = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
        speedY = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
    }

    private void update() {
        if (posX >= gameView.getWidth() - width - speedX || posX + speedX <= 0) {
            speedX = -speedX;
        }
        posX = posX + speedX;
        if (posY >= gameView.getHeight() - height - speedY || posY + speedY <= 0) {
            speedY = -speedY;
        }
        posY = posY + speedY;
    }

    public void draw(Canvas canvas) {
        update();
        canvas.drawBitmap(bmp, posX, posY, null);
    }

    public boolean isCollision(float x2, float y2) {
        return x2 > posX && x2 < posX + width && y2 > posY && y2 < posY + height;
    }
}
