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
    private int width;
    private int height;

    public Sprite(int resource, GameView gameView) {
        bmp = BitmapFactory.decodeResource(gameView.getResources(), resource);
        width = bmp.getWidth();
        height = bmp.getHeight();
    }

    public void setPos(int x, int y) {
        posX = x;
        posY = y;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bmp, posX, posY, null);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
