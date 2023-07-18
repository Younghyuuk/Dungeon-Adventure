package View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

public class TileManager {

    final GamePanel myGamePanel;
    final Tile[] myTile;
    final int myMapArr[][];

    public TileManager(GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
        myTile = new Tile[10];
        myMapArr = new int[myGamePanel.getMyWorldRow()][myGamePanel.getMyWorldCol()];
        getTileImage();
        loadMap("/map/roomExample.txt");
    }

    public void getTileImage() {

                   try {
                myTile[0] = new Tile();
                myTile[0].myImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/sandBrickFloor.png")));
                myTile[1] = new Tile();
                myTile[1].myImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/stoneWall.png")));



            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // This method reads a txt file.
    public void loadMap(String theMap) {
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

    public void draw(Graphics2D theGraphics) {

        int worldRow = 0;
        int worldCol = 0;

        while (worldCol < myGamePanel.getMyWorldCol() && worldRow < myGamePanel.getMyWorldRow()) {
            int tileTexture = myMapArr[worldRow][worldCol];

            int worldX = worldCol * myGamePanel.getSpriteSize();
            int worldY = worldRow * myGamePanel.getSpriteSize();
            // myThief may need to change.
            int screenX = worldX - myGamePanel.myThief.myX + myGamePanel.myThief.myMiddleX;
            int screenY = worldY - myGamePanel.myThief.myY + myGamePanel.myThief.myMiddleY;

            // draw only the tiles that are width a certain screen size.
            if (worldX +  myGamePanel.getSpriteSize() > myGamePanel.myThief.myX - myGamePanel.myThief.myMiddleX &&
                    worldX - myGamePanel.getSpriteSize() < myGamePanel.myThief.myX + myGamePanel.myThief.myMiddleX &&
                    worldY + myGamePanel.getSpriteSize() > myGamePanel.myThief.myY - myGamePanel.myThief.myMiddleY &&
                    worldY - myGamePanel.getSpriteSize() < myGamePanel.myThief.myY + myGamePanel.myThief.myMiddleY){
                theGraphics.drawImage(myTile[tileTexture].myImage, screenX, screenY, myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
            }

            worldCol++;

            if (worldCol == myGamePanel.getMyWorldCol()) {
                worldCol= 0;
                worldRow++;
            }
        }
    }
}
