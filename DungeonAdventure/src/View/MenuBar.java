package View;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    /** The command associated with the Quit option. */
    public static final String QUIT = "Quit";
    /** The command associated with the Save option. */
    public static final String SAVE = "Save";

    public MenuBar () {
        super();

        JMenu options = new JMenu("Options");
        JMenuItem quit = new JMenuItem("Quit");
        JMenuItem save = new JMenuItem("Save");

        options.add(quit);
        options.addSeparator();
        options.add(save);
        JMenu about = new JMenu("About");

        add(options);
        add(about);
    }


}
