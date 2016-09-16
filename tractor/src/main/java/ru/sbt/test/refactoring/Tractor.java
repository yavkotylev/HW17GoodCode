package ru.sbt.test.refactoring;

public class Tractor {

    private final Field field;
    private final Position position;
    private Orientation orientation;

    public Tractor() {
        field = new Field(5, 5);
        position = new Position(0, 0, field);
        orientation = Orientation.NORTH;
    }

    public Tractor(Field field, Position position) {
        this.field = field;
        this.position = position;
    }

    public Tractor(Orientation orientation, Position position, Field field) {
        this.orientation = orientation;
        this.position = position;
        this.field = field;
    }

    public void move(String command) {
        if (command.equals("F")) {
            moveForwards();
        }

        if (command.equals("T")) {
            turnClockwise();
        }
    }

    public void moveForwards() {
        position.moveForward(orientation);
    }

    public void turnClockwise() {
        if (orientation.ordinal() == 0) {
            orientation = orientation.values()[orientation.values().length - 1];
        } else {
            orientation = orientation.values()[orientation.ordinal() - 1];
        }
    }

    public int getPositionX() {
        return position.getX();
    }

    public int getPositionY() {
        return position.getY();
    }

    public Orientation getOrientation() {
        return orientation;
    }
}