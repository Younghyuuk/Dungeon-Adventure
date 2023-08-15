package View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class WinPage {

    /**
     * The main game panel.
     */
    GamePanel myGamePanel;
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
    public WinPage(GamePanel theGamePanel){
        myGamePanel = theGamePanel;
        setMyImages();
    }

    /**
     * A method that draws the Win Page.
     * @param theGraphics the pen used to draw.
     */
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

    /**
     * Sets the images of the thief, warrior and priest.
     */
    public void setMyImages() {
        try {
            myThiefImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/WinPage/win0.png")));
            myWarriorImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/WinPage/win1.png")));
            myPriestImage = read(Objects.requireNonNull(getClass().getResourceAsStream("/WinPage/win2.png")));
        }catch (IOException e) {
            throw new RuntimeException(e);
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
     * Sets the command number to zero.
     */
    public void setCommandToNewGame() {
        myCommandNum = 0;
    }
    /**
     * Sets the command number to one.
     */
    public void setCommandToQuitGame() {
        myCommandNum = 1;
    }
    /**
     * Gets the command number.
     * @return the command number.
     */
    public int getMyCommandNum() {return myCommandNum;
    }
}
