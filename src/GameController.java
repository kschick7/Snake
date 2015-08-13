import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class GameController implements GameState {
    public static final int GAME_FPS = 5;

    private GameInfo gameInfo;
    private View view;
    private ArrayList< ArrayList<Component> > stateComponentGroups;

    public GameController(GameInfo _gameInfo, View _view) {
        gameInfo = _gameInfo;
        view = _view;

        stateComponentGroups = new ArrayList<>(NUM_STATES);
        for (int i = 0; i < NUM_STATES; ++i)
            stateComponentGroups.add(new ArrayList<Component>());

        view.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeGameState(State.IN_GAME);
            }
        });

        initComponentGroups();
    }

    private void initComponentGroups() {
        stateComponentGroups.get(State.MAIN_MENU.ordinal()).add(view.getMenuTitle());
        stateComponentGroups.get(State.MAIN_MENU.ordinal()).add(view.getStartButton());
    }

    public void launch() {
        view.addGroup(stateComponentGroups.get(State.MAIN_MENU.ordinal()));
        new Timer(1000 / GAME_FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                run();
            }
        }).start();
    }

    private void run() {
        /*
        switch(gameInfo.getGameState()) {
            case MAIN_MENU:
                view.setBackground(Color.BLACK);
                //gameInfo.setGameState(State.IN_GAME);
                //view.removeAll();
                view.addGroup(stateComponentGroups.get(State.MAIN_MENU.ordinal()));
                break;
            case IN_GAME:
                view.setBackground(Color.BLUE);
                //gameInfo.setGameState(State.MAIN_MENU);
                //view.removeAll();
                //view.addGroup(inGameComponents);
                view.addGroup(stateComponentGroups.get(State.IN_GAME.ordinal()));
                break;
        }*/
    }

    private void changeGameState(State nextState) {
        view.removeGroup(stateComponentGroups.get(gameInfo.getGameState().ordinal()));
        gameInfo.setGameState(nextState);
        view.addGroup(stateComponentGroups.get(nextState.ordinal()));
        view.refresh();
    }
}
