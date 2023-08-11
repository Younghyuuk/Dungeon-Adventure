package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class MenuBar extends JMenuBar {

    /**
     * The command associated with the Quit option.
     */
    public final String QUIT = "Quit";
    /**
     * The command associated with the Save option.
     */
<<<<<<< HEAD
    public final String SAVE = "Save";
    private final long serialversionUID = -12345678;
=======
    public static final String SAVE = "Save";

>>>>>>> 0e48ed96ef24b99c1f3a82f7cad96b0773192489
    private final GamePanel myGamePanel;


    public MenuBar(GamePanel theGamePanel, JFrame theFrame) {
        super();
        myGamePanel = theGamePanel;
        JMenu options = new JMenu("Options");
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //new stuff
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myGamePanel.saveLoad.save();

            }
        });

        JMenuItem mainMenu = new JMenuItem("Main Menu");
        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myGamePanel.setMyGameState(0);
            }
        });

        options.add(quit);
        options.addSeparator();
        options.add(save);
        options.addSeparator();
        options.add(mainMenu);
        JMenu about = new JMenu("About");
        JMenuItem des = new JMenuItem("Description");

        des.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (myGamePanel.getMyGameState() == 2 && !myGamePanel.getMyAboutState()) {
                    myGamePanel.setMyAboutState(true);
                } else {
                    myGamePanel.setMyAboutState(false);
                }
            }
        });
        about.add(des);
        add(options);
        add(about);
    }
}
