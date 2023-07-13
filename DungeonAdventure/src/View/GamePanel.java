package View;

import Control.Keyboard;
import Model.DungeonCharacter;
import Model.Heroes;
import Model.Priestess;
import Model.Thief;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    private final static int ORIGINAL_SPRITE_SIZE = 16;
    private final static int SCALE = 3;
    public final static int GAME_SPRITE_SIZE = ORIGINAL_SPRITE_SIZE * SCALE;

    private final static int MAX_SCREEN_COL = 16;
    private final static int MAX_SCREEN_ROW = 12;

    private final static int FPS = 60;

    public Keyboard keyInputs = new Keyboard();
    private Thread gameThread;

    // This is for testing
    Priestess myPriestess = new Priestess(this, keyInputs);

    public GamePanel(){
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(keyInputs);
        setFocusable(true);
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        // Game Loop. Repainting the screen 60 FPS. Credit given to ryisnow.
        double interval = 1000000000/FPS;
        double delta = 0;
        long pastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
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

    public void update() {
        myPriestess.update();
    }

    @Override
    public void paintComponent(Graphics theGraphics){
        super.paintComponent(theGraphics);
        Graphics2D pen = (Graphics2D) theGraphics;

        // Placeholder for the character.
        myPriestess.draw(pen);

        pen.dispose();
    }
}
