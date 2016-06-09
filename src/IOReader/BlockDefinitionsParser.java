package IOReader;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Daniel on 6/2/2016.
 */
public class BlockDefinitionsParser {
    public static DefaultBlock getDefault(List<String> fileText) {
        DefaultBlock dBlock;
        //BufferedReader buffReader = new BufferedReader(reader);
            //String str = buffReader.readLine();
            //while (str != null) {
            for (String str : fileText) {
                if (str.contains("default ") && !str.contains("#")) {
                    String[] values = str.trim().split(" ");
                    int defaultHeight = getDefaultHeight(values);
                    int defaultWidth = getDefaultWidth(values);
                    int hitPoints = getDefaultHitPoints(values);
                    Color c = getDefaultStroke(values);
                    dBlock = new DefaultBlock(defaultHeight, defaultWidth, hitPoints, c);
                    return dBlock;
                }
                //str = buffReader.readLine();
            }
        return new DefaultBlock();
    }
    private static int getDefaultHeight(String[] values) {
        for (String item : values) {
            if (item.contains("height:")) {
                return Integer.parseInt(item.trim().split(":")[1]);
            }
        }
        return -1;
    }
    private static int getDefaultWidth(String[] values) {
        for (String item : values) {
            if (item.contains("width:")) {
                return Integer.parseInt(item.trim().split(":")[1]);
            }
        }
        return -1;
    }
    private static Color getDefaultStroke(String[] values) {
        for (String item : values) {
            if (item.contains("stroke:")) {
                String seperated = item.trim().split(":")[1];
                int start = seperated.lastIndexOf("(") + 1;
                int end = seperated.indexOf(")");
                if (item.contains("RGB")) {
                    String[] rgbValues = seperated.trim().substring(start, end).trim().split(",");
                    return new Color(Integer.parseInt(rgbValues[0]), Integer.parseInt(rgbValues[1]),
                                                                    Integer.parseInt(rgbValues[2]));
                } else {
                    return BlockDefinitionsParser.getMapColor().get(seperated.trim().substring(start, end));
                }
            }
        }
        return null;
    }
    private  static int getDefaultHitPoints(String[] values) {
        for (String item : values) {
            if (item.contains("hit_points:")) {
                return Integer.parseInt(item.trim().split(":")[1]);
            }
        }
        return -1;
    }

    public static String getSymbol(String[] values) {
        for (String item : values) {
            if (item.equals("bdef")) {
                continue;
            }
            String[] singleProperty = item.trim().split(":");
            if (singleProperty[0].toLowerCase().contains("symbol")) {
                return singleProperty[1];
            }
        }
        throw new RuntimeException("BAD VALUES AT BLOCKS DEFINITIONS FILE");
    }

    public static int getBlockWidth(String[] values, DefaultBlock dBlock) {
        for (String item : values) {
            if (item.contains("width:")) {
                return Integer.parseInt(item.trim().split(":")[1]);
            }
        }
        if (dBlock.getWidth() != -1) {
            return dBlock.getWidth();
        }
        throw new RuntimeException("BAD VALUES AT BLOCKS DEFINITIONS FILE");
    }

    public static int getBlockHeight(String[] values, DefaultBlock dBlock) {
        for (String item : values) {
            if (item.contains("height:")) {
                return Integer.parseInt(item.trim().split(":")[1]);
            }
        }
        if (dBlock.getHeight() != -1) {
            return dBlock.getHeight();
        }
        throw new RuntimeException("BAD VALUES AT BLOCKS DEFINITIONS FILE");
    }
    public static int getHitPoints(String[] values, DefaultBlock dBlock) {
        for (String item : values) {
            if (item.contains("hit_points:")) {
                return Integer.parseInt(item.trim().split(":")[1]);
            }
        }
        if (dBlock.getHitPoints() != -1) {
            return dBlock.getHitPoints();
        }
        throw new RuntimeException("BAD VALUES AT BLOCKS DEFINITIONS FILE");
    }
    public static Color getStroke(String[] values, DefaultBlock dBlock) {
        for (String item : values) {
            if (item.contains("stroke:")) {
                String seperator = item.trim().split(":")[1];
                int start = seperator.indexOf("(") + 1;
                int end = seperator.indexOf(")");
                return getMapColor().get(seperator.trim().substring(start, end));
            }
        }
        if (dBlock.getStroke() != null) {
            return dBlock.getStroke();
        }
        return null;
    }

