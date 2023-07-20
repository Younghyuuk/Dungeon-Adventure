package View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class TileManager {

    private final GamePanel myGamePanel;
    private final Tile[] myTile;
    private final int myMapArr[][];

    public TileManager(final GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
        myTile = new Tile[10];
        myMapArr = new int[myGamePanel.getMyWorldRow()][myGamePanel.getMyWorldCol()];
        getTileImage();
        loadMap("/map/dungeon.txt");
    }

    public void getTileImage() {

        try {
            myTile[0] = new Tile();
            myTile[0].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/sandBrickFloor.png"))));
            myTile[1] = new Tile();
            myTile[1].setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/stoneWall.png"))));
            myTile[1].setMyCollision(true);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int[][] getMyMapArray() {
        return myMapArr;
    }
    public Tile[] getMyTile() {
        return myTile;
    }
    // This method reads a txt file.
    public void loadMap(final String theMap) {
        int col = 0;
        int row = 0;
        try {
            InputStream input = getClass().getResourceAsStream(theMap);
            Scanner scan = new Scanner(Objects.requireNonNull(input));
            while (col < myGamePanel.getMyWorldCol() && row < myGamePanel.getMyWorldRow()) {
                int number = scan.nextInt();
                myMapArr[row][col] = number;
                col++;
                if (col == myGamePanel.getMyWorldCol()) {
                    col = 0;
                    row++;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(final Graphics2D theGraphics) {

        int worldRow = 0;
        int worldCol = 0;

        while (worldCol < myGamePanel.getMyWorldCol() && worldRow < myGamePanel.getMyWorldRow()) {
            int tileTexture = myMapArr[worldRow][worldCol];

            int worldX = worldCol * myGamePanel.getSpriteSize();
            int worldY = worldRow * myGamePanel.getSpriteSize();
            // myThief may need to change.
            int screenX = worldX - myGamePanel.getMyHero().getMyWorldXCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleX();
            int screenY = worldY - myGamePanel.getMyHero().getMyWorldYCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleY();

            // draw only the tiles that are width a certain screen size.
            if (worldX + myGamePanel.getSpriteSize() > myGamePanel.getMyHero().getMyWorldXCoordinate() - myGamePanel.getMyHero().getMyScreensMiddleX() &&
                    worldX - myGamePanel.getSpriteSize() < myGamePanel.getMyHero().getMyWorldXCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleX() &&
                    worldY + myGamePanel.getSpriteSize() > myGamePanel.getMyHero().getMyWorldYCoordinate() - myGamePanel.getMyHero().getMyScreensMiddleY() &&
                    worldY - myGamePanel.getSpriteSize() < myGamePanel.getMyHero().getMyWorldYCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleY()) {
                theGraphics.drawImage(myTile[tileTexture].getMyImage(), screenX, screenY, myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
            }

            worldCol++;

            if (worldCol == myGamePanel.getMyWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
