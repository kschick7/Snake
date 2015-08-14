import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;


public class GameController implements GameState {
    public static final int GAME_FPS = 20;

    private GameInfo gameInfo;
    private View view;
    private ArrayList< ArrayList<Component> > stateComponentGroups;
    private LinkedList<Pixel> snakeView;

    public GameController(GameInfo _gameInfo, View _view) {
        gameInfo = _gameInfo;
        view = _view;
        snakeView = new LinkedList<>();

        stateComponentGroups = new ArrayList<>(NUM_STATES);
        for (int i = 0; i < NUM_STATES; ++i)
            stateComponentGroups.add(new ArrayList<Component>());

        view.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeGameState(State.IN_GAME);
            }
        });

        view.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Blank
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (gameInfo.getGameState() == State.IN_GAME) {
                    switch(e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            if (gameInfo.getPlayer().getDirection() != Snake.Direction.DOWN)
                                gameInfo.getPlayer().setDirection(Snake.Direction.UP);
                            break;
                        case KeyEvent.VK_DOWN:
                            if (gameInfo.getPlayer().getDirection() != Snake.Direction.UP)
                                gameInfo.getPlayer().setDirection(Snake.Direction.DOWN);
                            break;
                        case KeyEvent.VK_LEFT:
                            if (gameInfo.getPlayer().getDirection() != Snake.Direction.RIGHT)
                                gameInfo.getPlayer().setDirection(Snake.Direction.LEFT);
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (gameInfo.getPlayer().getDirection() != Snake.Direction.LEFT)
                                gameInfo.getPlayer().setDirection(Snake.Direction.RIGHT);
                            break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Blank
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
                if (gameInfo.getGameState() == State.IN_GAME)
                    run();
            }
        }).start();
    }

    private void run() {
        move();
        view.refresh();
    }

    private void changeGameState(State nextState) {
        view.removeGroup(stateComponentGroups.get(gameInfo.getGameState().ordinal()));
        gameInfo.setGameState(nextState);
        view.addGroup(stateComponentGroups.get(nextState.ordinal()));
        if (nextState == State.IN_GAME) {
            renderSnake();
        }
        view.refresh();
    }

    public void move() {
        Coordinate nextCoordinate = new Coordinate(gameInfo.getPlayer().getFront());
        switch(gameInfo.getPlayer().getDirection()) {
            case UP:
                nextCoordinate.alterY(-1);
                break;
            case DOWN:
                nextCoordinate.alterY(1);
                break;
            case LEFT:
                nextCoordinate.alterX(-1);
                break;
            case RIGHT:
                nextCoordinate.alterX(1);
                break;
        }
        gameInfo.getPlayer().insertFront(nextCoordinate);
        snakeView.addFirst(new Pixel(nextCoordinate));
        view.add(snakeView.getFirst());
        if (gameInfo.getPlayer().getGrowCounter() == 0) {
            gameInfo.getPlayer().removeBack();
            view.remove(snakeView.getLast());
            snakeView.removeLast();
        } else {
            gameInfo.getPlayer().decrGrowCounter();
        }
    }

    private void renderSnake() {
        for (Coordinate coordinate: gameInfo.getPlayer().getCoordinates()) {
            snakeView.addLast(new Pixel(coordinate));
            view.add(snakeView.getLast());
        }
    }
}
