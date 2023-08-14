package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * This class creates and serves as the menu bar at the top of the game screen.
 */
public class MenuBar extends JMenuBar {
    /**
     * The game panel that will be used to draw the menu bar onto.
     */
    public final String QUIT = "Quit";
    /**
     * The command associated with the Save option.
     */

    public final String SAVE = "Save";


    private final GamePanel myGamePanel;

    /**
     * Constructs the whole menu bar displayed at the top of the screen in the game.
     *
     * @param theGamePanel The game panel to place the menu bar onto.
     */
    public MenuBar(GamePanel theGamePanel) {
        super();
        myGamePanel = theGamePanel;
        JMenu options = new JMenu("Options");
        JMenuItem quit = new JMenuItem("Quit");
        // Here we check if an event happens with the quit button (the player clicks it)
        // then we exit if so
        quit.addActionListener(e -> System.exit(0));
        // If the player clicks save, we call the save method
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(e -> myGamePanel.getSaveLoad().save());

        // If the player clicks on main menu, we set the game state to the menu
        JMenuItem mainMenu = new JMenuItem("Main Menu");
        mainMenu.addActionListener(e -> myGamePanel.setMyGameState(0));

        options.add(quit);
        options.addSeparator();
        options.add(save);
        options.addSeparator();
        options.add(mainMenu);
        JMenu about = new JMenu("About");
        JMenuItem des = new JMenuItem("Description");

        des.addActionListener(e -> myGamePanel.setMyAboutState(myGamePanel.getMyGameState() == 2 && !myGamePanel.getMyAboutState()));
        about.add(des);
        add(options);
        add(about);
    }
}
