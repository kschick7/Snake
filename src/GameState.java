
public interface GameState {
    int NUM_STATES = 4;

    enum State {
        MAIN_MENU, IN_GAME, GAME_OVER, PAUSED
    }
}
