import java.util.LinkedList;

public class Snake {
    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private Direction direction;
    private LinkedList<Coordinate> coordinates;
    private int growCounter;

    public Snake() {
        direction = Direction.UP;
        growCounter = 0;
        coordinates = new LinkedList<>();
        coordinates.add(new Coordinate(24, 22));
        coordinates.add(new Coordinate(24, 23));
        coordinates.add(new Coordinate(24, 24));
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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
}
