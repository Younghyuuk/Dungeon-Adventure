package View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class WinPage {

    private GamePanel myGamePanel;
    private Graphics2D myPen;
    private int myCommandNum = 0;
    private BufferedImage myThiefImage;
    private BufferedImage myWarriorImage;
    private BufferedImage myPriestImage;

    public WinPage(GamePanel theGamePanel){
        myGamePanel = theGamePanel;
        setMyImages();
    }

    public void draw(Graphics2D theGraphics){

        myPen = theGraphics;
        int x;
        int y = myGamePanel.getSpriteSize() * 3;
        // Make the JPanels background black
        myPen.setColor(Color.WHITE);
        myPen.fillRect(0,0, myGamePanel.getMyScreenWidth(), myGamePanel.getMyScreenHeight());

        myPen.setFont(myPen.getFont().deriveFont(Font.BOLD,75F));
        String title = "YOU WON!!!";
        x = getXToCenterString(title);
        // Title String Shadow
        myPen.setColor(Color.LIGHT_GRAY);
        myPen.drawString(title,x+5,y+5);
        // Title String
        myPen.setColor(Color.YELLOW);
        myPen.drawString(title, x,y);


        // Images for Title Page
        myPen.drawImage(myThiefImage,(myGamePanel.getMyScreenWidth()/2) - (4* myGamePanel.getSpriteSize()),200,myGamePanel.getSpriteSize()*2,myGamePanel.getSpriteSize() *2, null);
        myPen.drawImage(myWarriorImage,(myGamePanel.getMyScreenWidth()/2) - myGamePanel.getSpriteSize(),200,myGamePanel.getSpriteSize()*2,myGamePanel.getSpriteSize() *2, null);
        myPen.drawImage(myPriestImage,(myGamePanel.getMyScreenWidth()/2) + (2*myGamePanel.getSpriteSize()),200,myGamePanel.getSpriteSize()*2,myGamePanel.getSpriteSize() *2, null);
        myPen.setFont(myPen.getFont().deriveFont(Font.BOLD,39F));

        myPen.setFont(myPen.getFont().deriveFont(Font.BOLD,39F));
        // New Game String
        String newGame = "Play Again";
        x = getXToCenterString(newGame);
        myPen.setColor(Color.BLACK);
        myPen.drawString(newGame, x,y + myGamePanel.getSpriteSize()*5);
        if (myCommandNum == 0) {
            myPen.drawString(">", x - myGamePanel.getSpriteSize(), y + myGamePanel.getSpriteSize()*5);
        }
        // Load Game String
        String quit = " Quit";
        x = getXToCenterString(quit);
        myPen.setColor(Color.BLACK);
        myPen.drawString(quit, x,y + myGamePanel.getSpriteSize()*6);
        if (myCommandNum == 1) {
            myPen.drawString(">", x - myGamePanel.getSpriteSize(), y + myGamePanel.getSpriteSize()*6);
        }
    }
    public void setMyImages() {
        try {
            myThiefImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/WinPage/win0.png")));
            myWarriorImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/WinPage/win1.png")));
            myPriestImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/WinPage/win2.png")));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int getXToCenterString(String theText){
        int length = (int) myPen.getFontMetrics().getStringBounds(theText,myPen).getWidth();
        int x = myGamePanel.getMyScreenWidth()/2 - length/2;
        return x;
    }
    public void setCommandToLoadGame() {
        myCommandNum = 1;
    }
    public void setCommandToNewGame() {
        myCommandNum = 0;
    }
    public int getMyCommandNum() {return myCommandNum;
    }
}
