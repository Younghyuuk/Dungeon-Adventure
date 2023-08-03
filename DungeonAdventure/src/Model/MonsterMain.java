package Model;

import Control.Keyboard;
import View.GamePanel;

// TESTER class for how monsterDatabase will work with Battle logic
public class MonsterMain {


    public static void main(String[] args) {


        GamePanel gp = new GamePanel();
        Keyboard kb = new Keyboard(gp);
        Heroes myHero = new Priestess(gp, kb);
        MonsterDataBase tester = new MonsterDataBase(gp);
        Battle newBattle = new Battle(myHero, tester.getMonster("Gremlin"));
        String[] array = newBattle.getMyBattleLog();

        for(String s: array) {
            System.out.println(s);
        }


    }
}



