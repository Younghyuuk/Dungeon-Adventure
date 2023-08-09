package View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class CharacterSelectionPage {

    GamePanel myGamePanel;
    private transient BufferedImage myThiefImage;
    private transient BufferedImage myWarriorImage;
    private transient BufferedImage myPriestImage;
    private Graphics2D myPen;
    private int myCommandNum = 0;

    public CharacterSelectionPage(GamePanel theGamePanel){
        myGamePanel = theGamePanel;

    }

    public void draw(Graphics2D theGraphics){
        myPen = theGraphics;
        setMyImages();
        selectCharacter();
    }
    public void selectCharacter(){
        int x;
        int y = myGamePanel.getSpriteSize() * 3;
        // Make the JPanels background black
        myPen.setColor(Color.DARK_GRAY);
        myPen.fillRect(0,0, myGamePanel.getMyScreenWidth(), myGamePanel.getMyScreenHeight());
        myPen.setFont(myPen.getFont().deriveFont(Font.BOLD,69F));

        String title = "Select Your Hero";
        x = getXToCenterString(title);

        // Title String Shadow
        myPen.setColor(Color.BLACK);
        myPen.drawString(title, x+5,y+5);
        // Title String
        myPen.setColor(Color.white);
        myPen.drawString(title, x,y);

        // Images for HeroPage
        myPen.drawImage(myThiefImage,(myGamePanel.getMyScreenWidth()/2) - (4* myGamePanel.getSpriteSize()),200,myGamePanel.getSpriteSize()*2,myGamePanel.getSpriteSize() *2, null);
        myPen.drawImage(myWarriorImage,(myGamePanel.getMyScreenWidth()/2) - myGamePanel.getSpriteSize(),200,myGamePanel.getSpriteSize()*2,myGamePanel.getSpriteSize() *2, null);
        myPen.drawImage(myPriestImage,(myGamePanel.getMyScreenWidth()/2) + (2*myGamePanel.getSpriteSize()),200,myGamePanel.getSpriteSize()*2,myGamePanel.getSpriteSize() *2, null);

        if (myCommandNum == 0){
            myPen.drawString("^",(myGamePanel.getMyScreenWidth()/2) - (4 * myGamePanel.getSpriteSize() - myGamePanel.getSpriteSize()/2),400);
        } else if (myCommandNum == 1) {
            myPen.drawString("^",(myGamePanel.getMyScreenWidth()/2) - (myGamePanel.getSpriteSize() - myGamePanel.getSpriteSize()/2),400);
        } else {
            myPen.drawString("^",(myGamePanel.getMyScreenWidth()/2) + (2 * myGamePanel.getSpriteSize() + myGamePanel.getSpriteSize()/2),400);
        }
    }

    public int getXToCenterString(String theText){
        int length = (int) myPen.getFontMetrics().getStringBounds(theText,myPen).getWidth();
        int x = myGamePanel.getMyScreenWidth()/2 - length/2;
        return x;
    }
    public void setMyImages() {
        try {
            myThiefImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_down1.png")));
            myWarriorImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_down1.png")));
            myPriestImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_down1.png")));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void incMyCommandNum() {
        myCommandNum++;
    }
    public void decMyCommandNum() {
        myCommandNum--;
    }
    public void setMyCommandNum(int theNumber){
        myCommandNum = theNumber;
    }
    public int getMyCommandNum() {
        return myCommandNum;
    }
}
