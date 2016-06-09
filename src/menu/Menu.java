package menu;

import animation.Animation;

/**
 * Created by Dell on 03/06/2016.
 */
public interface Menu <T> extends Animation {

    void addSelection(String key, String message, T returnVal);

    T getStatus();

}
