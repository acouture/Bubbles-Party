package com.blueweird.bubblesparty;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.SurfaceView;

import com.blueweird.bubblesparty.controller.GameController;


public class MainActivity extends Activity {

    private SurfaceView mainView;
    private GameController controller;

    // TODO: Add the UI with score and buttons
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

        // Running the game
        controller = new GameController(this);

        // Set the view
        setContentView(controller.getLayout());
    }

    @Override
    protected void onPause() {
        super.onPause();
        controller.stopThread();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        controller.getGameView().destroyDrawingCache();
//    }
}
