package Model;

import View.GamePanel;

import java.util.List;

/**
 * This class checks if the player collides with any objects within the dungeon.
 */
public class Collision {
    /**
     * The game panel that the character and entities are drawn on.
     */
    private GamePanel myGamePanel;
    /**
     * The battle that happens when the character fights a monster.
     */
    private Battle myBattle;

    /**
     * Sets up the collision object with the correct game panel.
     *
     * @param theGamePanel The game panel the game is being drawn on.
     */
    public Collision(final GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
    }

    /**
     * Goes through all the tiles in the dungeon and checks if the player collided with any. <br>
     * If the player hits a tile that they can't move through, we stop the player.
     *
     * @param dungeonChar   The character that we are checking for collisions with.
     */
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

    /**
     * Goes through all the entities, or monsters, in the dungeon and checks if the player collided with any. <br>
     * If the player hits a monster, a battle will happen.
     *
     * @param theHero       The character that we are checking for collisions with.
     * @param theMonsters   The list of every monster in the dungeon.
     * @return The amount of collisions made with items.
     */
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

//        theHero.setHp(125);
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

    /**
     * Goes through all the items in the dungeon and checks if the player collided with any. <br>
     * If the player hits a pit or a health potion, we will set their HP accordingly.
     *
     * @param theHero       The character that we are checking for collisions with.
     * @param theItemList   The list of every item in the dungeon.
     * @return The amount of collisions made with items.
     */
    public int checkItem(final Heroes theHero, final List<Item> theItemList) {
        // We preset the indices
        int index = 999;
        int i = 0;
        // Then go through the item list looking at every item that hasn't been found
        for (Item item : theItemList) {
            if (!item.getFound()) {
                theHero.getMySolidArea().x = theHero.myWorldXCoordinate + theHero.getMySolidArea().x;
                theHero.getMySolidArea().y = theHero.myWorldYCoordinate + theHero.getMySolidArea().y;

                item.getMySolidArea().x = item.getMyWorldXCoordinate() + item.getMySolidArea().x;
                item.getMySolidArea().y = item.getMyWorldYCoordinate() + item.getMySolidArea().y;

                // Then we go through every direction the player might be going in and check for a collision
                // If one happens we adjust their hp accordingly
                switch (theHero.getMyDirection()) {
                    case "up" -> {
                        theHero.getMySolidArea().y -= theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(item.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            item.setFound(true);
                            // We need to check if we hit a health potion
                            if (item instanceof HealthPotion) {
                                theHero.setHp(theHero.getHp() + ((HealthPotion) item).getHealthBack());
                            } else if (item instanceof Pit) { // Or if we hit a pit
                                theHero.setHp(theHero.getHp() - ((Pit) item).getPitDamage());
                            }
                        }
                    }
                    case "down" -> {
                        theHero.getMySolidArea().y += theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(item.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            item.setFound(true);
                            // We need to check if we hit a health potion
                            if (item instanceof HealthPotion) {
                                theHero.setHp(theHero.getHp() + ((HealthPotion) item).getHealthBack());
                            } else if (item instanceof Pit) { // Or if we hit a pit
                                theHero.setHp(theHero.getHp() - ((Pit) item).getPitDamage());
                            }
                        }
                    }
                    case "left" -> {
                        theHero.getMySolidArea().x -= theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(item.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            item.setFound(true);
                            // We need to check if we hit a health potion
                            if (item instanceof HealthPotion) {
                                theHero.setHp(theHero.getHp() + ((HealthPotion) item).getHealthBack());
                            } else if (item instanceof Pit) { // Or if we hit a pit
                                theHero.setHp(theHero.getHp() - ((Pit) item).getPitDamage());
                            }
                        }
                    }
                    case "right" -> {
                        theHero.getMySolidArea().x += theHero.mySpeed;
                        if (theHero.getMySolidArea().intersects(item.getMySolidArea())) {
                            theHero.setMyCollision(true);
                            index = i++;
                            item.setFound(true);
                            // We need to check if we hit a health potion
                            if (item instanceof HealthPotion) {
                                theHero.setHp(theHero.getHp() + ((HealthPotion) item).getHealthBack());
                            } else if (item instanceof Pit) { // Or if we hit a pit
                                theHero.setHp(theHero.getHp() - ((Pit) item).getPitDamage());
                            }
                        }
                    }
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

