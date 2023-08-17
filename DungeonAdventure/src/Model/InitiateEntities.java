package Model;

import View.GamePanel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class initiates all the monsters and items that will be in the dungeon.
 */
public class InitiateEntities implements Serializable {
    /**
     * Represents the index in both the x and y direction of the middle of the room.
     */
    private static final int MIDDLE_OF_ROOM = 4;
    /**
     * The panel that we will draw all the entities to.
     */
    private transient GamePanel myGamePanel;
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
    private int myDungeonH;
    /**
     * The width of the dungeon.
     */
    private int myDungeonW;
    /**
     * The height of every room.
     */
    private int myRoomH;
    /**
     * The width of every room.
     */
    private int myRoomW;

    /**
     * Sets up all the entities that will be contained within the dungeon.
     *
     * @param theGamePanel The panel to draw all the entities onto.
     */
    public InitiateEntities(GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
        myMonsterDataBase = new MonsterDataBase(myGamePanel);
        myDungeonH = myGamePanel.getMyDungeon().getDungeonHeight();
        myDungeonW = myGamePanel.getMyDungeon().getDungeonWidth();
        myRoomH = myGamePanel.getMyDungeon().getRooms()[0][0].getRoomHeight();
        myRoomW = myGamePanel.getMyDungeon().getRooms()[0][0].getRoomWidth();
        myMonsterArray = new ArrayList<>();
        createMonster();
        myItemArray = new ArrayList<>();
        myFourPillarsArray = new ArrayList<>();
        // This creates both the items and pillars in the game
        createItems();
    }

    /**
     * Creates and draws in all the monsters into the dungeon.
     */
    public void createMonster() {
        // Fills out the first and last row, not including the first and last collumn.
        for (int i = 2; i < myDungeonW; i++) {
            Monster aMonster1 = myMonsterDataBase.getRandomMonster();
            aMonster1.setMyWorldXCoordinate(((myRoomW * i) - ((myRoomW / 2) + 1)) * myGamePanel.getSpriteSize());
            aMonster1.setMyWorldYCoordinate(((myRoomH) - ((myRoomH / 2) + 1)) * myGamePanel.getSpriteSize());
            myMonsterArray.add(aMonster1);

            Monster aMonster2 = myMonsterDataBase.getRandomMonster();
            aMonster2.setMyWorldXCoordinate(((myRoomW * i) - ((myRoomW / 2) + 1)) * myGamePanel.getSpriteSize());
            aMonster2.setMyWorldYCoordinate(((myRoomH * myDungeonH) - ((myRoomH / 2) + 1)) * myGamePanel.getSpriteSize());
            myMonsterArray.add(aMonster2);
        }
        for (int i = 1; i <= myDungeonW; i++) {
            for (int j = 2; j < myDungeonH; j++) {
                if (i == 4 && j == 4) {
                    // Starting point. Do not have the monster here.
                } else {
                    Monster aMonster1 = myMonsterDataBase.getRandomMonster();
                    aMonster1.setMyWorldXCoordinate(((myRoomW * i) - ((myRoomW / 2) + 1)) * myGamePanel.getSpriteSize());
                    aMonster1.setMyWorldYCoordinate(((myRoomH * j) - ((myRoomH / 2) + 1)) * myGamePanel.getSpriteSize());
                    myMonsterArray.add(aMonster1);
                }
            }
        }
    }

    /**
     * Creates and draws in all the items (including pillars) into the dungeon.
     */
    public void createItems() {
        // Here we are making variables to represent the first rooms in both
        // the rows and columns and also the middle room in both the rows and columns
        int firstRoom = 1;
        int middleRoom = 4;

        for (int i = 1; i < myDungeonH; i++) {
            for (int j = 1; j < myDungeonW; j++) {
                // We will check for the cases where we are in the pillar rooms or the spawn room
                if (i == firstRoom && j == firstRoom) { // Top Left Room
                    FourPillars abstraction = new FourPillars(
                            ((myRoomW) - ((myRoomW / 2) + 1)) * myGamePanel.getSpriteSize(),
                            ((myRoomH) - ((myRoomH / 2) + 1)) * myGamePanel.getSpriteSize(),
                            "a", myGamePanel);
                    myFourPillarsArray.add(abstraction);
                } else if (i == firstRoom && j == myDungeonW - 1) { // Top Right Room
                    FourPillars encapsulation = new FourPillars(
                            ((myRoomW * myDungeonW) - ((myRoomW / 2) + 1)) * myGamePanel.getSpriteSize(),
                            ((myRoomH) - ((myRoomH / 2) + 1)) * myGamePanel.getSpriteSize(),
                            "e", myGamePanel);
                    myFourPillarsArray.add(encapsulation);
                } else if (i == myDungeonH - 1 && j == firstRoom) { // Bottom Left Room
                    FourPillars inheritance = new FourPillars(
                            ((myRoomW) - ((myRoomW / 2) + 1)) * myGamePanel.getSpriteSize(),
                            ((myRoomH * myDungeonH) - ((myRoomH / 2) + 1)) * myGamePanel.getSpriteSize(),
                            "i", myGamePanel
                    );
                    myFourPillarsArray.add(inheritance);
                } else if (i == myDungeonH - 1 && j == myDungeonW - 1) { // Bottom Right Room
                    FourPillars polymorphism = new FourPillars(
                            ((myRoomW * myDungeonW) - ((myRoomW / 2) + 1)) * myGamePanel.getSpriteSize(),
                            ((myRoomH * myDungeonH) - ((myRoomH / 2) + 1)) * myGamePanel.getSpriteSize(),
                            "p", myGamePanel
                    );
                    myFourPillarsArray.add(polymorphism);
                } else if (i == middleRoom && j == middleRoom) {
                    // Don't do anything if we are in the middle room
                    break;
                } else {
                    Item item = ItemGenerator.getRandomItem(0, myGamePanel);
                    item.setMyWorldYCoordinate(((myRoomH * i) + getRandomY()) * myGamePanel.getSpriteSize());
                    item.setMyWorldXCoordinate(((myRoomW * j) + getRandomX()) * myGamePanel.getSpriteSize());
                    myItemArray.add(item);
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
        // Since the places where an item can actually spawn is only in a 5x5 area
        // We want to get a random value between 1 - 5
        int randomX = random.nextInt(5) + 1;
        // We want to avoid the center of the room where the monsters spawn
        return (randomX == MIDDLE_OF_ROOM) ? randomX + 1 : randomX;
    }

    /**
     * Calculates the value for a random Y coordinate in the dungeon.
     *
     * @return A valid, random, Y-coordinate.
     */
    private int getRandomY() {
        Random random = new Random();
        // Since the places where an item can actually spawn is only in a 5x5 area
        // We want to get a random value between 1 - 5
        int randomY = random.nextInt(5) + 1;
        // We want to avoid the center of the room where the monsters spawn
        return (randomY == MIDDLE_OF_ROOM) ? randomY + 1 : randomY;
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

