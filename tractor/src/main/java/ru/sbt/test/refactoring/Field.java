package ru.sbt.test.refactoring;

/**
 * Created by Yaroslav on 16.09.16.
 */
public class Field {
    private final int edgeX;
    private final int edgeY;

    public Field(int edgeX, int edgeY) {
        this.edgeX = edgeX;
        this.edgeY = edgeY;
    }

    public int getEdgeX() {
        return edgeX;
    }

    public int getEdgeY() {
        return edgeY;
    }
}
