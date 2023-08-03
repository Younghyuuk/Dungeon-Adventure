package View;

import Control.Keyboard;
import Model.*;

public class Main {


    public static void main(String[] args) {

//            MainFrame frame = new MainFrame();
//            frame.setVisible(true);
//            frame.setResizable(false);
//            frame.setLocationRelativeTo(null);


        GamePanel gp = new GamePanel();
        Keyboard kb = new Keyboard(gp);
        Heroes myHero = new Thief(gp, kb);
        Monster myMonster = new MonsterDataBase(gp).getRandomMonster();

        Battle newBattle = new Battle(myHero, myMonster);
        String[] array = newBattle.getMyBattleLog();

        for (String s : array) {
            System.out.println(s);
        }


    }
}
