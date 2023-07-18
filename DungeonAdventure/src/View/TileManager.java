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
        myMapArr = new int[myGamePanel.getMyMaxScreenRow()][myGamePanel.getMyMaxScreenCol()];
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
            while (col < myGamePanel.getMyMaxScreenCol() && row < myGamePanel.getMyMaxScreenRow()) {
                int number = scan.nextInt();
                myMapArr[row][col] = number;
                col++;
                if (col == myGamePanel.getMyMaxScreenCol()) {
                    col = 0;
                    row++;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D theGraphics) {

        int row = 0;
        int col = 0;

        int x = 0;
        int y = 0;
        while (col < myGamePanel.getMyMaxScreenCol() && row < myGamePanel.getMyMaxScreenRow()) {
            int tileTexture = myMapArr[row][col];
            theGraphics.drawImage(myTile[tileTexture].myImage, x, y,
                    myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
            col++;
            x += myGamePanel.getSpriteSize();
            if (col == myGamePanel.getMyMaxScreenCol()) {
                col = 0;
                x = 0;
                row++;
                y += myGamePanel.getSpriteSize();
            }
        }
    }
}
