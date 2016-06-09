package testers;

import IOReader.BlockDefinitionsReader;
import IOReader.BlocksFromSymbolFactory;

import java.io.*;

/**
 * Created by Daniel on 6/5/2016.
 */
public class stringSplitTest {
    public static void main(String[] args) {
        File f = new File("blockTest.txt");
        Reader fR = null;
        try {
            fR = new FileReader(f);
            BlocksFromSymbolFactory test = BlockDefinitionsReader.fromReader(fR);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                fR.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
