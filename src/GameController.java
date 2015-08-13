import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameController {
    public static final int GAME_FPS = 5;

    private GameInfo gameInfo;
    private View view;

    public GameController(GameInfo _gameInfo, View _view) {
        gameInfo = _gameInfo;
        view = _view;
    }

    public void launch() {
        new Timer(1000 / GAME_FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                run();
            }
        }).start();
    }

    public void run() {
        switch(gameInfo.getGameState()) {
            case MAIN_MENU:
                view.setBackground(Color.BLACK);
                gameInfo.setGameState(GameInfo.State.IN_GAME);
                break;
            case IN_GAME:
                view.setBackground(Color.BLUE);
                gameInfo.setGameState(GameInfo.State.MAIN_MENU);
                break;
        }
    }
}
