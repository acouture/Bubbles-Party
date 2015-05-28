package com.blueweird.bubblesparty.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.blueweird.bubblesparty.MainActivity;
import com.blueweird.bubblesparty.R;

/**
 * Created by blueweird on 27/05/2015.
 */
public class MainMenu extends RelativeLayout {
    MainActivity app;
    Button bQuit;
    Button bNewGame;
    Button bResumeGame;

    public MainMenu(final Context context) {
        super(context);

        app = (MainActivity) context;

        LayoutParams params;
        setBackgroundResource(R.drawable.background);

        int height = context.getResources().getDisplayMetrics().heightPixels;

        bNewGame = new Button(context);
        bNewGame.setText("Nouvelle partie");
        bNewGame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                app.newGame();
            }
        });
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_HORIZONTAL);
        params.setMargins(0, (int) (height / 2 - 0.05 * height), 0, 0);
        addView(bNewGame, params);

        bResumeGame = new Button(context);
        bResumeGame.setText("Continuer");
        bResumeGame.setEnabled(app.isController());
        bResumeGame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                app.resumeGame();
            }
        });
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_HORIZONTAL);
        params.setMargins(0, (int) (height / 2 + 0.05 * height), 0, 0);
        addView(bResumeGame, params);

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

    public void refresh() {
        bResumeGame.setEnabled(app.isController());
    }
}
