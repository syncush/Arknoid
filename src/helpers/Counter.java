package helpers;
/**
 * The class represents a simple counter.
 * @author DANIEL
 */
public class Counter {
    private int countingMember;
    /**
     * The method createss a new counter.
     */
    public Counter() {
        this.countingMember = 0;
    }
    /**
     * The method creates a new counter wiht a given counting start point.
     * @param count -Counting start point.
     */
    public Counter(int count) {
        this.countingMember = count;
    }
    /**
     * The method increases the counter value by a given number.
     * @param number The value we increase our counter.
     */
    public void increase(int number) {
        this.countingMember += number;
    }
    /**
     * The method decreases the counter value by a given amound.
     * @param number The amound we decrease our counter.
     */
    public void decrease(int number) {
        this.countingMember -= number;
    }
    /**
     * The method returns the counter value.
     * @return The counter value.
     */
    public int getValue() {
        return countingMember;
    }
    /**
     * The method changes the value of the counter.
     * @param newCount The new counter value.
     */
    public void changeValue(int newCount) {
        this.countingMember = newCount;
    }
}
