package animation;

import java.awt.Color;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.GUI;
import scoretable.HighScoresTable;
import scoretable.ScoreInfo;

public class HighScoresAnimation implements Animation {
	private List<ScoreInfo>  scoreBoard;
	private String endingKey;
	private GUI graphicUI;
	 public HighScoresAnimation(HighScoresTable scores, String endKey, GUI graphicInterface) {
		 this.scoreBoard = scores.getHighScores();
		 this.endingKey = endKey;
		 this.graphicUI = graphicInterface;
		 
	 }
	@Override
	public void doOneFrame(DrawSurface d, double dt) {
		d.setColor(Color.BLACK);
		d.drawText(100, 100, "Scoreboard", 20);
		d.drawText(100, 120, "___________________________________________________________________________", 3);
		for (int i = 0; i < this.scoreBoard.size(); i++) {
			ScoreInfo temp = this.scoreBoard.get(i);
			d.drawText(100, 120 + ((i + 1) * 40), temp.getName() + "         " + temp.getScore(), 15);
		}
	}

	@Override
	public boolean shouldStop() {
		return false;
	}

}
