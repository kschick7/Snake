import java.util.ArrayList;

public class GameInfo implements GameState {

	private State gameState;
	private int score;
	private Snake player;
	private ArrayList<Pixel> apples;

	public GameInfo() {
		gameState = State.MAIN_MENU;
		score = 0;
		player = new Snake();
		apples = new ArrayList<>();
	}

	public State getGameState() {
		return gameState;
	}

	public void setGameState(State _gameState) {
		gameState = _gameState;
	}

	public int getScore() {
		return score;
	}

	public void incrementScore() {
		++score;
	}

	public Snake getPlayer() {
		return player;
	}

	public ArrayList<Pixel> getApples() {
		return apples;
	}

	public void reset() {
		player.reset();
		score = 0;
		apples.clear();
	}

}