package Model;

import View.GamePanel;

import java.util.ArrayList;
import java.util.List;

public class InitiateEntites {
    private GamePanel myGamePanel;
    private MonsterDataBase myMonsterDataBase;
    private List<Monster> myMonsterArray;

    private int dungeonH;
    private int dungeonW;
    private int roomH;
    private int roomW;

    public InitiateEntites(GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
        myMonsterDataBase = new MonsterDataBase(myGamePanel);

        dungeonH = myGamePanel.getMyDungeon().getDungeonHeight();
        dungeonW = myGamePanel.getMyDungeon().getDungeonWidth();
        roomH = myGamePanel.getMyDungeon().getRooms()[0][0].getRoomHeight();
        roomW = myGamePanel.getMyDungeon().getRooms()[0][0].getRoomWidth();
        myMonsterArray = new ArrayList<>();
        createMonster();
    }

    public void createMonster(){

        Monster aMonster1 = myMonsterDataBase.getMonster("Skeleton");
        aMonster1.setMyXCords(3 * myGamePanel.getSpriteSize());
        aMonster1.setMyYCords(3 * myGamePanel.getSpriteSize());
        myMonsterArray.add(aMonster1);

    }

    public List<Monster> getMyMonsterArray() {
        return myMonsterArray;
    }
}

