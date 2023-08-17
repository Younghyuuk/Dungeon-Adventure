package Control;

import View.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class tracks all the key movements made by the player.
 */
public class Keyboard implements KeyListener {
    /**
     * Boolean representing if the player has pressed an "up" key.
     */
    public boolean up;
    /**
     * Boolean representing if the player has pressed a "down" key.
     */
    public boolean down;
    /**
     * Boolean representing if the player has pressed a "left" key.
     */
    public boolean left;
    /**
     * Boolean representing if the player has pressed a "right" key.
     */
    public boolean right;
    /**
     * The game panel that the key inputs will interact with.
     */
    private final GamePanel myGamePanel;

    /**
     * Initializes the class with the game panel needed to control player movement.
     *
     * @param theGamePanel The game panel that will display the character when moving.
     */
    public Keyboard(GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
    }

    @Override
    public void keyTyped(final KeyEvent e) {}

    /**
     * Method that occurs when a key on the keyboard is pressed.
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        if (myGamePanel.getMyGameState() == 0) {
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_W:
                    myGamePanel.getMyTitlePage().setCommandToNewGame();
                    break;
                case KeyEvent.VK_S:
                    myGamePanel.getMyTitlePage().setCommandToLoadGame();
                    break;
                case KeyEvent.VK_ENTER:
                    if (myGamePanel.getMyTitlePage().getMyCommandNum() == 0) {
                        myGamePanel.setNewGame();
                        myGamePanel.setMyGameState(1);
                    }
                    if(myGamePanel.getMyTitlePage().getMyCommandNum() == 1) {
                        myGamePanel.setNewGame();
                        myGamePanel.getSaveLoad().load();
                        System.out.println(myGamePanel.getWinCount());
                        myGamePanel.setMyGameState(2);

                    }
            }
        } else if (myGamePanel.getMyGameState() == 1) {
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_D:
                    myGamePanel.getMyCharacterSelectionPage().incMyCommandNum();
                    if (myGamePanel.getMyCharacterSelectionPage().getMyCommandNum() > 2) {
                        myGamePanel.getMyCharacterSelectionPage().setMyCommandNum(0);
                    }
                    break;
                case KeyEvent.VK_A:
                    myGamePanel.getMyCharacterSelectionPage().decMyCommandNum();
                    if (myGamePanel.getMyCharacterSelectionPage().getMyCommandNum() < 0) {
                        myGamePanel.getMyCharacterSelectionPage().setMyCommandNum(2);
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if (myGamePanel.getMyCharacterSelectionPage().getMyCommandNum() == 0) {
                        myGamePanel.setMyHero(1);
                        myGamePanel.setMyGameState(2);
                    } else if (myGamePanel.getMyCharacterSelectionPage().getMyCommandNum() == 1) {
                        myGamePanel.setMyHero(2);
                        myGamePanel.setMyGameState(2);
                    } else {
                        myGamePanel.setMyHero(3);
                        myGamePanel.setMyGameState(2);
                    }
            }
        } else if (myGamePanel.getMyGameState() == 2) {
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_W:
                    up = true;
                    break;
                case KeyEvent.VK_S:
                    down = true;
                    break;
                case KeyEvent.VK_A:
                    left = true;
                    break;
                case KeyEvent.VK_D:
                    right = true;
                    break;
                default:
                    break;
            }
        } else if (myGamePanel.getMyGameState() == 3) {
            int code = e.getKeyCode();
            if (code == KeyEvent.VK_ENTER) {
                if (myGamePanel.getMyHero().isAlive()){
                    myGamePanel.setMyGameState(2);
                } else {
                    myGamePanel.setMyGameState(4);
                }
            }
        } else if (myGamePanel.getMyGameState() == 4) {
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_W:
                    myGamePanel.getMyGameOver().setCommandToNewGame();
                    break;
                case KeyEvent.VK_S:
                    myGamePanel.getMyGameOver().setCommandToLoadGame();
                    break;
                case KeyEvent.VK_ENTER:
                    if (myGamePanel.getMyGameOver().getMyCommandNum() == 0) {
                        myGamePanel.resetGame();
                        myGamePanel.setMyGameState(2);
                    } else {
                        System.exit(0);
                    }
                    break;
            }
        } else if (myGamePanel.getMyGameState() == 5) {
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_W:
                    myGamePanel.getMyWinPage().setCommandToNewGame();
                    break;
                case KeyEvent.VK_S:
                    myGamePanel.getMyWinPage().setCommandToQuitGame();
                    break;
                case KeyEvent.VK_ENTER:
                    if (myGamePanel.getMyWinPage().getMyCommandNum() == 0) {
                        myGamePanel.resetGame();
                        myGamePanel.setMyGameState(2);
                    } else {
                        System.exit(0);
                    }
                    break;
            }
        }
    }

    /**
     * Method that occurs when a key on the keyboard is released.
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(final KeyEvent e) {
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_W:
                    up = false;
                    break;
                case KeyEvent.VK_S:
                    down = false;
                    break;
                case KeyEvent.VK_A:
                    left = false;
                    break;
                case KeyEvent.VK_D:
                    right = false;
                    break;
                default:
                    break;
            }
    }
}
