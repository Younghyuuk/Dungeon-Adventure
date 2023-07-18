package Model;

import View.GamePanel;

public class Collision {

    GamePanel myGamePanel;
    public Collision(GamePanel theGamePanel){
        myGamePanel = theGamePanel;
    }

    public void checkTile(Heroes theHeroes) {
        int heroLeftWorldX = theHeroes.myX + theHeroes.mySolidArea.x;
        int heroRightWorldX = theHeroes.myX + theHeroes.mySolidArea.x + theHeroes.mySolidArea.width;
        int heroTopWorldY = theHeroes.myY + theHeroes.mySolidArea.y;
        int heroBottomWorldY = theHeroes.myY + theHeroes.mySolidArea.y + theHeroes.mySolidArea.height;

        int heroLeftCol = heroLeftWorldX/myGamePanel.getSpriteSize();
        int heroRightCol = heroRightWorldX/myGamePanel.getSpriteSize();
        int heroTopRow = heroTopWorldY/myGamePanel.getSpriteSize();
        int heroBottomRow = heroBottomWorldY/myGamePanel.getSpriteSize();

        int tileNum1, tileNum2;

        switch(theHeroes.direction) {
            case "up" :
                heroTopRow = (heroTopWorldY - theHeroes.speed)/myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.myTileM.myMapArr[heroTopRow][heroLeftCol];
                tileNum2 = myGamePanel.myTileM.myMapArr[heroTopRow][heroRightCol];
                if(myGamePanel.myTileM.myTile[tileNum1].myCollision || myGamePanel.myTileM.myTile[tileNum2].myCollision){
                    theHeroes.myCollision = true;
                }
                break;
            case "down" :
                heroTopRow = (heroTopWorldY - theHeroes.speed)/myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.myTileM.myMapArr[heroBottomRow][heroLeftCol];
                tileNum2 = myGamePanel.myTileM.myMapArr[heroBottomRow][heroRightCol];
                if(myGamePanel.myTileM.myTile[tileNum1].myCollision || myGamePanel.myTileM.myTile[tileNum2].myCollision){
                    theHeroes.myCollision = true;
                }
                break;
            case "left":
                heroTopRow = (heroTopWorldY - theHeroes.speed)/myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.myTileM.myMapArr[heroTopRow][heroLeftCol];
                tileNum2 = myGamePanel.myTileM.myMapArr[heroBottomRow][heroLeftCol];
                if(myGamePanel.myTileM.myTile[tileNum1].myCollision || myGamePanel.myTileM.myTile[tileNum2].myCollision){
                    theHeroes.myCollision = true;
                }
                break;
            case "right" :
                heroTopRow = (heroTopWorldY - theHeroes.speed)/myGamePanel.getSpriteSize();
                tileNum1 = myGamePanel.myTileM.myMapArr[heroTopRow][heroRightCol];
                tileNum2 = myGamePanel.myTileM.myMapArr[heroBottomRow][heroRightCol];
                if(myGamePanel.myTileM.myTile[tileNum1].myCollision || myGamePanel.myTileM.myTile[tileNum2].myCollision){
                    theHeroes.myCollision = true;
                }
                break;
        }
    }
}
