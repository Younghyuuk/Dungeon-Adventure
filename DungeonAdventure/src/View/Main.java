package View;

import Control.Keyboard;
import Model.*;

public class Main {

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

<<<<<<< HEAD
        GamePanel gp = new GamePanel();
        Keyboard kb = new Keyboard();
        Heroes myHero = new Thief(gp, kb);
        Monster myMonster = new Skeleton();

        Battle newBattle = new Battle(myHero, myMonster);


=======
//        GamePanel gp = new GamePanel();
//        Keyboard kb = new Keyboard(gp);
//        Heroes myHero = new Warrior(gp, kb);
//        Monster myMonster = new Ogre();
//
//        Battle newBattle = new Battle(myHero, myMonster);
//        String[] array = newBattle.getMyBattleLog();
//
//        for(String s: array) {
//            System.out.println(s);
//        }
>>>>>>> 3ef828b944474163dd23b5bb15d9f655ddf6d51b
    }
}
