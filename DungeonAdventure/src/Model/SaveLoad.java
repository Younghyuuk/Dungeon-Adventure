package Model;

import View.GamePanel;

import java.io.*;

public class SaveLoad implements Serializable{

    private static final long serialVersionUID = -341835787402514619L;
    GamePanel myGp;

   public SaveLoad(GamePanel theGp) {
       myGp = theGp;
   }
    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            GameData gd = new GameData();
            gd.myHero = myGp.getMyHero();
//            gd.myHeroHp = myGp.getMyHero().getHp();
//            gd.myHeroName = myGp.getMyHero().getChName();
//            gd.myHeroAttackSpeed = myGp.getMyHero().getAttackSpeed();
//            gd.myHeroMinDamage = myGp.getMyHero().getMinDamage();
//            gd.myHeroMaxDamage = myGp.getMyHero().getMaxDamage();
//            gd.myHeroBlock = myGp.getMyHero().getBlockChance();
//            gd.myHeroHit = myGp.getMyHero().getHitChance();
//            gd.myScreensMiddleX = myGp.getMyHero().getMyScreensMiddleX();
//            gd.myScreensMiddleY = myGp.getMyHero().getMyScreensMiddleY();

            oos.writeObject(gd);
            oos.close();
        }
        catch(Exception e) {
            System.out.println("Save exception" + e.getMessage());
            e.printStackTrace();
        }
    }
    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File ("save.dat")));
            GameData gd = (GameData)ois.readObject();

            myGp.getMyHero().setHp(gd.myHeroHp);
            myGp.getMyHero().setChName(gd.myHeroName);
            myGp.getMyHero().setAttackSpeed(gd.myHeroAttackSpeed);
            myGp.getMyHero().setMinDamage(gd.myHeroMinDamage);
            myGp.getMyHero().setMaxDamage(gd.myHeroMaxDamage);
            myGp.getMyHero().setBlockChance(gd.myHeroBlock);
            myGp.getMyHero().setHitChance(gd.myHeroHit);
            myGp.getMyHero().setMyScreensMiddleX(gd.myScreensMiddleX);
            myGp.getMyHero().setMyScreensMiddleY(gd.myScreensMiddleY);
            ois.close();
        }
        catch (Exception e) {
            System.out.println("Load exception" + e.getMessage());
            e.printStackTrace();
        }
    }
}
