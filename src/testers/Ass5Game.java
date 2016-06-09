package testers;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import helpers.GameFlow;
import levels.LevelInformation;
import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.WideEasy;

/**
 * The "Ass5Game" class runs the game.
 *
 * @author Daniel Hermon
 */
public class Ass5Game {
    /**
     * The method runs the game animation.
     *
     * @param args -Arguments from command line.
     */
    public static void main(String[] args) {

        ArrayList<LevelInformation> listToRun = new ArrayList<LevelInformation>();
        Map<String, LevelInformation> map = new TreeMap<String, LevelInformation>();
        map.put("1", new DirectHit());
        map.put("2", new WideEasy());
        map.put("3", new Green3());
        map.put("4", new FinalFour());
        for (int i = 0; i < args.length; i++) {
            if (map.containsKey(args[i])) {
                listToRun.add(map.get(args[i]));
            }
        }
        if (listToRun.isEmpty()) {
            listToRun.add(new DirectHit());
            listToRun.add(new WideEasy());
            listToRun.add(new Green3());
            listToRun.add(new FinalFour());
        }
        GUI gui = new GUI("Arknoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, 60);
        KeyboardSensor ks = gui.getKeyboardSensor();
        GameFlow t = new GameFlow(ar, ks);
        t.runLevels(listToRun);
    }
}

