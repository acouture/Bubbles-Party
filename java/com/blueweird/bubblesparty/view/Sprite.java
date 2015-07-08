package com.blueweird.bubblesparty.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by blueweird on 06/05/2015.
 */
public class Sprite {
    private Bitmap bmp;
    private int posX;
    private int posY;

    public Sprite(int resource, GameView gameView) {
        bmp = BitmapFactory.decodeResource(gameView.getResources(), resource);
    }

    public void setPos(int x, int y) {
        posX = x;
        posY = y;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bmp, posX, posY, null);
    }

    public int getWidth() {
        return bmp.getWidth();
    }

    public int getHeight() {
        return bmp.getHeight();
    }

    public Bitmap getBmp() {
        return bmp;
    }
}
