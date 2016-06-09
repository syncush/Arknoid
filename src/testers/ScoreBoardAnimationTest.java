package testers;

import animation.Animation;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import biuoop.GUI;
import scoretable.HighScoresTable;
import scoretable.ScoreInfo;

public class ScoreBoardAnimationTest {
	public static void main(String[] args) {
		HighScoresTable table = new HighScoresTable(6);
		GUI ui = new GUI("TEST", 800, 600);
		table.add(new ScoreInfo("Tamir", 6000));
		table.add(new ScoreInfo("yoav", 5));
		table.add(new ScoreInfo("Daniel", 120000));
		table.add(new ScoreInfo("Sahar", 40));
		Animation yolo = new HighScoresAnimation(table, "p", ui);
		AnimationRunner run = new AnimationRunner(ui, 60);
		run.run(yolo);
		ui.close();

	}
}
