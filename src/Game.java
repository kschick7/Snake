import javax.swing.*;
import java.awt.*;

public class Game {
	public enum State {
		MAIN_MENU, IN_GAME, GAME_OVER, PAUSED
	}

	private static final int FRAME_DELAY = 1000;

	private Gui window;
	private State gameState;
	//private Timer timer;

	public Game(Gui gui) {
		this.window = gui;
		this.gameState = State.MAIN_MENU;
		//new Timer(FRAME_DELAY, run);
	}

	public void run() {
		window.setBackground(Color.BLACK);
	}

}