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

    public void createMonster() {
        for (int i = 2; i < dungeonW; i++) {
            Monster aMonster1 = myMonsterDataBase.getRandomMonster();
            aMonster1.setMyWorldXCoordinate(((roomW * i) - ((roomW / 2) + 1)) * myGamePanel.getSpriteSize());
            aMonster1.setMyWorldYCoordinate(((roomH * 1) - ((roomH / 2) + 1)) * myGamePanel.getSpriteSize());
            myMonsterArray.add(aMonster1);
            Monster aMonster2 = myMonsterDataBase.getRandomMonster();
            aMonster1.setMyWorldXCoordinate(((roomW * i) - ((roomW / 2) + 1)) * myGamePanel.getSpriteSize());
            aMonster1.setMyWorldYCoordinate(((roomH * dungeonH) - ((roomH / 2) + 1)) * myGamePanel.getSpriteSize());
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

    public List<Monster> getMyMonsterArray() {
        return myMonsterArray;
    }
}

