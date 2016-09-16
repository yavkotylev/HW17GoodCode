package ru.sbt.test.refactoring;

/**
 * Created by Yaroslav on 16.09.16.
 */
public class Position {
    private int x;
    private int y;
    private final Field field;

    public Position(int y, int x, Field field) {
        this.field = field;
        this.y = y;
        this.x = x;
    }

    public void moveForward(Orientation orientation) {
        switch (orientation) {
            case NORTH:
                y++;
                break;
            case WEST:
                x--;
                break;
            case SOUTH:
                y--;
                break;
            case EAST:
                x++;
                break;
        }

        if (x > field.getEdgeX() || y > field.getEdgeY()) throw new TractorInDitchException();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
