package Model;

import View.GamePanel;

import java.util.List;

public class Collision {

    GamePanel myGamePanel;
    Battle myBattle;

    public Collision(final GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
    }

    public void checkTile(final DungeonCharacter dungeonChar) {
        int heroLeftWorldX = dungeonChar.getMyWorldXCoordinate() + dungeonChar.getMySolidArea().x;
        int heroRightWorldX = dungeonChar.getMyWorldXCoordinate() + dungeonChar.getMySolidArea().x + dungeonChar.getMySolidArea().width;
        int heroTopWorldY = dungeonChar.getMyWorldYCoordinate() + dungeonChar.getMySolidArea().y;
        int heroBottomWorldY = dungeonChar.getMyWorldYCoordinate() + dungeonChar.getMySolidArea().y + dungeonChar.getMySolidArea().height;

        int heroLeftCol = heroLeftWorldX / myGamePanel.getSpriteSize();
        int heroRightCol = heroRightWorldX / myGamePanel.getSpriteSize();
        int heroTopRow = heroTopWorldY / myGamePanel.getSpriteSize();
        int heroBottomRow = heroBottomWorldY / myGamePanel.getSpriteSize();

        int tileNum1, tileNum2;

        switch (dungeonChar.getMyDirection()) {
            case "up":
                heroTopRow = (heroTopWorldY - dungeonChar.getMySpeed()) / myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.getMyTileM().getMyMapArray()[heroTopRow][heroLeftCol];
                tileNum2 = myGamePanel.getMyTileM().getMyMapArray()[heroTopRow][heroRightCol];
                if (myGamePanel.getMyTileM().getMyTile()[tileNum1].getMyCollision() || myGamePanel.getMyTileM().getMyTile()[tileNum2].getMyCollision()) {
                    dungeonChar.setMyCollision(true);
                    // if the heroes collison is true and tile is monster , thennwe call battle.
                }
                break;
            case "down":
                heroTopRow = (heroTopWorldY - dungeonChar.getMySpeed()) / myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.getMyTileM().getMyMapArray()[heroBottomRow][heroLeftCol];
                tileNum2 = myGamePanel.getMyTileM().getMyMapArray()[heroBottomRow][heroRightCol];
                if (myGamePanel.getMyTileM().getMyTile()[tileNum1].getMyCollision() || myGamePanel.getMyTileM().getMyTile()[tileNum2].getMyCollision()) {
                    dungeonChar.setMyCollision(true);
                }
                break;
            case "left":
                heroTopRow = (heroTopWorldY - dungeonChar.getMySpeed()) / myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.getMyTileM().getMyMapArray()[heroTopRow][heroLeftCol];
                tileNum2 = myGamePanel.getMyTileM().getMyMapArray()[heroBottomRow][heroLeftCol];
                if (myGamePanel.getMyTileM().getMyTile()[tileNum1].getMyCollision() || myGamePanel.getMyTileM().getMyTile()[tileNum2].getMyCollision()) {
                    dungeonChar.setMyCollision(true);
                }
                break;
            case "right":
                heroTopRow = (heroTopWorldY - dungeonChar.getMySpeed()) / myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.getMyTileM().getMyMapArray()[heroTopRow][heroRightCol];
                tileNum2 = myGamePanel.getMyTileM().getMyMapArray()[heroBottomRow][heroRightCol];
                if (myGamePanel.getMyTileM().getMyTile()[tileNum1].getMyCollision() || myGamePanel.getMyTileM().getMyTile()[tileNum2].getMyCollision()) {
                    dungeonChar.setMyCollision(true);
                }
                break;
        }
    }

    public int checkEntity(Heroes theHero, List<Monster> theMonsters) {
        int index = 999;
        int i = 0;
        for (Monster mon : theMonsters) {
            if (mon.isAlive()) {
                theHero.getMySolidArea().x = theHero.myWorldXCoordinate + theHero.getMySolidArea().x;
                theHero.getMySolidArea().y = theHero.myWorldYCoordinate + theHero.getMySolidArea().y;

                mon.getMySolidArea().x = mon.getMyWorldXCoordinate() + mon.getMySolidArea().x;
                mon.getMySolidArea().y = mon.myWorldYCoordinate + mon.getMySolidArea().y;

                switch (theHero.getMyDirection()) {
                    case "up":
                        theHero.getMySolidArea().y -= theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(mon.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            myBattle = new Battle(theHero, mon);
                            myGamePanel.setMyBattleLog(myBattle.getMyBattleLog());
                            myGamePanel.setMyGameState(3);
                        }
                        break;
                    case "down":
                        theHero.getMySolidArea().y += theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(mon.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            myBattle = new Battle(theHero, mon);
                            myGamePanel.setMyBattleLog(myBattle.getMyBattleLog());
                            myGamePanel.setMyGameState(3);
                        }
                        break;
                    case "left":
                        theHero.getMySolidArea().x -= theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(mon.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            myBattle = new Battle(theHero, mon);
                            myGamePanel.setMyBattleLog(myBattle.getMyBattleLog());
                            myGamePanel.setMyGameState(3);
                        }
                        break;
                    case "right":
                        theHero.getMySolidArea().x += theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(mon.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            myBattle = new Battle(theHero, mon);
                            myGamePanel.setMyBattleLog(myBattle.getMyBattleLog());
                            myGamePanel.setMyGameState(3);
                        }
                        break;
                }
                theHero.resetSolidArea();
                mon.resetSolidArea();
            }
        }

        theHero.setHp(125);
        return index;
    }

