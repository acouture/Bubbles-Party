package com.blueweird.bubblesparty.controller;

/**
 * Created by blueweird on 06/05/2015.
 */
public abstract class MainLoopThread extends Thread {
    protected static final int FPS = 20;
    protected boolean running;
    protected boolean paused;

    public MainLoopThread() {
        running = true;
        paused = true;
    }

    @Override
    public void run() {
        System.out.println("Run controller with pause = " + paused);
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        while (running) {
            if(!paused) {
                startTime = System.currentTimeMillis();

                // Does what the game has to do
                loop();
                draw();

                // Sleep until the next frame to get the right FPS
                sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
                try {
                    if (sleepTime > 0)
                        sleep(sleepTime);
                    else
                        sleep(10);
                } catch (Exception e) {
                }
            }
        }
    }

    protected abstract void loop();
    public abstract void draw();
}
