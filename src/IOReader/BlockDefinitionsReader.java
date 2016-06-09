package IOReader;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 6/2/2016.
 */
public class BlockDefinitionsReader {
    public static BlocksFromSymbolFactory fromReader(Reader reader) {
        List<BlockSymbol> bSymbolList = new ArrayList<BlockSymbol>();
        List<SpacerSymbol> spacerSymbolList = new ArrayList<SpacerSymbol>();
        List<String> fileText = convertFileToStringList(reader);

        try {
            //BufferedReader buffRead = new BufferedReader(reader);
            DefaultBlock dBlock = BlockDefinitionsParser.getDefault(fileText);
            //String str = buffRead.readLine();
            //   while (str != null) {
            for (String str : fileText) {
                if (str.contains("bdef")) {
                    String[] values = str.trim().split(" ");
                    String symbol = BlockDefinitionsParser.getSymbol(values);
                    int blockWidth = BlockDefinitionsParser.getBlockWidth(values, dBlock);
                    int height = BlockDefinitionsParser.getBlockHeight(values, dBlock);
                    int hitPoints = BlockDefinitionsParser.getHitPoints(values, dBlock);
                    Color co = BlockDefinitionsParser.getStroke(values, dBlock);
                    List<FillK> fillKList = BlockDefinitionsParser.getFillKList(values);
                    BlockSymbol bSymbol = new BlockSymbol(symbol, blockWidth, height, hitPoints, fillKList, co);
                    bSymbolList.add(bSymbol);
                }
                if (str.contains("sdef")) {
                    String[] values = str.trim().split(" ");
                    int spacerWidth = BlockDefinitionsParser.getSpacerWidth(values);
                    String symbol = BlockDefinitionsParser.getSpacerSymbol(values);
                    spacerSymbolList.add(new SpacerSymbol(symbol, spacerWidth));
                }
            }
        } catch (Exception ex) {
                throw new RuntimeException("Failed reading blocks text file");
            }
            return new BlocksFromSymbolFactory(bSymbolList, spacerSymbolList);
    }
    private static List<String> convertFileToStringList(Reader reader) {
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
}

