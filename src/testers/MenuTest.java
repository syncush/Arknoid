package testers;

import animation.*;
import biuoop.GUI;
import helpers.GameFlow;
import levels.LevelInformation;
import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.WideEasy;
import menu.Menu;
import menu.Task;
import scoretable.HighScoresTable;

import java.util.ArrayList;

/**
 * Created by Dell on 03/06/2016.
 */
public class MenuTest {
    public static void main(String [] args) {
        final GUI gui = new GUI("Ankorid", 800, 600);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Menu", gui);
        final AnimationRunner runner = new AnimationRunner(gui, 60);
        final Animation scoreBoard = new HighScoresAnimation(new HighScoresTable(5), runner.getGui().getKeyboardSensor().SPACE_KEY, gui);
        final GameFlow gameFlow= new GameFlow(runner,runner.getGui().getKeyboardSensor());
        final ArrayList<LevelInformation> listToRun = new ArrayList<LevelInformation>();
        listToRun.add(new DirectHit());
        listToRun.add(new WideEasy());
        listToRun.add(new Green3());
        listToRun.add(new FinalFour());
        Task <Void> q = new Task<Void>() {
            @Override
            public Void run() {
                gui.close();
                System.exit(0);
                return null;
            }
        };
        Task <Void> h = new Task<Void>() {
            @Override
            public Void run() {
                gameFlow.showScoreTable();
                return null;
            }
        };
        Task <Void> s = new Task<Void>() {
            @Override
            public Void run() {
                gameFlow.runLevels(listToRun);
                gameFlow.reset();
                return null;
            }
        };
        menu.addSelection("s", "press s to start playing",s);
        menu.addSelection("h", "press h to see high score table", h);
        menu.addSelection("q","press q to quit the game",q);
        while (true) {
            runner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }
}