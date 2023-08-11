package Data;

import View.GamePanel;

import java.io.*;

public class SaveLoad {
    private static final long serialVersionUID = 123456789L;
    transient GamePanel myGp;


    public SaveLoad(GamePanel theGp) {
        myGp = theGp;
    }

    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream("game.save");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            GameData gd = new GameData();
//            gd.myHero = myGp.getMyHero();
            gd.myHeroHp = myGp.getMyHero().getHp();
            System.out.println(gd.myHeroHp);
            gd.myHeroName = myGp.getMyHero().getChName();
            gd.myHeroAttackSpeed = myGp.getMyHero().getAttackSpeed();
            gd.myHeroMinDamage = myGp.getMyHero().getMinDamage();
            gd.myHeroMaxDamage = myGp.getMyHero().getMaxDamage();
            gd.myHeroHit = myGp.getMyHero().getHitChance();
            gd.myHeroBlock = myGp.getMyHero().getBlockChance();
            gd.myScreensMiddleX = myGp.getMyHero().getMyScreensMiddleX();
            gd.myScreensMiddleY = myGp.getMyHero().getMyScreensMiddleY();

            oos.writeObject(gd);
            oos.close();
        } catch (Exception e) {
            System.out.println("Save exception " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void load() {
        try {
//            FileInputStream fis = new FileInputStream("game.save");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("game.save")));
            GameData gd = (GameData) ois.readObject();

            myGp.getMyHero().setHp(gd.myHeroHp);
            myGp.getMyHero().setChName(gd.myHeroName);
            myGp.getMyHero().setAttackSpeed(gd.myHeroAttackSpeed);
            myGp.getMyHero().setMinDamage(gd.myHeroMinDamage);
            myGp.getMyHero().setMaxDamage(gd.myHeroMaxDamage);
            myGp.getMyHero().setBlockChance(gd.myHeroBlock);
            myGp.getMyHero().setHitChance(gd.myHeroHit);
            myGp.getMyHero().setMyScreensMiddleX(gd.myScreensMiddleX);
            myGp.getMyHero().setMyScreensMiddleY(gd.myScreensMiddleY);

//            System.out.println(myGp.toString());
            ois.close();
        } catch (Exception e) {
            System.out.println("Load exception " + e.getMessage());
            e.printStackTrace();
        }
    }
}
