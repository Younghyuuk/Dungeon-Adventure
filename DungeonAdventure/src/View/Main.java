package View;

import Model.MonsterDataBase;

/**
 * This class creates and runs the whole game.
 */
public class Main {

    /**
     * Runs the game.
     *
     * @param args The String array of input arguments.
     */
    public static void main(String[] args) {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
//            MonsterDataBase mdb = new MonsterDataBase(new GamePanel());
    }
}
