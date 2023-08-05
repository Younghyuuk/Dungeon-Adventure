package View;

import javax.swing.*;
/** This Class is the main JFrame that will hold all the other JPanels*/
public class MainFrame extends JFrame {

    private static final int SCREEN_WIDTH = 700;
    private static final int SCREEN_HEIGHT = 500;

    /** Constructor that creates the MainFrame object. */
    public MainFrame() {
        super("Dungeon Adventure");
        setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        GamePanel gamePanel = new GamePanel();
        MenuBar menuBar = new MenuBar(gamePanel, this);
        setJMenuBar(menuBar);
        add(gamePanel);
        pack();
        gamePanel.startGame();
    }
}
