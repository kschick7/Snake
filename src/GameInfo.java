
public class GameInfo implements GameState {

	private State gameState;
	private int score;
	private Snake player;

	public GameInfo() {
		gameState = State.MAIN_MENU;
		score = 0;
		player = new Snake();
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
}