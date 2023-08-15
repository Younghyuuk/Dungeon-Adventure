package View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class TitlePage {
    /**
     * The main game panel.
     */
    private final GamePanel myGamePanel;
    /**
     * The pen used to draw.
     */
    private Graphics2D myPen;
    /**
     * Integer that represents the command we want.
     */
    private int myCommandNum = 0;
    /**
     * An image of a thief.
     */
    private transient BufferedImage myThiefImage;
    /**
     * An image of a warrior.
     */
    private transient BufferedImage myWarriorImage;
    /**
     * An image of a priest.
     */
    private transient BufferedImage myPriestImage;

    /**
     * Constructor that creates a TitlePage object.
     * @param theGamePanel the main game panel.
     */
    public TitlePage(GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
    }

    /**
     * Draws the title page.
     * @param theGraphics the pen used to draw.
     */
    public void draw(Graphics2D theGraphics){
        myPen = theGraphics;
        setMyImages();
        titleScreen();
    }

    /**
     * Method that sets the title Screen.
     */
    public void titleScreen(){
        int x;
        int y = myGamePanel.getSpriteSize() * 3;
        // Make the JPanels background black
        myPen.setColor(Color.DARK_GRAY);
        myPen.fillRect(0,0, myGamePanel.getMyScreenWidth(), myGamePanel.getMyScreenHeight());
        myPen.setFont(myPen.getFont().deriveFont(Font.BOLD,69F));

        String title = "Dungeon Adventure";
        x = getXToCenterString(title);

        // Title String Shadow
        myPen.setColor(Color.BLACK);
        myPen.drawString(title, x+5,y+5);
        // Title String
        myPen.setColor(Color.white);
        myPen.drawString(title, x,y);


        // Images for Title Page
        myPen.drawImage(myThiefImage,(myGamePanel.getMyScreenWidth()/2) - (4* myGamePanel.getSpriteSize()),200,myGamePanel.getSpriteSize()*2,myGamePanel.getSpriteSize() *2, null);
        myPen.drawImage(myWarriorImage,(myGamePanel.getMyScreenWidth()/2) - myGamePanel.getSpriteSize(),200,myGamePanel.getSpriteSize()*2,myGamePanel.getSpriteSize() *2, null);
        myPen.drawImage(myPriestImage,(myGamePanel.getMyScreenWidth()/2) + (2*myGamePanel.getSpriteSize()),200,myGamePanel.getSpriteSize()*2,myGamePanel.getSpriteSize() *2, null);
        myPen.setFont(myPen.getFont().deriveFont(Font.BOLD,39F));
        // New Game String
        String newGame = "New Game";
        x = getXToCenterString(newGame);
        myPen.setColor(Color.BLACK);
        myPen.drawString(newGame, x,(y + myGamePanel.getSpriteSize()*5) +5);
        myPen.setColor(Color.white);
        myPen.drawString(newGame, x,y + myGamePanel.getSpriteSize()*5);
        if (myCommandNum == 0) {
            myPen.drawString(">", x - myGamePanel.getSpriteSize(), y + myGamePanel.getSpriteSize()*5);
        }

        // Load Game String
        String load = " Load Game";
        x = getXToCenterString(load);
        myPen.setColor(Color.BLACK);
        myPen.drawString(load, x ,(y + myGamePanel.getSpriteSize()*6) +5);
        myPen.setColor(Color.white);
        myPen.drawString(load, x,y + myGamePanel.getSpriteSize()*6);
        if (myCommandNum == 1) {
            myPen.drawString(">", x - myGamePanel.getSpriteSize(), y + myGamePanel.getSpriteSize()*6);
        }
    }

    /**
     * Method used to center a string.
     * @param theText the string to center.
     * @return the x coordinate that centers the string.
     */
    public int getXToCenterString(String theText){
        int length = (int) myPen.getFontMetrics().getStringBounds(theText,myPen).getWidth();
        int x = myGamePanel.getMyScreenWidth()/2 - length/2;
        return x;
    }

    /**
     * Method that sets the image.
     */
    public void setMyImages() {
        try {
           myThiefImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_down1.png")));
           myWarriorImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_down1.png")));
           myPriestImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_down1.png")));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the command number to one.
     */
    public void setCommandToLoadGame() {
        myCommandNum = 1;
    }
    /**
     * Sets the command number to zero.
     */
    public void setCommandToNewGame() {
        myCommandNum = 0;
    }
    /**
     * Gets the command number.
     * @return the command number.
     */
    public int getMyCommandNum() {
        return myCommandNum;
    }
}
