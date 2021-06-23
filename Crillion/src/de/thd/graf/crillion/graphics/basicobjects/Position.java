package de.thd.graf.crillion.graphics.basicobjects;

/**
 * creates the Position
 */
public final class Position {
    /**
     * x position for the objects.
     */
    public double x;
    /**
     * y position for the objects.
     */
    public double y;

    /**
     * creates the positions of the objects
     */
    public Position(int x, int y) {
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

}