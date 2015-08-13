
public class GameInfo implements GameState {

	private State gameState;

	public GameInfo() {
		this.gameState = State.MAIN_MENU;
	}

	public State getGameState() {
		return gameState;
	}

	public void setGameState(State gameState) {
		this.gameState = gameState;
	}
}