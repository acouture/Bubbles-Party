package com.blueweird.bubblesparty.view;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    TextView tvVersion;
    TextView tvTitle;

    public MainMenu(final Context context) {
        super(context);

        app = (MainActivity) context;

        LayoutParams params;
        setBackgroundResource(R.drawable.background);

        int height = context.getResources().getDisplayMetrics().heightPixels;

        tvTitle = new TextView(context);
        tvTitle.setText(R.string.app_name);
        tvTitle.setTextSize(45);
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_HORIZONTAL);
        params.setMargins(0, (int) (0.2 * height), 0, 0);
        addView(tvTitle, params);

        bNewGame = new Button(context);
        bNewGame.setText(R.string.new_game);
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
        bResumeGame.setText(R.string.resume);
        bResumeGame.setEnabled(app.isGameModel());
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
        bQuit.setText(R.string.quit);
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

        tvVersion = new TextView(context);
        try {
            tvVersion.setText(app.getPackageManager().getPackageInfo(app.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(ALIGN_PARENT_RIGHT);
        params.setMargins(5, 5, 5, 5);
        addView(tvVersion, params);
    }

    public void refresh() {
        bResumeGame.setEnabled(app.isGameModel());
    }
}
