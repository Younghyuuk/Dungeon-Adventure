package View;

import java.awt.*;
import java.util.Locale;

public class AboutPage {

    private final GamePanel myGamePanel;

    public AboutPage(final GamePanel theGamePanel){
        myGamePanel = theGamePanel;
    }

    public void draw (Graphics2D theGraphics){
        Graphics2D myPen = theGraphics;
        myPen.setColor(Color.black);
        myPen.fillRect(2*myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), 12*myGamePanel.getSpriteSize(),  3*myGamePanel.getSpriteSize());
        String text1 ="Welcome to Dungeon Adventure. The goal of this game is to traverse through the maze and to";
        String text2 = "find all of the four pillars of JAVA. Be careful of the monsters that roam around the maze!!!";
        String text3 = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>GOOD LUCK<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
        myPen.setColor(Color.WHITE);
        myPen.drawString(text1,3*myGamePanel.getSpriteSize(), 2*myGamePanel.getSpriteSize());
        myPen.drawString(text2,3*myGamePanel.getSpriteSize(), 2*myGamePanel.getSpriteSize() + myGamePanel.getSpriteSize()/2);
        myPen.drawString(text3,3*myGamePanel.getSpriteSize(), 3*myGamePanel.getSpriteSize());
    }
}
