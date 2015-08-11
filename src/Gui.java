import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
	JLabel title;
	JButton start;
	Container frame;
	boolean inGame;

	
	
	public Gui(){
		super("Snake!");
		inGame = false;
		frame = getContentPane();
		setLayout(null);
		frame.setBackground(Color.WHITE);
		title = new JLabel("SNAKE", SwingConstants.CENTER);
		title.setFont(new Font("Courier New", Font.BOLD, 100));
		title.setForeground(Color.green);
		title.setBounds(100,100,300,150);
		
		start = new JButton("Start");
		start.setBackground(Color.WHITE);
		start.setFont(new Font("Courier New", NORMAL, 36));
		start.setBounds(178,250,144,36);
		start.setForeground(Color.BLUE);
		start.setBorderPainted(false);
		
		
		start.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					inGame = true;
					remove(title);
					remove(start);
					getContentPane().setBackground(Color.CYAN);
				}
			}
				
		);

		add(title);
		add(start);
		
	}

	public void setBackground(Color color) {
		this.getContentPane().setBackground(color);
	}
}

