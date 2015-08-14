import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class View {

	private JLabel menuTitle;
	private JButton startButton;
	private JFrame frame;
	private JLabel scoreLabel;

	public View(){
		frame = new JFrame("Snake");
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(506,528);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-250, dim.height/2-250);
		frame.setResizable(false);
		frame.setVisible(true);
		setBackground(Color.WHITE);
		frame.setFocusable(true);

		menuTitle = new JLabel("SNAKE", SwingConstants.CENTER);
		menuTitle.setFont(new Font("Courier New", Font.BOLD, 100));
		menuTitle.setForeground(Color.green);
		menuTitle.setBounds(100,100,300,150);

		startButton = new JButton("Start");
		startButton.setBackground(Color.WHITE);
		startButton.setFont(new Font("Courier New", JFrame.NORMAL, 36));
		startButton.setBounds(178, 250, 144, 36);
		startButton.setForeground(Color.BLUE);
		startButton.setBorderPainted(false);

		scoreLabel = new JLabel("0");
		scoreLabel.setForeground(Color.LIGHT_GRAY);
		scoreLabel.setBounds(450, 10, 50, 50);
		frame.add(scoreLabel);
	}

	public void setBackground(Color color) {
		frame.getContentPane().setBackground(color);
	}

	public void removeAll() {
		frame.removeAll();
	}

	public void addGroup(ArrayList<Component> group) {
		for (Component item : group)
			frame.add(item);
	}

	public void removeGroup(ArrayList<Component> group) {
		for (Component item : group)
			frame.remove(item);
	}

	public JLabel getMenuTitle() {
		return menuTitle;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void refresh() {
		frame.revalidate();
		frame.repaint();
	}

	public void add(Component component) {
		frame.add(component);
	}

	public void remove(Component component) {
		frame.remove(component);
	}

	public void addKeyListener(KeyListener keyListener) {
		frame.addKeyListener(keyListener);
	}

	public JLabel getScoreLabel() {
		return scoreLabel;
	}
}

