package Model;

import View.GamePanel;

import java.util.ArrayList;
import java.util.List;

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
        myItemArray = new ArrayList<>();
        createMonster();
    }

    public void createMonster(){
        for (int i = 1; i < dungeonW; i++) {
            for (int j = 2; j < dungeonH; j++) {
                if(i == 4 && j == 4){
                    // Starting point. Do not have the monster here.
                }else {
                    Monster aMonster1 = myMonsterDataBase.getRandomMonster();
                    aMonster1.setMyWorldXCoordinate(((roomW * i) - ((roomW/2)+1))  * myGamePanel.getSpriteSize());
                    aMonster1.setMyWorldYCoordinate(((roomH * j) - ((roomH/2)+1))  * myGamePanel.getSpriteSize());
                    myMonsterArray.add(aMonster1);

                }

            }
        }
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

