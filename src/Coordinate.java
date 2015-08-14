
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public Coordinate(Coordinate other) {
        x = other.x;
        y = other.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Adds 'change' to x
    public void alterX(int change) {
        x += change;
    }

    // Adds 'change' to y
    public void alterY(int change) {
        y += change;
    }

    // Returns whether or not this coordinate is out of the grid boundaries (xBound, yBound)
    public boolean isOutOfBounds(int xBound, int yBound) {
        return (x >= xBound || y >= yBound);
    }

    public boolean equals(Coordinate other) {
        return (x == other.x && y == other.y);
    }
}
