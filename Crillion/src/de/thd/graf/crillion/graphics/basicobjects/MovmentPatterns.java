package de.thd.graf.crillion.graphics.basicobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MovmentPatterns {
    private final HashMap<String, ArrayList<Position>> hashMap;

    private final ArrayList<Position> square;
    private final ArrayList<Position> zigzag;

    private final Random random;

    public MovmentPatterns() {
        this.hashMap = new HashMap<>();
        this.square =
                new ArrayList<>(List.of(
                        new Position(30, 30),
                        new Position(930, 30),
                        new Position(930, 510),
                        new Position(30, 510)));
        this.zigzag =
                new ArrayList<>(List.of(
                        new Position(300, 200),
                        new Position(400, 340),
                        new Position(500, 200),
                        new Position(600, 340),
                        new Position(700, 200),
                        new Position(800, 340)));
        this.hashMap.put("Square", square);
        this.hashMap.put("ZigZag", zigzag);
        this.random = new Random();
    }

    public String getRandomPattern(){
        if (random.nextBoolean()) {
            return "Square";
        } else {
            return "ZigZag";
        }
    }

    public ArrayList getPattern(String pattern){
        return hashMap.get(pattern);
    }
}
