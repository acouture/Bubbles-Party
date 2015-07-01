package com.blueweird.bubblesparty.controller;

/**
 * Created by blueweird on 06/05/2015.
 */
public abstract class MainLoopThread extends Thread {
    protected static final long FPS = 20;
    protected boolean running = false;

    public MainLoopThread() {
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        while (running) {
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
            } catch (Exception e) {}
        }
    }

    public boolean isRunning() {
        return running;
    }

    protected abstract void loop();
    protected abstract void draw();
}
