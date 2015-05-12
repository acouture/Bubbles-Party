package com.blueweird.bubblesparty.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by blueweird on 12/05/2015.
 */
public class GameUI extends UserInterface {
    String score;
    Paint paint;

    public GameUI(Context context) {
        super(context);
        score = new String();
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(80);
    }
    
    public void setScore(String newScore) {
        score = newScore;
        invalidate();
    }

    @Override
    public void onDraw(Canvas c) {
        c.drawColor(Color.CYAN);
        c.drawText(score, c.getWidth() / 2 - getScoreWidth() / 2, paint.getTextSize(), paint);
    }

    private float getScoreWidth() {
        float[] widths = new float[score.length()];
        paint.getTextWidths(score, widths);
        float w = 0;
        for(float f: widths)
            w += f;
        return w;
    }
}
