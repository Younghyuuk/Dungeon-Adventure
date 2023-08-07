package Model;

import View.GamePanel;

import java.util.ArrayList;
import java.util.List;

public class InitiateEntites {
    private final GamePanel myGamePanel;
    private final MonsterDataBase myMonsterDataBase;
    private List<Monster> myMonsterArray;
    private List<FourPillars> myFourPillarsArray;

    private final int dungeonH;
    private final int dungeonW;
    private final int roomH;
    private final int roomW;

    public InitiateEntites(GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
        myMonsterDataBase = new MonsterDataBase(myGamePanel);
        dungeonH = myGamePanel.getMyDungeon().getDungeonHeight();
        dungeonW = myGamePanel.getMyDungeon().getDungeonWidth();
        roomH = myGamePanel.getMyDungeon().getRooms()[0][0].getRoomHeight();
        roomW = myGamePanel.getMyDungeon().getRooms()[0][0].getRoomWidth();
        myMonsterArray = new ArrayList<>();
        myFourPillarsArray = new ArrayList<>();
        createFourPillars();
    }
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


    public List<FourPillars> getMyFourPillarsArray(){
        return myFourPillarsArray;
    }
    public List<Monster> getMyMonsterArray() {
        return myMonsterArray;
    }
}

