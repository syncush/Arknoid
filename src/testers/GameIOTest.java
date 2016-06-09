package testers;

import IOReader.LevelSpecificationReader;
import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import helpers.GameFlow;
import levels.LevelInformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Daniel on 6/8/2016.
 */
public class GameIOTest {
    public static void main(String[] args) {
        ArrayList<LevelInformation> listToRun = new ArrayList<LevelInformation>();
        Map<String, LevelInformation> map = new TreeMap<String, LevelInformation>();
        //map.put("1", new DirectHit());
        //map.put("2", new WideEasy());
        //map.put("3", new Green3());
        //map.put("4", new FinalFour());
        LevelSpecificationReader tr = new LevelSpecificationReader();
        try {
            List<LevelInformation> levels = tr.fromReader(new FileReader(new File(args[0])));
            GUI gui = new GUI("Arknoid", 800, 600);
            AnimationRunner ar = new AnimationRunner(gui, 60);
            KeyboardSensor ks = gui.getKeyboardSensor();
            GameFlow t = new GameFlow(ar, ks);
            t.runLevels(levels);
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }
}
