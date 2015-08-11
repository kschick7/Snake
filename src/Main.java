import javax.swing.*;
import java.awt.*;

public class Main {

	public static void main(String[] args){
		Gui window = new Gui();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setSize(500,500);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width/2-250, dim.height/2-250);
		window.setResizable(false);
		window.setVisible(true);

		Game game = new Game(window);
	}
}
