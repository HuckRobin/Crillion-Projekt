package de.thd.graf.crillion.graphics.basicobjects;

import de.thd.graf.crillion.gameview.GameView;

import java.util.*;

/**
 * creates the MovementPatterns
 */
public class MovementPatterns extends GameObject {
    private int randomInt;
    private final Random random;
    private final Position middle;
    private final XAxisComparator xAxisComparator;


    private final ArrayList<Position> square;
    private final ArrayList<Position> zigZag;
    private final ArrayList<Position> zero;
    private final ArrayList<Position> xFirst;
    private final Position[] pointsSquareArray;
    private final Position[] pointsZigZagArray;

    private final HashMap<String, ArrayList<Position>> movementCluster;

    private final ArrayList<Position> yFirst;
    private final ArrayList<Position> centered;

    private final Comparator<Position> centeredComparator;

    /**
     * creates the MovementPatterns
     *
     * @param gameView
     */
    public MovementPatterns(GameView gameView) {
        super(gameView);
        this.randomInt = 1;
        this.middle = new Position(400, 400);
        this.random = new Random();
        this.position = new Position();
        this.square = new ArrayList<>();
        this.zigZag = new ArrayList<>();
        this.zero = new ArrayList<>();
        this.xFirst = new ArrayList<>();
        this.yFirst = new ArrayList<>();
        this.centered = new ArrayList<>();
        this.xAxisComparator = new XAxisComparator();
        this.movementCluster = new HashMap<>();

        this.pointsSquareArray = new Position[]{
                new Position(30, 30),
                new Position(930, 30),
                new Position(930, 510),
                new Position(30, 510)};
        this.pointsZigZagArray = new Position[]{
                new Position(300, 200),
                new Position(400, 340),
                new Position(500, 200),
                new Position(600, 340),
                new Position(700, 200),
                new Position(800, 340)};

        this.zigZag.addAll(Arrays.asList(pointsZigZagArray));
        this.square.addAll(Arrays.asList(pointsSquareArray));

        this.zero.addAll(square);
        this.zero.addAll(zigZag);

        this.movementCluster.put("Square", square);
        this.movementCluster.put("ZigZag", zigZag);

        Collections.sort(zero);
        this.movementCluster.put("Zero", zero);

        this.yFirst.addAll(zero);
        this.xFirst.addAll(zero);
        Collections.sort(xFirst, new XAxisComparator());
        this.movementCluster.put("XFirst", xFirst);

        Comparator<Position> yAxisComparator = new Comparator<Position>() {
            @Override
            public int compare(Position obj1, Position obj2) {
                return (int) Math.signum(obj1.y - obj2.y);
            }
        };

        Collections.sort(yFirst, yAxisComparator);
        movementCluster.put("YFirst", yFirst);
        centered.addAll(zero);

        this.centeredComparator = (Position obj1, Position obj2) -> {
            return (int) Math.signum(Math.abs(position.distance(obj1.x, obj1.y) - position.distance(middle.x, middle.y)) - Math.abs(position.distance(obj2.x, obj2.y) - position.distance(middle.x, middle.y)));
        };

        Collections.sort(centered, centeredComparator);
        movementCluster.put("Center", centered);
    }

    @Override
    public void addToCanvas() {

    }


    @Override
    protected void updateStatus() {

    }

    /**
     * get the input pattern from movementCluster hashmap
     *
     * @param pattern
     * @return with index of pattern
     * @see #movementCluster
     */
    public ArrayList<Position> getPattern(String pattern) {
        return movementCluster.get(pattern);
    }

    /**
     * get a random pattern out of the hashmap movementCluster
     *
     * @return
     */
    public ArrayList<Position> getRandomPattern() {
        randomInt = random.nextInt(2);
        if (randomInt == 1) {
            return movementCluster.get("Square");
        } else {
            return movementCluster.get("ZigZag");
        }
    }


    /**
     * updates position of the objects by speedInPixel
     */
    public void updatePosition() {

    }
}
