package Model;

import View.GamePanel;

public class Collision {

    GamePanel myGamePanel;

    public Collision(final GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
    }

    public void checkTile(final Heroes theHeroes) {
        int heroLeftWorldX = theHeroes.getMyWorldXCoordinate() + theHeroes.getMySolidArea().x;
        int heroRightWorldX = theHeroes.getMyWorldXCoordinate() + theHeroes.getMySolidArea().x + theHeroes.getMySolidArea().width;
        int heroTopWorldY = theHeroes.getMyWorldYCoordinate() + theHeroes.getMySolidArea().y;
        int heroBottomWorldY = theHeroes.getMyWorldYCoordinate() + theHeroes.getMySolidArea().y + theHeroes.getMySolidArea().height;

        int heroLeftCol = heroLeftWorldX / myGamePanel.getSpriteSize();
        int heroRightCol = heroRightWorldX / myGamePanel.getSpriteSize();
        int heroTopRow = heroTopWorldY / myGamePanel.getSpriteSize();
        int heroBottomRow = heroBottomWorldY / myGamePanel.getSpriteSize();

        int tileNum1, tileNum2;

        switch (theHeroes.getMyDirection()) {
            case "up":
                heroTopRow = (heroTopWorldY - theHeroes.HEROES_MOVE_SPEED) / myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.getMyTileM().getMyMapArray()[heroTopRow][heroLeftCol];
                tileNum2 = myGamePanel.getMyTileM().getMyMapArray()[heroTopRow][heroRightCol];
                if (myGamePanel.getMyTileM().getMyTile()[tileNum1].getMyCollision() || myGamePanel.getMyTileM().getMyTile()[tileNum2].getMyCollision()) {
                    theHeroes.setMyCollision(true);
                }
                break;
            case "down":
                heroTopRow = (heroTopWorldY - theHeroes.HEROES_MOVE_SPEED) / myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.getMyTileM().getMyMapArray()[heroBottomRow][heroLeftCol];
                tileNum2 = myGamePanel.getMyTileM().getMyMapArray()[heroBottomRow][heroRightCol];
                if (myGamePanel.getMyTileM().getMyTile()[tileNum1].getMyCollision() || myGamePanel.getMyTileM().getMyTile()[tileNum2].getMyCollision()) {
                    theHeroes.setMyCollision(true);
                }
                break;
            case "left":
                heroTopRow = (heroTopWorldY - theHeroes.HEROES_MOVE_SPEED) / myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.getMyTileM().getMyMapArray()[heroTopRow][heroLeftCol];
                tileNum2 = myGamePanel.getMyTileM().getMyMapArray()[heroBottomRow][heroLeftCol];
                if (myGamePanel.getMyTileM().getMyTile()[tileNum1].getMyCollision() || myGamePanel.getMyTileM().getMyTile()[tileNum2].getMyCollision()) {
                    theHeroes.setMyCollision(true);
                }
                break;
            case "right":
                heroTopRow = (heroTopWorldY - theHeroes.HEROES_MOVE_SPEED) / myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.getMyTileM().getMyMapArray()[heroTopRow][heroRightCol];
                tileNum2 = myGamePanel.getMyTileM().getMyMapArray()[heroBottomRow][heroRightCol];
                if (myGamePanel.getMyTileM().getMyTile()[tileNum1].getMyCollision() || myGamePanel.getMyTileM().getMyTile()[tileNum2].getMyCollision()) {
                    theHeroes.setMyCollision(true);
                }
                break;
        }
    }
}
