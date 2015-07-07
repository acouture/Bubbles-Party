package com.blueweird.bubblesparty.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blueweird.bubblesparty.controller.GameController;

/**
 * Created by blueweird on 12/05/2015.
 */
public class UserInterface extends RelativeLayout {
    private GameController controller;
//    private RelativeLayout layout;
    private TextView score;
    private ImageView bonus;
    private TextView malus;
    private ImageButton button;

    public UserInterface(Context context, GameController ctrl) {
        super(context);

        controller = ctrl;
//        layout = new RelativeLayout(context);

        int uiSize = (int) (0.1 * context.getResources().getDisplayMetrics().heightPixels);
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, uiSize));

        LayoutParams params;

        // Init the pause button
        button = new ImageButton(context);
        button.setImageResource(android.R.drawable.ic_media_pause);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(CENTER_VERTICAL);
        button.setLayoutParams(params);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.togglePauseButton();
            }
        });


        // Init the score
        score = new TextView(context);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_IN_PARENT);
        score.setLayoutParams(params);
        score.setTextSize(40);

        // Init the bonus / malus
        bonus = new ImageView(context);
//        malus = new TextView(context);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_PARENT_LEFT);
        bonus.setLayoutParams(params);

        addView(bonus);
        addView(score);
        addView(button);
    }

    public void toggleIsPaused(boolean paused) {
        if (paused)
            button.setImageResource(android.R.drawable.ic_media_play);
        else
            button.setImageResource(android.R.drawable.ic_media_pause);
    }

    public void printScore(String newScore) {
        score.setText(newScore);
    }

    public void printBonus(int res) {
        bonus.setImageResource(res);
    }
}
