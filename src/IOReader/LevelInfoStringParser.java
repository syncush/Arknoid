package IOReader;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import helpers.Velocity;
import sprites.ColorBackground;
import sprites.ImageBackground;
import sprites.Sprite;

public class LevelInfoStringParser {
	public static List<Velocity> getVelocitesFromFile(String velocities) {
		List<Velocity> ballsVelocity = new ArrayList<Velocity>();
		if (!velocities.contains(" ")) {
			String[] singleVelocity = velocities.split(",");
			int angle = Integer.parseInt(singleVelocity[0]);
			int speed = Integer.parseInt(singleVelocity[1]);
			ballsVelocity.add(Velocity.fromAngleAndSpeed(angle, speed));

		} else {
			String[] perVelocity = velocities.split(" ");
			for (String item : perVelocity) {
				String[] velocityValues = item.split(",");
				int angle = Integer.parseInt(velocityValues[0]);
				int speed = Integer.parseInt(velocityValues[1]);
				ballsVelocity.add(Velocity.fromAngleAndSpeed(angle, speed));
			}
		}
		return ballsVelocity;
	}
	public static int getPaddleSpeed(String str) {
		return Integer.parseInt(str);
	}
	public static int getPaddleWidth(String str) {
		return Integer.parseInt(str);
	}
	public static int getBlockStartX(String str) {
		return Integer.parseInt(str);
	}
	public static int getBlocksY(String str) {
		return Integer.parseInt(str);
	}
	public static int getRowHeight(String str) {
		return Integer.parseInt(str);
	}
	public static int getNumberOfBlocks(String str) {
		return Integer.parseInt(str);
	}
	public static String getBlock_Definitions(String str) {
		return str;
	}
	public static String getLevelName(String str) {
		return str;
	}
	public static Sprite getBackground(String background) {
		String[] seperated = background.split("[(]");
		if (seperated[0].toLowerCase().contains("color") && !seperated[1].contains("RGB")) {
			String colorName = seperated[1].substring(0, seperated[1].length());
			Map<String, Color> colorMap = BlockDefinitionsParser.getMapColor();
			return new ColorBackground(colorMap.get(colorName));
		} else {
			if (seperated[1].contains("RGB")) {
				String[] rgbValues = seperated[2].replace("color(RGB(", "").replace("))", "").split(",");
				int redValue = Integer.parseInt(rgbValues[0]);
				int greenValue = Integer.parseInt(rgbValues[1]);
				int blueValue = Integer.parseInt(rgbValues[2]);
				return new ColorBackground(new Color(redValue, greenValue, blueValue));

			} else {
				if (seperated[0].toLowerCase().contains("image")) {
					String imagePath = seperated[1].substring(0, seperated[1].length() - 1);
					return new ImageBackground(imagePath);
				}
			}

		}
		throw new RuntimeException("FILE DOESN'T HOLD CORRECT BACKGROUND VALUES");
	}
}
