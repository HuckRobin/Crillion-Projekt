package de.thd.graf.crillion.graphics.basicobjects;

/**
 * Position for the game objects
 */
public class Position {

    /**
     * Initial x is the position x
     */
    public double x;
    /**
     * Initial y. Is the position y.
     */
    public double y;

    /**
     * Create a Position with selectable coordinates
     *
     * @param x x coordinate for the position
     * @param y y coordinate for the position
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Create a Position with the Coordinates x = 0 and y = 0
     */
    public Position() {
        this(0, 0);
    }

    /**
     * Move Position to left
     */
    public void left() {
        this.x--;
    }

    /**
     * Move Position to left
     *
     * @param movePositionInPixel Value of Pixel to move
     */
    public void left(double movePositionInPixel) {
        this.x = this.x - movePositionInPixel;
    }

    /**
     * Move Position to right
     */
    public void right() {
        this.x++;
    }

    /**
     * Move Position to right
     *
     * @param movePositionInPixel Value of Pixel to move
     */
    public void right(double movePositionInPixel) {
        this.x = this.x + movePositionInPixel;
    }

    /**
     * Move Position up
     */
    public void up() {
        this.y--;
    }

    /**
     * Move position up
     *
     * @param movePositionInPixel Value of Pixel to move
     */
    public void up(double movePositionInPixel) {
        this.y = this.y - movePositionInPixel;
    }

    /**
     * Move position down
     */
    public void down() {
        this.y++;
    }

    /**
     * Move position down
     *
     * @param movePositionInPixel Value of Pixel to move
     */
    public void down(double movePositionInPixel) {
        this.y = this.y + movePositionInPixel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Position (" + (int) Math.round(this.x) + ", " + (int) Math.round(this.y) + ")";
    }
}