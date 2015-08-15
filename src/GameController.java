import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


public class GameController implements GameState {
    public static final int GAME_FPS = 15;
    private static final int MAX_NUM_APPLES = 3;

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
                                gameInfo.getPlayer().setNextDirection(Snake.Direction.UP);
                            break;
                        case KeyEvent.VK_DOWN:
                            if (gameInfo.getPlayer().getDirection() != Snake.Direction.UP)
                                gameInfo.getPlayer().setNextDirection(Snake.Direction.DOWN);
                            break;
                        case KeyEvent.VK_LEFT:
                            if (gameInfo.getPlayer().getDirection() != Snake.Direction.RIGHT)
                                gameInfo.getPlayer().setNextDirection(Snake.Direction.LEFT);
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (gameInfo.getPlayer().getDirection() != Snake.Direction.LEFT)
                                gameInfo.getPlayer().setNextDirection(Snake.Direction.RIGHT);
                            break;
                        case KeyEvent.VK_ESCAPE:
                        case KeyEvent.VK_P:
                        case KeyEvent.VK_SPACE:
                            changeGameState(State.PAUSED);
                    }
                } else if (gameInfo.getGameState() == State.PAUSED && e.getKeyCode() == KeyEvent.VK_SPACE) {
                    changeGameState(State.IN_GAME);
                } else if (gameInfo.getGameState() == State.GAME_OVER && e.getKeyCode() == KeyEvent.VK_SPACE) {
                    changeGameState(State.MAIN_MENU);
                    reset();
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
        stateComponentGroups.get(State.IN_GAME.ordinal()).add(view.getScoreLabel());
        stateComponentGroups.get(State.PAUSED.ordinal()).add(view.getPausedLabel());
        stateComponentGroups.get(State.PAUSED.ordinal()).add(view.getContinueLabel());
        stateComponentGroups.get(State.GAME_OVER.ordinal()).add(view.getContinueLabel());
        stateComponentGroups.get(State.GAME_OVER.ordinal()).add(view.getGameOverLabel());
    }

    public void launch() {
        view.addGroup(stateComponentGroups.get(State.MAIN_MENU.ordinal()));
        view.refresh();
        new Timer(1000 / GAME_FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameInfo.getGameState() == State.IN_GAME)
                    run();
            }
        }).start();
        new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameInfo.getGameState() == State.IN_GAME && gameInfo.getApples().size() < MAX_NUM_APPLES)
                    spawnApple();
            }
        }).start();
    }

    private void run() {
        gameInfo.getPlayer().updateDirection();
        move();
        view.refresh();
    }

    private void changeGameState(State nextState) {
        view.removeGroup(stateComponentGroups.get(gameInfo.getGameState().ordinal()));
        gameInfo.setGameState(nextState);
        view.addGroup(stateComponentGroups.get(nextState.ordinal()));
        if (nextState == State.IN_GAME && snakeView.isEmpty()) {
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
        if (nextCoordinate.isOutOfBounds(50, 50) || gameInfo.getPlayer().isCollision(nextCoordinate)) {
            gameOver();
            return;
        }
        checkForApples(nextCoordinate);

        gameInfo.getPlayer().insertFront(nextCoordinate);
        snakeView.addFirst(new Pixel(nextCoordinate, Color.red));
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
            snakeView.addLast(new Pixel(coordinate, Color.red));
            view.add(snakeView.getLast());
        }
    }

    private void spawnApple() {
        Random random = new Random();
        Pixel apple = new Pixel(new Coordinate(random.nextInt(50), random.nextInt(50)), Color.yellow);
        view.add(apple);
        gameInfo.getApples().add(apple);
    }

    private void checkForApples(Coordinate coordinate) {
        ArrayList<Pixel> apples = gameInfo.getApples();
        for (int i = 0; i < apples.size(); ++i) {
            if (apples.get(i).getCoordinate().equals(coordinate)) {
                view.remove(apples.get(i));
                apples.remove(i);
                gameInfo.incrementScore();
                gameInfo.getPlayer().incrGrowCounter();
                view.getScoreLabel().setText(Integer.toString(gameInfo.getScore()));
            }
        }
    }

    public void setSnakeColor(Color color) {
        for (Pixel pixel : snakeView) {
            pixel.setBackground(color);
        }
    }

    public void gameOver() {
        changeGameState(State.GAME_OVER);
        setSnakeColor(Color.GRAY);
    }

    public void reset() {
        for (Pixel pixel : snakeView)
            view.remove(pixel);
        snakeView.clear();
        gameInfo.reset();

    }
}
