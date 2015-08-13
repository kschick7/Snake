import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class View {

	private JLabel menuTitle;
	private JButton startButton;
	private JFrame frame;

	public View(){
		frame = new JFrame("Snake");
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-250, dim.height/2-250);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setBackground(Color.WHITE);

		menuTitle = new JLabel("SNAKE", SwingConstants.CENTER);
		menuTitle.setFont(new Font("Courier New", Font.BOLD, 100));
		menuTitle.setForeground(Color.green);
		menuTitle.setBounds(100,100,300,150);
		frame.add(menuTitle, BorderLayout.CENTER);

		startButton = new JButton("Start");
		startButton.setBackground(Color.WHITE);
		startButton.setFont(new Font("Courier New", JFrame.NORMAL, 36));
		startButton.setBounds(178, 250, 144, 36);
		startButton.setForeground(Color.BLUE);
		startButton.setBorderPainted(false);
		startButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.remove(menuTitle);
						frame.remove(startButton);
						frame.getContentPane().setBackground(Color.CYAN);
					}
				}
		);
		frame.add(startButton);

		Scanner keyboard = new Scanner(System.in);
		//keyboard.addActionListener
	}

	public void setBackground(Color color) {
		frame.getContentPane().setBackground(color);
	}

	public void setStateElements(GameInfo.State state) {
		frame.removeAll();
		switch(state) {
			case MAIN_MENU:
				frame.add(menuTitle);
				frame.add(startButton);
				break;
		}
	}

	public void startGame() {

	}
}

