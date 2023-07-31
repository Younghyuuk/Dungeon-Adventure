package Model;

import Control.Keyboard;
import View.GamePanel;

// TESTER class for how monsterDatabase will work with Battle logic
public class MonsterMain {
    MonsterDataBase tester = new MonsterDataBase();

    public static void main(String[] args) {
        MonsterDataBase tester = new MonsterDataBase();


        GamePanel gp = new GamePanel();
        Keyboard kb = new Keyboard(gp);
        Heroes myHero = new Priestess(gp, kb);

        Battle newBattle = new Battle(myHero, tester.getMonster("Gremlin"));
        String[] array = newBattle.getMyBattleLog();

        for(String s: array) {
            System.out.println(s);
        }


    }
}



