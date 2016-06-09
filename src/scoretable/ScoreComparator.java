package scoretable;

import java.util.Comparator;

public class ScoreComparator implements Comparator<ScoreInfo> {
	@Override
	public int compare(ScoreInfo o1, ScoreInfo o2) {
		return o2.getScore() - o1.getScore();
	}
}

