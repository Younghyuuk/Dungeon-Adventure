package View;

import Control.Keyboard;
import Model.*;

public class Main {

    public static void main(String[] args){
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
    }
}
