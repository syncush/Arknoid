package listeners;
import java.util.ArrayList;
/**
 * The class represents a collection of listeners.
 * @author Daniel Hermon
 */
public class ListenerCollection {
    // Class member.
    private ArrayList<HitListener> listenerList;
    /**
     * The method creates a new collection of listeners.
     */
    public ListenerCollection() {
        this.listenerList = new ArrayList<HitListener>();
    }
    /**
     * The method adds a new listener.
     * @param hl -The listener we need to add.
     */
    public void addListener(HitListener hl) {
        this.listenerList.add(hl);
    }
    /**
     * The method removes a new listener.
     * @param hl -The listener we need to removes.
     */
    public void removeListener(HitListener hl) {
        this.listenerList.remove(hl);
    }
    /**
     * The method returns the array of listeners.
     * @return the array of listeners.
     */
    public ArrayList<HitListener> getArray() {
        return this.listenerList;
    }
}