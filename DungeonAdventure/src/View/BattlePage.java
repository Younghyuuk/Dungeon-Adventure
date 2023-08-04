package View;

import Model.Battle;

import java.awt.*;

public class BattlePage {
    Battle myBattle;
    private final GamePanel myGamePanel;

    public BattlePage(final GamePanel theGamePanel){
        myGamePanel = theGamePanel;
    }

    public void draw (Graphics2D theGraphics){
        Graphics2D myPen = theGraphics;
        myPen.setColor(Color.black);
        myPen.fillRect(2*myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), 12*myGamePanel.getSpriteSize(),  3*myGamePanel.getSpriteSize());
        myPen.setColor(Color.WHITE);
//        myPen.drawString(text1,3*myGamePanel.getSpriteSize(), 2*myGamePanel.getSpriteSize());
//        myPen.drawString(text2,3*myGamePanel.getSpriteSize(), 2*myGamePanel.getSpriteSize() + myGamePanel.getSpriteSize()/2);
//        myPen.drawString(text3,3*myGamePanel.getSpriteSize(), 3*myGamePanel.getSpriteSize());
    }
}
