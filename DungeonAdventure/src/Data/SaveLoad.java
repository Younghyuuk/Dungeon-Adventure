package Data;

import View.GamePanel;

import java.io.*;

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
            // Save the game panel
            gd.setMyGp(myGp);
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
            gd.setMyDungeon(myGp.getMyDungeon());
            // Save the data for my lists
            gd.setMyMonsters(myGp.getMyMonsterArray());
            gd.setMyItems(myGp.getMyItemArray());

            // We want to write the game data to the save file
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
            if(gd.getMyHero().getChName().equals("Thief")) {
                num = 1;
            } else if(gd.getMyHero().getChName().equals("Warrior")) {
                num = 2;
            } else {
                num = 3;
            }
            // Load in the game panel
            myGp = gd.getMyGp();
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
            myGp.setMyDungeon(gd.getMyDungeon());
            // Load lists
            myGp.setMonsterList(gd.getMyMonsters());
            myGp.setItemList(gd.getMyItems());

            // Then close everything
            input.close();
            file.close();
            System.out.println("Deserialized!");
        } catch (Exception e) {
            System.out.println("Load exception " + e.getMessage());
            e.printStackTrace();
        }
    }
}
