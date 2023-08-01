package Model;

import Control.Keyboard;
import View.GamePanel;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

// TESTER class for how monsterDatabase will work with Battle logic
public class MonsterMain {
    MonsterDataBase tester = new MonsterDataBase();

    public static void main(String[] args) {
        MonsterDataBase tester = new MonsterDataBase();

        GamePanel gp = new GamePanel();
        Keyboard kb = new Keyboard(gp);
        Heroes myHero = new Priestess(gp, kb);

        Battle newBattle = new Battle(myHero, tester.getRandomMonster());
        String[] array = newBattle.getMyBattleLog();

        for(String s: array) {
            System.out.println(s);
        }
    }
}



