package View;

import Control.Keyboard;
import Model.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private final static int ORIGINAL_SPRITE_SIZE = 16;
    private final static int SCALE = 3;
    private final int mySpriteSize = ORIGINAL_SPRITE_SIZE * SCALE;

    private final int myMaxScreenRow = 12;
    private final int myMaxScreenCol = 16;
    private final int myScreenWidth = myMaxScreenCol * mySpriteSize;
    private final int myScreenHeight = myMaxScreenRow * mySpriteSize;
    private final static int FPS = 60;

    // these values will be dependent on Dungeon Maze array size.
    /**
     * The width of the world map as a 2d array
     */
    public final int myWorldCol = 16;
    /**
     * The height of the world map as a 2d array
     */
    public final int myWorldRow = 12;

    /**
     * The width of the world map in pixels
     */
    public final int myWorldWidth = mySpriteSize * myWorldCol;
    /**
     * The height of the world map in pixels
     */
    public final int myWorldHeight = mySpriteSize * myWorldRow;

    private final TileManager myTileM = new TileManager(this);
    private final Keyboard myKeyInputs = new Keyboard();
    private final Thread gameThread = new Thread(this);
    private final Collision myCollision = new Collision(this);
    private Heroes myHero;

    public GamePanel() {
        setPreferredSize(new Dimension(myScreenWidth, myScreenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(myKeyInputs);
        setFocusable(true);
        setMyHero(1);
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

    public Heroes getMyHero() {
        return myHero;
    }

    public Collision getMyCollision() {
        return myCollision;
    }

    public TileManager getMyTileM() {
        return myTileM;
    }

    public int getMyWorldWidth() {
        return myWorldWidth;
    }

    public int getMyWorldHeight() {
        return myWorldHeight;
    }

    public int getMyMaxScreenCol() {
        return myMaxScreenCol;
    }

    public int getMyMaxScreenRow() {
        return myMaxScreenRow;
    }


    // For now, 1 is thief, 2 is Priestess, and 3 is Warrior.
    public void setMyHero(int number) {
        if (number == 1) {
            myHero = new Thief(this, myKeyInputs);
        }
        if (number == 2) {
            myHero = new Priestess(this, myKeyInputs);
        }
        if (number == 3) {
            myHero = new Warrior(this, myKeyInputs);
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

        myTileM.draw(pen);
        myHero.draw(pen); // We are going to need to figure out a way to draw the correct character.
        pen.dispose();
    }
}
