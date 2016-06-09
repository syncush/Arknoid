package scoretable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HighScoresTable {
	public static final int DEFAULT_TABLE_SIZE = 10;
	private List<ScoreInfo> listScores;
	 private int tableSize;
	  // Create an empty high-scores table with the specified size.
	   // The size means that the table holds up to size top scores.
	   public HighScoresTable(int size) {
		   this.listScores = new ArrayList<ScoreInfo>();
		   this.tableSize = size;
		   
	   }

	   // Add a high-score.
	   public void add(ScoreInfo score) {
		   if (this.getRank(score.getScore()) <= this.tableSize) {
			   listScores.add(score);
		   }
	   }

	   // Return table size.
	   public int size() {
		   return this.tableSize;
	   }

	   // Return the current high scores.
	   // The list is sorted such that the highest
	   // scores come first.
	   public List<ScoreInfo> getHighScores() {
		   Collections.sort(this.listScores, new ScoreComparator());
		   return this.listScores;
	   }

	   // return the rank of the current score: where will it
	   // be on the list if added?
	   // Rank 1 means the score will be highest on the list.
	   // Rank `size` means the score will be lowest.
	   // Rank > `size` means the score is too low and will not
	   //      be added to the list.
	   public int getRank(int score) {
		  List<ScoreInfo> tempList = this.getHighScores();
		  if (tempList.isEmpty()) {
			  return 1;
		  } else {
			  if (tempList.size() < this.tableSize) {
				  for (int i = 0; i < tempList.size(); i++) {
					  if (score > tempList.get(i).getScore()) {
						  return i + 1;
					  }
				  }
				  return tempList.size();
			  }
			  return this.DEFAULT_TABLE_SIZE + 1;
		  }
	   }
	   // Clears the table
	   public void clear() {
		   this.listScores = new ArrayList<ScoreInfo>();
	   }

	   // Load table data from file.
	   // Current table data is cleared.
	   public void load(File filename) throws IOException {
		   if (!filename.exists()) {
			   throw new IOException();
		   } else {
			   this.clear();
			   HighScoresTable table = loadFromFile(filename);
			   this.tableSize = table.size();
			   this.listScores = table.getHighScores();
		   }
	   }
	   // Save table data to the specified file.
	   public void save(File filename) throws IOException {
		   ObjectOutputStream c = new ObjectOutputStream(new FileOutputStream(filename));
		   c.write(this.tableSize);
		   c.write(this.listScores.size());
		   for (ScoreInfo item : this.listScores) {
			   c.writeObject(item);
		   }
		   c.close();
	   }

	   // Read a table from file and return it.
	   // If the file does not exist, or there is a problem with
	   // reading it, an empty table is returned.
	   public static HighScoresTable loadFromFile(File filename) {
		   FileInputStream io = null;
		   ObjectInputStream objectInput = null;
		   HighScoresTable table = null;
		   try {
			   io = new FileInputStream(filename);
			   objectInput = new ObjectInputStream(io);
			   table = new HighScoresTable(objectInput.read());
			   int currentcapacity = objectInput.read();
			   currentcapacity = Math.min(currentcapacity, table.size());
			   for (int i = 0; i < currentcapacity; i++) {
				   table.add((ScoreInfo) objectInput.readObject());
			   }
			   
			   
		   } catch(IOException c) {
			   return new HighScoresTable(DEFAULT_TABLE_SIZE);
		   } 
		   catch(ClassNotFoundException c) {
			   return new HighScoresTable(DEFAULT_TABLE_SIZE);
			   
		   } finally {
			   try {
				io.close();
				objectInput.close();
			} catch (IOException e) {
				
			}
			   
		   }
		   return table;
	   }
}
 
