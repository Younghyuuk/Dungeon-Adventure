package Model;

import View.GamePanel;

import java.io.*;

public class GameData implements Serializable {
//    private static final long serialVersionUID = -123456;

    //    public Heroes myHero;
    GamePanel gp = new GamePanel();
    public int myScreensMiddleX;

    public int myScreensMiddleY;

    public int myHeroHp;
    public String myHeroName;
    public int myHeroAttackSpeed;
    public int myHeroMinDamage;
    public int myHeroMaxDamage;
    public double myHeroBlock;
    public double myHeroHit;

    private Dungeon myDungeon;
    //    private Heroes myHero;
    private int myGameState;

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            GameData gd = new GameData();
            gd.myHeroHp = gp.getMyHero().getHp();
            gd.myHeroName = gp.getMyHero().getChName();
            gd.myHeroAttackSpeed = gp.getMyHero().getAttackSpeed();
            gd.myHeroMinDamage = gp.getMyHero().getMinDamage();
            gd.myHeroMaxDamage = gp.getMyHero().getMaxDamage();
            gd.myHeroAttackSpeed = gp.getMyHero().getAttackSpeed();
            gd.myHeroBlock = gp.getMyHero().getBlockChance();
            gd.myHeroHit = gp.getMyHero().getHitChance();
            gd.myScreensMiddleX = gp.getMyHero().getMyScreensMiddleX();
            gd.myScreensMiddleY = gp.getMyHero().getMyScreensMiddleY();

            oos.writeObject(gd);
        }
        catch(Exception e) {
            System.out.println("Save exception");
        }
    }
    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File ("save.dat")));
            GameData gd = (GameData)ois.readObject();
            gp.getMyHero().setHp(gd.myHeroHp);
            gp.getMyHero().setChName(gd.myHeroName);
            gp.getMyHero().setAttackSpeed(gd.myHeroAttackSpeed);
            gp.getMyHero().setMinDamage(gd.myHeroMinDamage);
            gd.myHeroMaxDamage = gp.getMyHero().getMaxDamage();
            gd.myHeroAttackSpeed = gp.getMyHero().getAttackSpeed();
            gd.myHeroBlock = gp.getMyHero().getBlockChance();
            gd.myHeroHit = gp.getMyHero().getHitChance();
            gd.myScreensMiddleX = gp.getMyHero().getMyScreensMiddleX();
            gd.myScreensMiddleY = gp.getMyHero().getMyScreensMiddleY();
        }
        catch (Exception e) {
            System.out.println("Load exception");
        }
    }
}

