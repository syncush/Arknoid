package testers;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import levels.LevelInformation;
import IOReader.LevelSpecificationReader;
import sprites.Block;

import java.io.*;
import java.util.List;

/**
 * Created by Daniel on 6/6/2016.
 */
public class MoreTest {
    public static void main(String[] args) {
        File f = new File("Level.txt");
        Reader read = null;
        try {
            read = new FileReader(f);
            LevelSpecificationReader b = new LevelSpecificationReader();
            List<LevelInformation> t = b.fromReader(read);
            Sleeper sleeper = new Sleeper();
            GUI gui = new GUI("Arknoid", 800, 600);
            int millisecondsPerFrame = 60;
            while (true) {
                long startTime = System.currentTimeMillis(); // timing
                DrawSurface d = gui.getDrawSurface();
                t.get(0).getBackground().drawOn(d);
                for (Block blo : t.get(0).blocks()) {
                    blo.drawOn(d);
                }
                gui.show(d);
                long usedTime = System.currentTimeMillis() - startTime;
                long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
                if (milliSecondLeftToSleep > 0) {
                    sleeper.sleepFor(milliSecondLeftToSleep);
                }
                sleeper.sleepFor(1000);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