    public int checkPillar(Heroes theHero, List<FourPillars> thePillars) {
        int index = 999;
        int i = 0;
        for (FourPillars pillar : thePillars) {
            if (!pillar.getFound()) {
                theHero.getMySolidArea().x = theHero.myWorldXCoordinate + theHero.getMySolidArea().x;
                theHero.getMySolidArea().y = theHero.myWorldYCoordinate + theHero.getMySolidArea().y;

                pillar.getMySolidArea().x = pillar.getMyWorldXCoordinate() + pillar.getMySolidArea().x;
                pillar.getMySolidArea().y = pillar.myWorldYCoordinate + pillar.getMySolidArea().y;

                switch (theHero.getMyDirection()) {
                    case "up":
                        theHero.getMySolidArea().y -= theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(pillar.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            pillar.setFound(true);
                            myGamePanel.incWinCount();
                        }
                        break;
                    case "down":
                        theHero.getMySolidArea().y += theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(pillar.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            pillar.setFound(true);
                            myGamePanel.incWinCount();
                        }
                        break;
                    case "left":
                        theHero.getMySolidArea().x -= theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(pillar.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            pillar.setFound(true);
                            myGamePanel.incWinCount();
                        }
                        break;
                    case "right":
                        theHero.getMySolidArea().x += theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(pillar.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            pillar.setFound(true);
                            myGamePanel.incWinCount();
                        }
                        break;
                }
                theHero.resetSolidArea();
                pillar.resetSolidArea();
            }
        }
        return index;
    }

    public int checkItem(final Heroes theHero, final List<Item> theItemList) {
        int index = 999;
        int i = 0;
        for (Item item : theItemList) {
            if (!item.getFound()) {
                theHero.getMySolidArea().x = theHero.myWorldXCoordinate + theHero.getMySolidArea().x;
                theHero.getMySolidArea().y = theHero.myWorldYCoordinate + theHero.getMySolidArea().y;

                item.getMySolidArea().x = item.getMyWorldXCoordinate() + item.getMySolidArea().x;
                item.getMySolidArea().y = item.getMyWorldYCoordinate() + item.getMySolidArea().y;

                switch (theHero.getMyDirection()) {
                    case "up":
                        theHero.getMySolidArea().y -= theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(item.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            item.setFound(true);
                            myGamePanel.incWinCount();
                        }
                        break;
                    case "down":
                        theHero.getMySolidArea().y += theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(item.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            item.setFound(true);
                            myGamePanel.incWinCount();
                        }
                        break;
                    case "left":
                        theHero.getMySolidArea().x -= theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(item.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            item.setFound(true);
                            myGamePanel.incWinCount();
                        }
                        break;
                    case "right":
                        theHero.getMySolidArea().x += theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(item.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            item.setFound(true);
                            myGamePanel.incWinCount();
                        }
                        break;
                }
                theHero.resetSolidArea();
                item.resetSolidArea();
            }
        }
        return index;
    }
}

    // Probably wont need as we will delete the monster on player to monster collision.
//    public void monsterToPlayer(Monster theMon){
//        theMon.getMySolidArea().x = theMon.myWorldXCoordinate + theMon.getMySolidArea().x;
//        theMon.getMySolidArea().y = theMon.myWorldYCoordinate + theMon.getMySolidArea().y;
//
//        myGamePanel.getMyHero().getMySolidArea().x =  myGamePanel.getMyHero().getMyWorldXCoordinate() +  myGamePanel.getMyHero().getMySolidArea().x;
//        myGamePanel.getMyHero().getMySolidArea().y =  myGamePanel.getMyHero().myWorldYCoordinate +  myGamePanel.getMyHero().getMySolidArea().y;
//
//        switch (theMon.getMyDirection()){
//            case "up" :
//                theMon.getMySolidArea().y -= theMon.mySpeed;
//                if(theMon.getMySolidArea().intersects( myGamePanel.getMyHero().getMySolidArea())){
//                    theMon.setMyCollision(true);
//                }
//                break;
//            case "down" :
//                theMon.getMySolidArea().y += theMon.mySpeed;
//                if(theMon.getMySolidArea().intersects( myGamePanel.getMyHero().getMySolidArea())){
//                    theMon.setMyCollision(true);
//                }
//                break;
//            case "left" :
//                theMon.getMySolidArea().x -= theMon.mySpeed;
//                if(theMon.getMySolidArea().intersects( myGamePanel.getMyHero().getMySolidArea())){
//                    theMon.setMyCollision(true);
//                }
//                break;
//            case "right" :
//                theMon.getMySolidArea().x += theMon.mySpeed;
//                if(theMon.getMySolidArea().intersects( myGamePanel.getMyHero().getMySolidArea())){
//                    theMon.setMyCollision(true);
//                }
//                break;
//        }
//        theMon.resetSolidArea();
//        myGamePanel.getMyHero().resetSolidArea();
//    }

