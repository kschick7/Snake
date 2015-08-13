public class GameInfo {
	public enum State {
		MAIN_MENU, IN_GAME, GAME_OVER, PAUSED
	}

	private State gameState;

	public GameInfo() {
		this.gameState = State.MAIN_MENU;
	}


	public void switchState(State nextState) {

	}

	public State getGameState() {
		return gameState;
	}

	public void setGameState(State gameState) {
		this.gameState = gameState;
	}
}