package com.blueweird.bubblesparty.view;

import android.content.Context;
import android.widget.RelativeLayout;

import com.blueweird.bubblesparty.controller.GameController;

/**
 * Created by blueweird on 12/05/2015.
 */
public abstract class UserInterface extends RelativeLayout {
    protected GameController controller;

    public UserInterface(Context context, GameController ctrl) {
        super(context);
        controller = ctrl;
    }
}