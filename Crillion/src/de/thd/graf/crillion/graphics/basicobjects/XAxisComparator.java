package de.thd.graf.crillion.graphics.basicobjects;

import java.util.Comparator;

/**
 * creates the XAxisComparator
 */
public class XAxisComparator implements Comparator<Position> {
    /**
     * compares obj1 and obj2
     * @param obj1
     * @param obj2
     * @return
     */
    public int compare(Position obj1, Position obj2){
        return (int) Math.signum(obj1.x-obj2.x);
    }
}
