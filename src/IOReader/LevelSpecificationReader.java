package IOReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.io.*;

import levels.LevelInformation;
import helpers.Velocity;
import levels.BaseLevel;
import sprites.Block;
import sprites.Sprite;

public class LevelSpecificationReader {
	   public List<LevelInformation> fromReader(Reader reader) {
		   int levelCounter = 0;
		   List<LevelInformation> levelsInfo = new ArrayList<>();
		   Map<String, String> mapValues = new TreeMap<String, String>();
		   List<String> fileText = convertFileToText(reader);
		   List<List<String>> levelText = getLevelsText(fileText);
		   List<List<String>> blockLines = getBlocksLines(fileText);
		   try {
			   for (List<String> singleLevel : levelText) {
				   for (String readingLine : singleLevel ) {
					   if (!shouldIgnore(readingLine)) {
						   String[] xy = readingLine.split(":");
						   mapValues.put(xy[0], xy[1]);
					   }
				   }
				   List<String> singleLevelBlocksLine = blockLines.get(levelCounter);
				   List <Velocity>  ballVelo = LevelInfoStringParser.getVelocitesFromFile(mapValues.get("ball_velocities"));
				   int paddleWidth = LevelInfoStringParser.getPaddleWidth(mapValues.get("paddle_width"));
				   int paddleSpeed = LevelInfoStringParser.getPaddleSpeed(mapValues.get("paddle_speed"));
				   int rowHeight = LevelInfoStringParser.getRowHeight(mapValues.get("row_height"));
				   int blockStartX = LevelInfoStringParser.getBlockStartX(mapValues.get("blocks_start_x"));
				   int blockStartY = LevelInfoStringParser.getBlocksY(mapValues.get("blocks_start_y"));
				   int numOfBlocks = LevelInfoStringParser.getNumberOfBlocks(mapValues.get("num_blocks"));
				   String blockDefinition = LevelInfoStringParser.getBlock_Definitions(mapValues.get("block_definitions"));
				   String levelName = LevelInfoStringParser.getLevelName(mapValues.get("level_name"));
				   Sprite background = LevelInfoStringParser.getBackground(mapValues.get("background"));
				   BlocksFromSymbolFactory bfsFactory = BlockDefinitionsReader.fromReader(
						   new FileReader(new File(blockDefinition)));
				   List<Block>  blockListSingleLevel = getBlocks(rowHeight, blockStartX, blockStartY, bfsFactory, singleLevelBlocksLine);
				   BaseLevel basicLevel = new BaseLevel(paddleWidth, ballVelo, blockListSingleLevel,
						   paddleSpeed, numOfBlocks, levelName, background);
				   levelsInfo.add(basicLevel);
				   levelCounter++;
			   }
			   return levelsInfo;
		   } catch (IOException ex) {
			   throw new RuntimeException("Failed reading blocks definitions file");
		   }
	   }

	private List<List<String>> getBlocksLines(List<String> fileText) {
		List<List<String>> levelsBlocks = new ArrayList<>();
		List<String> singleBlocksLines;
		for (int i = 0; i < howManyLevels(fileText); i++) {
			singleBlocksLines = new ArrayList<String>();
			singleBlocksLines = getBlockLineOFLevelNumX(i + 1, fileText);
			levelsBlocks.add(singleBlocksLines);
		}
		return levelsBlocks;
	}

	private List<String> getBlockLineOFLevelNumX(int i, List<String> fileText) {
		int counter = 0;
		List<String> blockLines = new ArrayList<>();
		for(int j = 0; i < fileText.size(); j++) {
			if (fileText.get(j).contains("START_BLOCKS")) {
				counter++;
				if (counter == i) {
					j++;
					while(!fileText.get(j).contains("END_BLOCKS")) {
						blockLines.add(fileText.get(j));
						j++;
					}
					break;
				}
			}
		}
		return blockLines;
	}

