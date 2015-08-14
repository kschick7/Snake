import javax.swing.*;
import java.awt.*;

/**
 * Basic building block of the Gui
 */
public class Pixel extends JLabel{

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    public Pixel(Coordinate coordinate) {
        super("");
        setOpaque(true);
        setBackground(Color.RED);
        setBounds(coordinate.getX() * WIDTH, coordinate.getY() * HEIGHT, WIDTH, HEIGHT);
    }
}
