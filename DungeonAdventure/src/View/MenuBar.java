package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class MenuBar extends JMenuBar implements Serializable {

    private static final long serialversionUID = -12345678;

    /** The command associated with the Quit option. */
    public static final String QUIT = "Quit";
    /** The command associated with the Save option. */
    public static final String SAVE = "Save";

    private final GamePanel myGamePanel;

    public MenuBar (GamePanel theGamePanel, JFrame theFrame) {
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
//        save.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser fileChooser = new JFileChooser();
//                int userSelection = fileChooser.showSaveDialog(theFrame);
//
//                if (userSelection == JFileChooser.APPROVE_OPTION) {
//                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
////                    myGamePanel.saveGame(filePath);
//                    System.out.println("Game saved to: " + filePath);
//                }
//            }

        JMenuItem mainMenu = new JMenuItem("Main Menu");
        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
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
                if(myGamePanel.getMyGameState() == 2 && !myGamePanel.getMyAboutState()) {
                    myGamePanel.setMyAboutState(true);
                }else{
                    myGamePanel.setMyAboutState(false);
                }
            }
        });
        about.add(des);
        add(options);
        add(about);
    }
}
