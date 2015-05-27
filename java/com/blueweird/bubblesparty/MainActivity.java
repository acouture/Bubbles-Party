package com.blueweird.bubblesparty;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.blueweird.bubblesparty.controller.GameController;
import com.blueweird.bubblesparty.view.MainMenu;


public class MainActivity extends Activity {

    private MainMenu mainMenu;
    private GameController controller;

    // TODO: Create a menu
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

        // Set the view
        setContentView(mainMenu);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(controller != null)
            controller.stopThread();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        controller.getGameView().destroyDrawingCache();
//    }

    public void setController(GameController gc) {
        controller = gc;
    }
}
