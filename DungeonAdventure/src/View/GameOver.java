package View;

import java.awt.*;

public class GameOver {

    private GamePanel myGamePanel;
    private Graphics2D myPen;
    private int myCommandNum = 0;

    public GameOver(GamePanel theGamePanel){
        myGamePanel = theGamePanel;
    }

    public void draw(Graphics2D theGraphics){
        myPen = theGraphics;
        int x;
        int y = myGamePanel.getSpriteSize() * 3;
        // Make the JPanels background black
        myPen.setColor(Color.black);
        myPen.fillRect(0,0, myGamePanel.getMyScreenWidth(), myGamePanel.getMyScreenHeight());

        myPen.setFont(myPen.getFont().deriveFont(Font.BOLD,75F));
        String title = "GAME OVER";
        x = getXToCenterString(title);
        // Title String Shadow
        myPen.setColor(Color.DARK_GRAY);
        myPen.drawString(title,x+5,y+5);
        // Title String
        myPen.setColor(Color.RED);
        myPen.drawString(title, x,y);

        for (int i = 1; i < 30; i++) {
            myPen.setFont(myPen.getFont().deriveFont(Font.BOLD,20F));
            String message = "you died you died you died you died you died you died you died you died ";
            x = getXToCenterString(message);
            // Title String Shadow
            myPen.setColor(Color.darkGray);
            myPen.drawString(message, x,y + myGamePanel.getSpriteSize() +5*i);
            // Title String
            myPen.setColor(Color.RED);
            myPen.drawString(message, x,y + myGamePanel.getSpriteSize());
        }

        myPen.setFont(myPen.getFont().deriveFont(Font.BOLD,39F));

        // New Game String
        String newGame = "Try Again";
        x = getXToCenterString(newGame);
        myPen.setColor(Color.DARK_GRAY);
        myPen.drawString(newGame, x,(y + myGamePanel.getSpriteSize()*5) +5);
        myPen.setColor(Color.white);
        myPen.drawString(newGame, x,y + myGamePanel.getSpriteSize()*5);
        if (myCommandNum == 0) {
            myPen.drawString(">", x - myGamePanel.getSpriteSize(), y + myGamePanel.getSpriteSize()*5);
        }
        // Load Game String
        String quit = " Quit";
        x = getXToCenterString(quit);
        myPen.setColor(Color.DARK_GRAY);
        myPen.drawString(quit, x ,(y + myGamePanel.getSpriteSize()*6) +5);
        myPen.setColor(Color.white);
        myPen.drawString(quit, x,y + myGamePanel.getSpriteSize()*6);
        if (myCommandNum == 1) {
            myPen.drawString(">", x - myGamePanel.getSpriteSize(), y + myGamePanel.getSpriteSize()*6);
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
