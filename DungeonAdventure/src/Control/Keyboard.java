package Control;

import View.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private static final long serialVersionUID = 123456789L;
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    private final GamePanel myGamePanel;

    public Keyboard(GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
    }

    @Override
    public void keyTyped(final KeyEvent e) {

    }
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
                        myGamePanel.saveLoad.load();
                        myGamePanel.setMyGameState(2);
//                        if(myGamePanel.getMyHero().getChName().equals("Thief")) {
//                            //add in the sprites
//                            myGamePanel.getMyHero().getHeroesImage();
//                        } else if(myGamePanel.getMyHero().getChName().equals("Warrior")) {
////                            myGamePanel.setMyHero(2);
//                            myGamePanel.getMyHero().getHeroesImage();
//                        } else {
//                             myGamePanel.getMyHero().getHeroesImage();
//                        }
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
                    myGamePanel.getMyWinPage().setCommandToLoadGame();
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
        }
    }
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
