package Model;

import View.GamePanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class initiates all the monsters and items that will be in the dungeon.
 */
public class InitiateEntites {
    /**
     * The panel that we will draw all the entities to.
     */
    private GamePanel myGamePanel;
    /**
     * The database containing all the info for the monsters in the dungeon.
     */
    private MonsterDataBase myMonsterDataBase;
    /**
     * A list that stores every monster that will be in the dungeon.
     */
    private List<Monster> myMonsterArray;
    /**
     * A list that stores every item that will be in the dungeon.
     */
    private List<Item> myItemArray;
    /**
     * A list that stores all the four pillars of OO that are in the dungeon.
     */
    private List<FourPillars> myFourPillarsArray;
    /**
     * The height of the dungeon.
     */
    private int dungeonH;
    /**
     * The width of the dungeon.
     */
    private int dungeonW;
    /**
     * The height of ever room.
     */
    private int roomH;
    /**
     * The width of every room.
     */
    private int roomW;

    /**
     * Sets up all the entities that will be contained within the dungeon.
     *
     * @param theGamePanel The panel to draw all the entities onto.
     */
    public InitiateEntites(GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
        myMonsterDataBase = new MonsterDataBase(myGamePanel);
        dungeonH = myGamePanel.getMyDungeon().getDungeonHeight();
        dungeonW = myGamePanel.getMyDungeon().getDungeonWidth();
        roomH = myGamePanel.getMyDungeon().getRooms()[0][0].getRoomHeight();
        roomW = myGamePanel.getMyDungeon().getRooms()[0][0].getRoomWidth();
        myMonsterArray = new ArrayList<>();
        createMonster();
        myItemArray = new ArrayList<>();
        createItems();
        myFourPillarsArray = new ArrayList<>();
        createFourPillars();
    }

    /**
     * Creates and draws in all the Four Pillars of OO into the dungeon.
     */
    public void createFourPillars() {
        FourPillars abstraction = new FourPillars(((roomW) - ((roomW / 2) + 1)) * myGamePanel.getSpriteSize(),
                ((roomH) - ((roomH / 2) + 1)) * myGamePanel.getSpriteSize(),"a", myGamePanel);
        FourPillars encapsulation = new FourPillars(((roomW*dungeonW) - ((roomW / 2) + 1)) * myGamePanel.getSpriteSize(),
                ((roomH) - ((roomH / 2) + 1)) * myGamePanel.getSpriteSize(),"e", myGamePanel);
        FourPillars inheritance = new FourPillars(((roomW) - ((roomW / 2) + 1)) * myGamePanel.getSpriteSize(),
                ((roomH*dungeonH) - ((roomH / 2) + 1)) * myGamePanel.getSpriteSize(),"i", myGamePanel);
        FourPillars polymorphism = new FourPillars(((roomW*dungeonW) - ((roomW / 2) + 1)) * myGamePanel.getSpriteSize(),
                ((roomH*dungeonH) - ((roomH / 2) + 1)) * myGamePanel.getSpriteSize(),"p", myGamePanel);

        myFourPillarsArray.add(abstraction);
        myFourPillarsArray.add(encapsulation);
        myFourPillarsArray.add(inheritance);
        myFourPillarsArray.add(polymorphism);
    }

    /**
     * Creates and draws in all the monsters into the dungeon.
     */
    public void createMonster() {
        // Fills out the first and last row, not including the first and last collumn.
        for (int i = 2; i < dungeonW; i++) {
            Monster aMonster1 = myMonsterDataBase.getRandomMonster();
            aMonster1.setMyWorldXCoordinate(((roomW * i) - ((roomW / 2) + 1)) * myGamePanel.getSpriteSize());
            aMonster1.setMyWorldYCoordinate(((roomH) - ((roomH / 2) + 1)) * myGamePanel.getSpriteSize());
            myMonsterArray.add(aMonster1);

            Monster aMonster2 = myMonsterDataBase.getRandomMonster();
            aMonster2.setMyWorldXCoordinate(((roomW * i) - ((roomW / 2) + 1)) * myGamePanel.getSpriteSize());
            aMonster2.setMyWorldYCoordinate(((roomH * dungeonH) - ((roomH / 2) + 1)) * myGamePanel.getSpriteSize());
            myMonsterArray.add(aMonster2);
        }
        for (int i = 1; i <= dungeonW; i++) {
            for (int j = 2; j < dungeonH; j++) {
                if (i == 4 && j == 4) {
                    // Starting point. Do not have the monster here.
                } else {
                    Monster aMonster1 = myMonsterDataBase.getRandomMonster();
                    aMonster1.setMyWorldXCoordinate(((roomW * i) - ((roomW / 2) + 1)) * myGamePanel.getSpriteSize());
                    aMonster1.setMyWorldYCoordinate(((roomH * j) - ((roomH / 2) + 1)) * myGamePanel.getSpriteSize());
                    myMonsterArray.add(aMonster1);
                }
            }
        }
    }

    /**
     * Creates and draws in all the items into the dungeon.
     */
    public void createItems() {
        // We will create a random generator to decide where an item will be placed.
        Random random = new Random();
        int
        int firstRoom = 1;
        int middleRoom = 4;
        for (int i = 1; i <= dungeonH; i++) {
            for (int j = 1; j <= dungeonW; j++) {
                // We will check for the cases that an item is in the corners or where the character spawns
                if (i == firstRoom && j == firstRoom || i == firstRoom && j == dungeonW ||
                        i == dungeonH && j == firstRoom || i == dungeonH && j == dungeonW ||
                                                        i == middleRoom && j == middleRoom) {
                    // We don't want to draw an item in these rooms.
                } else {
                    Item item = ItemGenerator.getRandomItem(0, myGamePanel);
                    item.setMyWorldXCoordinate(getRandomX());
                    item.setMyWorldYCoordinate(getRandomY());

                }
            }
        }
    }

    /**
     * Calculates the value for a random X coordinate in the dungeon.
     *
     * @return A valid, random, X-coordinate.
     */
    private int getRandomX() {
        Random random = new Random();
        int randomRoomX = {1, 2, 3, }
    }

    /**
     * Calculates the value for a random Y coordinate in the dungeon.
     *
     * @return A valid, random, Y-coordinate.
     */
    private int getRandomY() {
        Random random = new Random();
    }

    /**
     * Gets the list of every Pillar of OO currently in the dungeon.
     *
     * @return The list of pillars contained in the dungeon.
     */
    public List<FourPillars> getMyFourPillarsArray(){
        return myFourPillarsArray;
    }
  
    /**
     * Gets the list of every monster currently in the dungeon.
     *
     * @return The list of monsters contained in the dungeon.
     */
    public List<Monster> getMyMonsterArray() {
        return myMonsterArray;
    }

    /**
     * Gets the list of all items currently in the dungeon.
     *
     * @return The list of items contained in the dungeon.
     */
    public List<Item> getMyItemArray() {
        return myItemArray;
    }
}

