package View;

import Control.Keyboard;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {

    // Actual GUI screen size.
    private final static int ORIGINAL_SPRITE_SIZE = 16;
    private final static int SCALE = 3;
    private final int mySpriteSize = ORIGINAL_SPRITE_SIZE * SCALE;
    private final int myMaxScreenRow = 12;
    private final int myMaxScreenCol = 16;
    private final int myScreenWidth = myMaxScreenCol * mySpriteSize;
    private final int myScreenHeight = myMaxScreenRow * mySpriteSize;
    private final static int FPS = 60;

    //We may need to pass this from mainframe and play with the gamestate to load new dungeons.=
    private final Dungeon myDungeon = new Dungeon();
    // these values will be dependent on Dungeon Maze array size.
    /**
     * The width of the world map as a 2d array
     */
    private final int myWorldCol = myDungeon.getDungeonWidth() * myDungeon.getRooms()[0][0].getRoomWidth();

    /**
     * The height of the world map as a 2d array
     */
    private final int myWorldRow = myDungeon.getDungeonHeight() * myDungeon.getRooms()[0][0].getRoomHeight();

    private final TileManager myTileM = new TileManager(this);
    private final Thread gameThread = new Thread(this);
    private final Collision myCollision = new Collision(this);
    private final Keyboard myKeyInputs = new Keyboard(this);
    private final TitlePage myTitlePage = new TitlePage(this);
    private final CharacterSelectionPage myCharacterSelectionPage = new CharacterSelectionPage(this);
    private final AboutPage myAboutPage = new AboutPage(this);
    InitiateEntites myIE = new InitiateEntites(this);
    List<Monster> myMonsterArray = myIE.getMyMonsterArray();

    private Heroes myHero;

    private int myGameState;
    private final static int TITLE_STATE = 0;
    private final static int CHARACTER_STATE = 1;
    private final static int PLAY_STATE = 2;
    private boolean myAboutState = false;



    public GamePanel() {
        setPreferredSize(new Dimension(myScreenWidth, myScreenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(myKeyInputs);
        setFocusable(true);
        myGameState = TITLE_STATE;
        setMyHero(1);
    }

    public CharacterSelectionPage getMyCharacterSelectionPage(){
        return myCharacterSelectionPage;
    }
    public int getMyGameState() {
        return myGameState;
    }

    public TitlePage getMyTitlePage() {
        return myTitlePage;

    }

    public void setMyGameState(int theGameState) {
        myGameState = theGameState;
    }

    public void startGame() {
        gameThread.start();
    }

    public int getSpriteSize() {
        return mySpriteSize;
    }

    public int getMyScreenWidth() {
        return myScreenWidth;
    }

    public int getMyScreenHeight() {
        return myScreenHeight;
    }

    public int getMyWorldCol() {
        return myWorldCol;
    }

    public int getMyWorldRow() {
        return myWorldRow;
    }

    public Dungeon getMyDungeon() {
        return myDungeon;
    }

    public Heroes getMyHero() {
        return myHero;
    }

    public Collision getMyCollision() {
        return myCollision;
    }

    public TileManager getMyTileM() {
        return myTileM;
    }

    public void setMyAboutState(boolean theAboutState) {
        myAboutState = theAboutState;
    }
    public boolean getMyAboutState(){
        return myAboutState;
    }

    // For now, 1 is thief, 2 is Priestess, and 3 is Warrior.
    public void setMyHero(int number) {
        if (number == 1) {
            myHero = new Thief(this, myKeyInputs);
        }
        if (number == 2) {
            myHero = new Warrior(this, myKeyInputs);
        }
        if (number == 3) {
            myHero = new Priestess(this, myKeyInputs);
        }
    }

    @Override
    public void run() {
        // Game Loop. Repainting the screen 60 FPS. Credit given to ryisnow.
        double interval = 1000000000 / FPS;
        double delta = 0;
        long pastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - pastTime) / interval;
            pastTime = currentTime;

            // if more than a 16 millisecond has passed than repaint and update the panel;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    // This method will update our model.
    public void update() {
        myHero.update();
    }

    // This method updates our view.
    @Override
    public void paintComponent(Graphics theGraphics) {
        super.paintComponent(theGraphics);
        Graphics2D pen = (Graphics2D) theGraphics;

        if (myGameState == TITLE_STATE) {
            myTitlePage.draw(pen);
            pen.dispose();
        } else if (myGameState == CHARACTER_STATE) {
            myCharacterSelectionPage.draw(pen);
            pen.dispose();
        } else if (myGameState == PLAY_STATE) {
            myTileM.draw(pen);
            myHero.draw(pen);
            for (Monster list : myMonsterArray){
                list.draw(pen);
            }
            if (myAboutState) {
                myAboutPage.draw(pen);
            }
            pen.dispose();
        }
    }
}
