package helpers;

import animation.AnimationRunner;
import animation.EndScreenAnimation;
import animation.GameLevel;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.EndScreen;
import levels.LevelInformation;
import menu.Menu;
import scoretable.HighScoresTable;
import scoretable.ScoreInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Dell on 19/05/2016.
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter livesCounter;
    private Counter scoreCounter;
    private HighScoresTable scoreBoard;
    private Menu menu;
    /**
     * The method creates a new GameFlow object.
     * @param ar The animation.
     * @param ks -The keyboard sensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.livesCounter = new Counter(1);
        this.scoreCounter = new Counter();
        File f = new File("HighScores");
        this.scoreBoard = new HighScoresTable(HighScoresTable.DEFAULT_TABLE_SIZE);
        if (!f.exists()) {
        	try {
				this.scoreBoard.save(f);
			} catch (IOException e) {
				throw new RuntimeException();
			}
        } else {
        	try {
				this.scoreBoard.load(f);
			} catch (IOException e) {
				throw new RuntimeException();
			}
        }
    }
    /**
     * The method runs the levels by order.
     * @param levels a list of levels we run our game.
     */
    public void runLevels(List<LevelInformation> levels) {
        GameLevel level = null;

        for (LevelInformation levelInfo : levels) {

            level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                    this.scoreCounter.getValue(), this.livesCounter.getValue());
            level.initialize();

            while (level.getBlockRemover().getBlocksCounter().getValue() > 0
                    && level.getLiveIndicator().getliveCounter().getValue() > 0) {
                level.playOneTurn();
                if (level.getBallRemover().getBallCounter().getValue() < 1) {
                    level.getLiveIndicator().getliveCounter().decrease(1);
                }
            }
            this.scoreCounter.changeValue(level.getScore().getValue());
            this.livesCounter.changeValue(level.getLiveIndicator().getliveCounter().getValue());
            if (this.getNumOfLives() < 1) {
                break;
            }
        }
        EndScreen endScreen = new EndScreen(this.getNumOfLives() > 0, this.getScore());
        EndScreenAnimation endScreenAni = new EndScreenAnimation(endScreen, this.getGui());
        KeyPressStoppableAnimation stopableAni = new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY, endScreenAni);
        this.animationRunner.run(stopableAni);
        if (this.scoreBoard.getRank(this.getScore()) <= this.scoreBoard.size()) {
            DialogManager dialog = this.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "Annonymous");
            this.scoreBoard.add(new ScoreInfo(name, this.getScore()));
            try {
                this.scoreBoard.save(new File("HighScores"));
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
        this.showScoreTable();
    }
    public void showScoreTable(){
        HighScoresAnimation ani = new HighScoresAnimation(scoreBoard, KeyboardSensor.SPACE_KEY, animationRunner.getGui());
        KeyPressStoppableAnimation stopableHighScoreAni = new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY, ani);
        animationRunner.run(stopableHighScoreAni);
    }
    public void reset(){
        this.scoreCounter.changeValue(0);
        this.livesCounter.changeValue(1);
    }
    /**
     * The method returns how many lives left for the player.
     * @return the number of lives left for the player.
     */
    public int getNumOfLives() {
        return this.livesCounter.getValue();
    }
    /**
     * The method returns the GUI.
     * @return the graphic user interface.
     */
    public GUI getGui() {
        return this.animationRunner.getGui();
    }
    /**
     * The method returns the player's score.
     * @return the player's score.
     */
    public int getScore() {
        return this.scoreCounter.getValue();
    }
}