    public static List<FillK> getFillKList(String[] values) {
        Map<String, Color> colorMap = BlockDefinitionsParser.getMapColor();
        List<FillK> fillKlist = new ArrayList<FillK>();
        for (String item : values) {
            if (item.contains("fill:image")) {
                int start = item.indexOf("(") + 1;
                int end = item.lastIndexOf(")");
                String imagePath = item.substring(start, end);
                fillKlist.add(new FillK(1, imagePath, null));
            }
            if (item.contains("fill-") && item.contains(":image")) {
                //TODO fill K with image
                String[] separator = item.trim().split(":");
                int start = separator[1].indexOf("(") + 1;
                int end = separator[1].lastIndexOf(")");
                String imagePath = separator[1].substring(start, end);
                int kValue = Integer.parseInt(separator[0].trim().split("-")[1]);
                fillKlist.add(new FillK(kValue, imagePath, null));
            }
            if (item.contains("fill:color")) {
                if (item.contains("RGB")) {
                    String[] seperator = item.trim().split(":");
                    int start = seperator[1].lastIndexOf("(") + 1;
                    int end = seperator[1].indexOf(")");
                    String rgbValues = seperator[1].substring(start, end);
                    String[] rgbL = rgbValues.split(",");
                    int redValue = Integer.parseInt(rgbL[0]);
                    int greenValue = Integer.parseInt(rgbL[1]);
                    int blueValue = Integer.parseInt(rgbL[2]);
                    fillKlist.add(new FillK(1, "", new Color(redValue, greenValue, blueValue)));
                } else {
                    String[] seperator = item.trim().split(":");
                    int start = seperator[1].lastIndexOf("(") + 1;
                    int end = seperator[1].indexOf(")");
                    String colorName = seperator[1].substring(start, end);
                    if (colorMap.containsKey(colorName.toLowerCase())) {
                        fillKlist.add(new FillK(1, "", colorMap.get(colorName.toLowerCase())));
                    }
                }
            }
            if (item.contains("fill-") && item.contains(":color")) {
                int kValue = Integer.parseInt(item.trim().split(":")[0].trim().split("-")[1]);
                String[] separator = item.trim().split(":");
                int start = separator[1].lastIndexOf("(") + 1;
                int end = separator[1].indexOf(")");
                if (item.contains("RGB")) {
                    String rgbValues = separator[1].trim().substring(start, end);
                    String[] colorValues = rgbValues.trim().split(",");
                    int redValue = Integer.parseInt(colorValues[0]);
                    int greenValue = Integer.parseInt(colorValues[1]);
                    int blueValue = Integer.parseInt(colorValues[2]);
                    Color c = new Color(redValue, greenValue, blueValue);
                    fillKlist.add(new FillK(kValue, "", c));
                } else {
                    String colorName = separator[1].substring(start, end);
                    if (colorMap.containsKey(colorName)) {
                        Color c = colorMap.get(colorName);
                        fillKlist.add(new FillK(kValue, "", c));
                    } else {
                        throw new RuntimeException("MISSING SPACER WIDTH");
                    }
                }
            }
        }
        return fillKlist;
    }
    public static Map<String, Color> getMapColor() {
        Map<String,Color> colorMap = new TreeMap<String, Color>();
        colorMap.put("red", Color.RED);
        colorMap.put("black", Color.black);
        colorMap.put("cyan", Color.cyan);
        colorMap.put("blue", Color.blue);
        colorMap.put("gray", Color.gray);
        colorMap.put("lightgray", Color.lightGray);
        colorMap.put("green", Color.green);
        colorMap.put("orange", Color.orange);
        colorMap.put("pink", Color.pink);
        colorMap.put("white", Color.white);
        colorMap.put("yellow", Color.yellow);
        return colorMap;
    }

    public static int getSpacerWidth(String[] values) {
        for (String item : values) {
            if (item.contains("width:")) {
                return Integer.parseInt(item.trim().split(":")[1]);
            }
        }
        throw new RuntimeException("BAD SPACER DEF ARGS");
    }
    public static String getSpacerSymbol(String[] values) {
        for (String item : values) {
            if (item.contains("symbol:")) {
                return item.trim().split(":")[1];
            }
        }
        throw new RuntimeException("MISSING SPACER SYMBOL ARG");
    }
}