	private boolean shouldIgnore(String readingLine) {
		if (readingLine.contains("START_LEVEL") || readingLine.contains("END_LEVEL")) {
			return true;
		}
		return false;
	}
	private static List<String> convertFileToText(Reader reader) {
		List<String> stringFile = new ArrayList<String>();
		BufferedReader br = new BufferedReader(reader);
		String strLine;
		try {
			while ((strLine = br.readLine()) != null) {
					stringFile.add(strLine);
			}
			br.close();
		} catch(IOException ex) {
			throw new RuntimeException("Failed reading a file");

		}
		return stringFile;
	}
	private static int howManyLevels(List<String> text) {
		int counter = 0;
		for (String item : text) {
			if (item.contains("START_LEVEL")) {
				counter++;
			}
		}
		return counter;
	}
	private static List<List<String>> getLevelsText(List<String> fileText) {
		List<List<String>> levelList = new ArrayList<List<String>>();
		List<String> singleLevel;
		int numOfLevels = howManyLevels(fileText);
		for (int i = 0; i < numOfLevels; i++) {
			singleLevel = new ArrayList<String>();
			singleLevel = getSingleLevelNumX(i + 1, fileText);
			levelList.add(singleLevel);
		}
		return levelList;
	}

	private static List<String> getSingleLevelNumX(int i, List<String> fileText) {
		int counter = 0;
		List<String> levelText = new ArrayList<>();
		int startLevelText = getStartLevelText(i, fileText);
		int endLevelText = getEndLevelText(i, fileText);
		int startOfBlock = getStartOfBlocksOfLevelNumX(i, fileText);
		int endofBlocks = getEndOfBlocksOfLevelNumX(i, fileText);
		for (int j = startLevelText; j <= endLevelText; j++) {
			if (startOfBlock <= j && j <= endofBlocks) {
				continue;
			} else {
				levelText.add(fileText.get(j));
			}
		}
		return levelText;
	}

	private static int getEndOfBlocksOfLevelNumX(int i, List<String> fileText) {
		int counter = 0;
		for (int j = 0; j < fileText.size(); j++) {
			if (fileText.get(j).contains("END_BLOCKS")) {
				counter++;
				if (counter == i) {
					return j;
				}
			}
		}
		return -1;
	}

	private static int getStartOfBlocksOfLevelNumX(int i, List<String> fileText) {
		int counter = 0;
		for (int j = 0; j < fileText.size(); j++) {
			if (fileText.get(j).contains("START_BLOCKS")) {
				counter++;
				if (counter == i) {
					return j;
				}
			}
		}
		return -1;
	}

	private static int getEndLevelText(int i, List<String> fileText) {
		int counter = 0;
		for (int j = 0; j < fileText.size(); j++) {
			if (fileText.get(j).contains("END_LEVEL")) {
				counter++;
				if (counter == i) {
					return j;
				}
			}
		}
		return -1;
	}

	private static int getStartLevelText(int i, List<String> fileText) {
		int counter = 0;
		for (int j = 0; j < fileText.size(); j++) {
			if (fileText.get(j).contains("START_LEVEL")) {
				counter++;
				if (counter == i) {
					return j;
				}
			}
		}
		return -1;
	}
	private static List<Block> getBlocks(int rowHeight,int blockStartX,int blockStartY,
										 BlocksFromSymbolFactory bfsFactory,
										 List<String> singleLevelBlocksLine) {
		List<Block> blocksList = new ArrayList<>();
		int runningX = blockStartX;
		int runningY = blockStartY;
		for (String item : singleLevelBlocksLine) {
			if (containsOnlySpacers(item, bfsFactory)) {
				runningX = blockStartX;
				runningY += rowHeight;
				continue;
			} else {
				for (int i = 0; i < item.toCharArray().length; i++) {
					String s = "" + item.charAt(i);
					if (bfsFactory.isSpaceSymbol(s)) {
						runningX += bfsFactory.getSpaceWidth(s);

					} else {
						if (bfsFactory.isBlockSymbol(s)) {
							Block b = bfsFactory.getBlock(s, runningX, runningY);
							blocksList.add(b);
							runningX += b.getWidth();
						}
					}
				}
			}
			runningX = blockStartX;
			runningY += rowHeight;
		}
		return blocksList;
	}

	private static boolean containsOnlySpacers(String item, BlocksFromSymbolFactory bfsFactory) {
		for (char c : item.toCharArray()) {
			String s = "" + c;
			if (bfsFactory.isBlockSymbol(s)) {
				return false;
			}
		}
		return true;
	}
}