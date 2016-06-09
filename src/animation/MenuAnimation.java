package animation;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import menu.*;
import scoretable.ShowHiScoresTask;

import java.awt.*;
import java.util.*;

/**
 * Created by Dell on 03/06/2016.
 */
public class MenuAnimation<T> implements menu.Menu {
    private String menuTitle;
    private GUI gui;
    private Map<String, Object> map;
    private String stopper;
    private ArrayList<String> options;


    public MenuAnimation(String title, GUI gui) {
        this.gui = gui;
        this.menuTitle = title;
        map = new TreeMap<>();
        options= new ArrayList<>();
    }


    @Override
    public void addSelection(String key, String message, Object returnVal) {
        map.put(key, returnVal);
        options.add(message);
    }

    @Override
    public T getStatus() {
        T temp= (T)map.get(this.stopper);
        return temp;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.yellow);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(400, 80, this.menuTitle, 60);
        int height=270;
        for (String s:this.options) {
            d.drawText(150,height,s,20);
            height+=30;
        }
    }

    @Override
    public boolean shouldStop() {
        Set<String> set = this.map.keySet();
        for (String stopperKey : set) {
            if (this.gui.getKeyboardSensor().isPressed(stopperKey)) {
                this.stopper = stopperKey;
                return true;
            }
        }
        return false;
    }
}
