package Data;

import Model.*;
import View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class saves and loads the game data.
 */
public class SaveLoad {
    /**
     * The filename of the save file.
     */
    private static final String FILENAME = "Resources/save/game.save";
    /**
     * The game panel containing all the data for the game.
     */
    private GamePanel myGp;


    /**
     * Sets up the class by getting a game panel object from 'GamePanel'.
     *
     * @param theGp The game panel to save and load data from.
     */
    public SaveLoad(GamePanel theGp) {
        myGp = theGp;
    }

    /**
     * Saves all the game data.
     */
    public void save() {
        try {
            FileOutputStream file = new FileOutputStream(FILENAME);
            ObjectOutputStream output = new ObjectOutputStream(file);
            GameData gd = new GameData();

            // Save the hero data
            gd.setMyHero(myGp.getMyHero());
            gd.setMyHeroHp(myGp.getMyHero().getHp());
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
            Dungeon dungeon = myGp.getMyDungeon();
            gd.setMyDungeon(myGp.getMyDungeon());

            // Save the data for my lists
            gd.setMyMonsters(myGp.getMyMonsterArray());
            gd.setMyItems(myGp.getMyItemArray());
            gd.setMyPillars(myGp.getMyPillarArray());

            // We want to write the game data to the save file
            gd.setMyWinCount(myGp.getWinCount());

            output.writeObject(gd);
            // Then we close everything
            output.close();
            file.close();
            System.out.println("Serialized!");
        } catch (Exception e) {
            System.out.println("Save exception " + e.getMessage());
            e.printStackTrace();
        }


    }

    /**
     * Loads all the game data.
     */
    public void load() {
        try {
            FileInputStream file = new FileInputStream(FILENAME);
            ObjectInputStream input = new ObjectInputStream(file);
            // We want to read in the data
            GameData gd = (GameData) input.readObject();
            // Then grab the number pertaining to the hero the player had chosen
            int num = 0;
            if (gd.getMyHero().getChName().equals("Thief")) {
                num = 1;
            } else if (gd.getMyHero().getChName().equals("Warrior")) {
                num = 2;
            } else {
                num = 3;
            }

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
            Dungeon dungeon;
            dungeon = gd.getMyDungeon();
//            for(int i = 0; i < dungeon.getDungeonWidth(); i++) {
//                for(int j = 0; j < dungeon.getDungeonHeight(); j++) {
////                    myGp.getMyDungeon().getRooms()
//                }
//            }
//            dungeon.textDungeon("Resources/save/loadMap");
            myGp.setMyDungeon(dungeon);


            List<Monster> monsterList = new ArrayList<>();

            for (Monster savedMonster : gd.getMyMonsters()) {
                MonsterDataBase mon = new MonsterDataBase(myGp);
                if (savedMonster.isAlive()) {
                    // Create new monster instances based on saved data and add them to the list
                    // For example:
                    if (savedMonster.getChName().equals("Gremlin")) {
                        Monster test = mon.getMonster("Gremlin");
                        test.setMyWorldXCoordinate(savedMonster.getMyWorldXCoordinate());
                        test.setMyWorldYCoordinate(savedMonster.getMyWorldYCoordinate());
                        monsterList.add(test);
                    } else if (savedMonster.getChName().equals("Skeleton")) {
                        Monster test = mon.getMonster("Skeleton");
                        test.setMyWorldXCoordinate(savedMonster.getMyWorldXCoordinate());
                        test.setMyWorldYCoordinate(savedMonster.getMyWorldYCoordinate());
                        monsterList.add(test);
                    } else {
                        Monster test = mon.getMonster("Ogre");
                        test.setMyWorldXCoordinate(savedMonster.getMyWorldXCoordinate());
                        test.setMyWorldYCoordinate(savedMonster.getMyWorldYCoordinate());
                        monsterList.add(test);
                    }
                }
            }
            myGp.setMonsterList(monsterList);

            List<Item> itemList = new ArrayList<>();

            for(Item items : gd.getMyItems()) {
                if(items instanceof HealthPotion && !items.getFound()) {
                    items = new HealthPotion(items.getMyWorldXCoordinate(), items.getMyWorldYCoordinate(), myGp);
                } else if(items instanceof VisionPotion && !items.getFound()) {
                    items = new VisionPotion(items.getMyWorldXCoordinate(), items.getMyWorldYCoordinate(), myGp);
                } else if(items instanceof Pit && !items.getFound()){
                    items = new Pit(items.getMyWorldXCoordinate(), items.getMyWorldYCoordinate(), myGp);
                }
                itemList.add(items);
            }
            myGp.setItemList(itemList);

            List<FourPillars> pillarList = new ArrayList<>();

            for(FourPillars pillar : gd.getMyPillars()) {
                if(!pillar.getFound()) {
                    FourPillars newPillar = new FourPillars(pillar.getMyWorldXCoordinate(),
                            pillar.getMyWorldYCoordinate(), pillar.getMyName(), myGp);
                    newPillar.setImage(newPillar.findImage());
                    pillarList.add(newPillar);
                }
            }
            myGp.setPillarList(pillarList);
            myGp.setMyWinCount(gd.getMyWinCount());

            // Then close everything
            input.close();
            file.close();
            System.out.println("Deserialized!");
        } catch (Exception e) {
            System.out.println("Load exception " + e.getMessage());
            e.printStackTrace();
        }




    }
    private BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
