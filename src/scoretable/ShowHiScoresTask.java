package scoretable;

import animation.Animation;
import animation.AnimationRunner;
import menu.Task;

/**
 * Created by Dell on 03/06/2016.
 */
public class ShowHiScoresTask implements Task<Void> {

    AnimationRunner runner;
    Animation highScoresAnimation;
    
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}
