import javax.swing.*;
import java.awt.*;

/**
 * Basic building block of the Gui
 */
public class Pixel extends JLabel{

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private Coordinate coordinate;

    public Pixel(Coordinate _coordinate, Color _color) {
        super("");
        setOpaque(true);
        setBackground(_color);
        setBounds(_coordinate.getX() * WIDTH, _coordinate.getY() * HEIGHT, WIDTH, HEIGHT);
        coordinate = _coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
