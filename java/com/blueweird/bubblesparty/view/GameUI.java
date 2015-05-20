package com.blueweird.bubblesparty.view;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by blueweird on 12/05/2015.
 */
public class GameUI extends UserInterface {
    RelativeLayout layout;
    TextView score;
    ImageButton button;

    public GameUI(Context context) {
        super(context);

        layout = new RelativeLayout(context);
        LayoutParams params;

        // Init the pause button
        button = new ImageButton(context);
        button.setImageResource(android.R.drawable.ic_media_pause);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(CENTER_VERTICAL);
        button.setLayoutParams(params);


        // Init the score
        score = new TextView(context);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_IN_PARENT);
        score.setLayoutParams(params);
        score.setTextSize(40);

        addView(score);
        addView(button);
    }

    public void setScore(String newScore) {
        score.setText(newScore);
    }
}
