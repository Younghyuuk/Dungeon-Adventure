package Data;

import View.GamePanel;

import java.io.*;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

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
            GameData gd = new GameData(myGp);
            // Save the hero data
            gd.setMyHero(myGp.getMyHero());
            gd.setMyHeroHp(myGp.getMyHero().getHp());
//            System.out.println(gd.getMyHeroHp());
            gd.setMyHeroName(myGp.getMyHero().getChName());
            gd.setMyHeroAttackSpeed(myGp.getMyHero().getAttackSpeed());
            gd.setMyHeroMinDamage(myGp.getMyHero().getMinDamage());
            gd.setMyHeroMaxDamage(myGp.getMyHero().getMaxDamage());
            gd.setMyHeroHit(myGp.getMyHero().getHitChance());
            gd.setMyHeroBlock(myGp.getMyHero().getBlockChance());
            gd.setMyScreensMiddleX(myGp.getMyHero().getMyScreensMiddleX());
            gd.setMyScreensMiddleY(myGp.getMyHero().getMyScreensMiddleY());
            gd.setMyWorldX(myGp.getMyHero().getMyWorldXCoordinate());
            gd.setMyWorldY(myGp.getMyHero().getMyWorldYCoordinate());
            // Save the dungeon data
//            gd.setMyRooms(myGp.getMyDungeon().getRooms());
//            gd.setMyDoors(myGp.getMyDungeon().getRooms());
//            gd.setMyDungeon(myGp.getMyDungeon());
//            gd.setTextDungeon(myGp.getMyDungeon().getTEXT_DUNGEON());
            gd.setTextDungeon("/map/dungeon.txt");
//            gd.setDungeonH(myGp.getMyDungeon().getDungeonHeight());
//            gd.setDungeonH(myGp.getMyDungeon().getDungeonWidth());

//            gd.setMyDungeonRooms(myGp.getMyDungeon().getRooms());
//            gd.setMyVisitedRooms(myGp.getMyDungeon().getVisitedRooms());

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
            int num = 0;
            if(gd.getMyHero().getChName().equals("Thief")) {
                num = 1;
            } else if(gd.getMyHero().getChName().equals("Warrior")) {
                num = 2;
            } else {
                num = 3;
            }
            // Load in the hero data
            myGp.setMyHero(num);
            myGp.getMyHero().setHp(gd.getMyHeroHp());
            myGp.getMyHero().setChName(gd.getMyHeroName());
            myGp.getMyHero().setAttackSpeed(gd.getMyHeroAttackSpeed());
            myGp.getMyHero().setMinDamage(gd.getMyHeroMinDamage());
            myGp.getMyHero().setMaxDamage(gd.getMyHeroMaxDamage());
            myGp.getMyHero().setBlockChance(gd.getMyHeroBlock());
            myGp.getMyHero().setHitChance(gd.getMyHeroHit());
            myGp.getMyHero().setMyScreensMiddleX(gd.getMyScreensMiddleX());
            myGp.getMyHero().setMyScreensMiddleY(gd.getMyScreensMiddleY());
            myGp.getMyHero().setMyWorldXCoordinate(gd.getMyWorldX());
            myGp.getMyHero().setMyWorldYCoordinate(gd.getMyWorldY());
            // Load in the dungeon data
//            myGp.getMyDungeon().setDoors(gd.getDoors());
//            myGp.setMyDungeon(gd.getMyDungeon());
            myGp.getMyDungeon().setTextDungeon(gd.getTextDungeon());
//            myGp.setMyDungeon(gd.getMyDungeon());
//            myGp.getMyDungeon().setDoors(gd.getMyDoors());


//            System.out.println(myGp.toString());
            ois.close();
        } catch (Exception e) {
            System.out.println("Load exception " + e.getMessage());
            e.printStackTrace();
        }
    }
}
