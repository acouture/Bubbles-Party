# Bubbles-Party
Bu-Bu-Bu-Bubbles breaker !
... an android game

## TODO list before the stable version
### New features
* Save highscores
* Add a "about" menu with version and credits
* Create a real gameplay !

### Improvements/patchs
* Found why the game always run when it resume, even if it was paused

## Others optionnal addition
* Make a lift ! (get better images)
* Optimize memory by charging images only 1 time and use sprites

## Actual bugs encountered
* java.util.ConcurrentModificationException
            at java.util.ArrayList$ArrayListIterator.next(ArrayList.java:573)
            at removeDeadBubbles(GameModel.java:20)
            at com.blueweird.bubblesparty.controller.GameThread.loop(GameThread.java:40)
            at com.blueweird.bubblesparty.controller.MainLoopThread.run(MainLoopThread.java:30)
