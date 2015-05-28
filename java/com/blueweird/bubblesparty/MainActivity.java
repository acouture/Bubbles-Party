package com.blueweird.bubblesparty;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.blueweird.bubblesparty.controller.GameController;
import com.blueweird.bubblesparty.view.MainMenu;


public class MainActivity extends Activity {

    private MainMenu mainMenu;
    private GameController controller;
    private boolean inGame;

    // TODO: Optimize memory with sprite loads

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Remove title bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mainMenu = new MainMenu(this);
        inGame = false;

        // Set the view
        setContentView(mainMenu);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(inGame)
            controller.stopThread();
    }

    @Override
    public void onBackPressed() {
        if(inGame) {
            controller.stopThread();
            inGame = false;
            mainMenu.refresh();
            setContentView(mainMenu);
        }
        else {
            super.onBackPressed();
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        controller.getGameView().destroyDrawingCache();
//    }

    public void newGame() {
        controller = new GameController(this);
        inGame = true;
        setContentView(controller.getLayout());
    }

    public void resumeGame() {
        inGame = true;
        setContentView(controller.getLayout());
    }

    public boolean isController() {
        if(controller == null)
            return false;
        return true;
    }
}
