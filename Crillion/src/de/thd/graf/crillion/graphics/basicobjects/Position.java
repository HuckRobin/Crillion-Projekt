package de.thd.graf.crillion.graphics.basicobjects;

import java.util.Objects;

/**
 * creates the Position
 */
public final class Position implements Cloneable, Comparable<Position> {
    /**
     * x position for the objects.
     */
    public double x;
    /**
     * y position for the objects.
     */
    public double y;
    private Position obj1;
    private Position obj2;

    /**
     * creates the positions of the objects
     */
    public Position(double x, double y) {
        this.y = y;
        this.x = x;
    }

    /**
     * set x,y variable to (0,0)
     */
    public Position() {
        this(0, 0);

    }

    /**
     * 1 position to the left
     */
    public void left() {
        x--;
    }

    /**
     * 1 position to the right
     */
    public void right() {
        x++;
    }

    /**
     * 1 position up
     */
    public void up() {
        y--;
    }

    /**
     * 1 position down
     */
    public void down() {
        y++;
    }

    /**
     * @param pixel change position by pixel to the left
     */
    public void left(double pixel) {
        x -= pixel;
    }

    /**
     * @param pixel change position by pixel to the right
     */
    public void right(double pixel) {
        x += pixel;
    }

    /**
     * @param pixel change position by pixel up
     */
    public void up(double pixel) {
        y -= pixel;
    }

    /**
     * @param pixel change position by pixel down
     */
    public void down(double pixel) {
        y += pixel;
    }


    @Override
    public Position clone (){
        Position clone = null;
        try{
            clone = (Position) super.clone();
        }
        catch (CloneNotSupportedException ignored){

        }
        return clone;
    }

    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position) o;
        return x == position.x && y== position.y;
    }
    /**
     * Calculates the distance to any other position.
     *
     * @param other Position to calculate the distance to.
     * @return The distance.
     */
    public double distance(Position other) {
        return Math.sqrt(Math.pow((x - other.x), 2) + Math.pow((y - other.y), 2));
    }
    /**
     * Not dependend to konstruktor xy
     * @param x
     * @param y
     * @return
     */
    public double distance(double x, double y){
        return Math.sqrt(Math.pow((x), 2) + Math.pow((y), 2));
    }

    @Override
    public int hashCode(){
        return Objects.hash(x,y);
    }

    @Override
    public String toString() {
        return "Position (" + (int) Math.round(x) + ", " + (int) Math.round(y) + ")";
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param obj the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    public int compareTo(Position obj) {
        return (int) Math.signum(Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2)) - Math.sqrt(Math.pow(obj.x, 2) + Math.pow(obj.y, 2)) + distance(this));

    }
}