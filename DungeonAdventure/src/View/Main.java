package View;

import Control.Keyboard;
import Model.*;

public class Main {

    public static void main(String[] args){
<<<<<<< HEAD
//        MainFrame frame = new MainFrame();
//        frame.setVisible(true);
//        frame.setResizable(false);
//        frame.setLocationRelativeTo(null);

        GamePanel gp = new GamePanel();
        Keyboard kb = new Keyboard();
        Heroes myHero = new Priestess(gp, kb);
        Monster myMonster = new Skeleton();

        Battle newBattle = new Battle(myHero, myMonster);
        String[] newArray = newBattle.getMyBattleLog();

        for (String s : newArray) {
            System.out.println(s);
        }
=======
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

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

>>>>>>> d04b60b8e65ecc423ce6bb5826bfe1d881829e2d
    }
}
