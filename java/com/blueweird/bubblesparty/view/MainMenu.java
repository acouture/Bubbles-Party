package com.blueweird.bubblesparty.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.blueweird.bubblesparty.MainActivity;
import com.blueweird.bubblesparty.R;
import com.blueweird.bubblesparty.controller.GameController;

/**
 * Created by blueweird on 27/05/2015.
 */
public class MainMenu extends RelativeLayout {
    MainActivity app;
    Button bQuit;
    Button bPlay;

    public MainMenu(final Context context) {
        super(context);

        app = (MainActivity) context;

        LayoutParams params;
        setBackgroundResource(R.drawable.background);

        bPlay = new Button(context);
        bPlay.setText("Jouer");
        bPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                app.runGame();
            }
        });
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_IN_PARENT);
        addView(bPlay, params);

        bQuit = new Button(context);
        bQuit.setText("Quitter");
        bQuit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                app.finish();
            }
        });
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_HORIZONTAL);
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.setMargins(0, 0, 0, 100);
        addView(bQuit, params);
    }
}
