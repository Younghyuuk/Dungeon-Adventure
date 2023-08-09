package Data;

import Model.Heroes;
import View.GamePanel;

import java.io.*;

public class GameData implements Serializable {
    private static final long serialVersionUID = -123456;

    //    public Heroes myHero;
    GamePanel myGp = new GamePanel();
    public int myScreensMiddleX;

    public int myScreensMiddleY;

    public Heroes myHero;
    public int myHeroHp;
    public String myHeroName;
    public int myHeroAttackSpeed;
    public int myHeroMinDamage;
    public int myHeroMaxDamage;
    public double myHeroBlock;
    public double myHeroHit;

//    private Dungeon myDungeon;
//    //    private Heroes myHero;
//    private int myGameState;


}

