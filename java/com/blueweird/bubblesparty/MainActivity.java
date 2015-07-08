package com.blueweird.bubblesparty;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.blueweird.bubblesparty.model.GameModel;
import com.blueweird.bubblesparty.view.MainMenu;


public class MainActivity extends Activity {

    private MainMenu mainMenu;
    private GameModel gameModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Remove title bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mainMenu = new MainMenu(this);

        // Set the view
        setContentView(mainMenu);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(gameModel != null)
            if(gameModel.isInGame())
                gameModel.pauseGame();
    }

    @Override
    public void onBackPressed() {
        if(gameModel != null) {
            if (gameModel.isInGame()) {
                gameModel.stopGame();
                mainMenu.refresh();
                setContentView(mainMenu);
            }
            else {
                super.onBackPressed();
            }
        }
        else {
            super.onBackPressed();
        }
    }

    public void newGame() {
        gameModel = new GameModel(this);
        gameModel.initGame();
        setContentView(gameModel.getView());
        gameModel.playGame();
    }

    public void resumeGame() {
        // TODO: When resume with paused = true, game run like paused is false ...
        gameModel.initGame();
        setContentView(gameModel.getView());
    }

    public boolean isGameModel() {
        if(gameModel == null)
            return false;
        return true;
    }
}
