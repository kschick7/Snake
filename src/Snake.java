import java.util.Arrays;
import java.util.LinkedList;

public class Snake {
    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    private static final Coordinate START_COORDS[] = {new Coordinate(24, 22),
                                                      new Coordinate(24, 22),
                                                      new Coordinate(24, 22)};

    private Direction direction;
    private Direction nextDirection;
    private LinkedList<Coordinate> coordinates;
    private int growCounter;

    public Snake() {
        direction = Direction.UP;
        nextDirection = Direction.UP;
        growCounter = 0;
        coordinates = new LinkedList<>(Arrays.asList(START_COORDS));
    }

    public Direction getDirection() {
        return direction;
    }

    public void updateDirection() {
        direction = nextDirection;
    }

    public Coordinate getFront() {
        return coordinates.getFirst();
    }

    public Coordinate getBack() {
        return coordinates.getLast();
    }

    public LinkedList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void insertFront(Coordinate coordinate) {
        coordinates.addFirst(coordinate);
    }

    public void removeBack() {
        coordinates.removeLast();
    }

    public int getGrowCounter() {
        return growCounter;
    }

    public void decrGrowCounter() {
        --this.growCounter;
    }

    public void incrGrowCounter() {
        this.growCounter += 3;
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }

    public boolean isCollision(Coordinate coordinate) {
        for (Coordinate snakeCoord : coordinates) {
            if (snakeCoord.equals(coordinate))
                return true;
        }
        return false;
    }

    public void reset() {
        coordinates = new LinkedList<>(Arrays.asList(START_COORDS));
    }
}
