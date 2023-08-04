package View;

import Model.Battle;

import java.awt.*;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.text.AttributedString;

public class BattlePage {
    Battle myBattle;
    private final GamePanel myGamePanel;
    private String[] myBattleLog;

    public BattlePage(final GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
    }

    public void setMyBattleLog(String[] theBattle) {
        myBattleLog = theBattle;
    }

    public void draw(Graphics2D theGraphics) {
        Graphics2D myPen = theGraphics;
        myPen.setColor(Color.black);
//      myPen.fillRect(2 * myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), 12 * myGamePanel.getSpriteSize(), 6 * myGamePanel.getSpriteSize());
        myPen.fillRect(0,0, myGamePanel.getMyScreenWidth(), myGamePanel.getMyScreenHeight());
        myPen.setColor(Color.WHITE);
        String text = myBattleLog[0];
        if (text != null) {
            LineBreakMeasurer lineBreaker = new LineBreakMeasurer(new AttributedString(text)
                    .getIterator(), theGraphics.getFontRenderContext());
            int y = myGamePanel.getSpriteSize();
            while (lineBreaker.getPosition() < text.length()) {
                TextLayout textLayout = lineBreaker.nextLayout(600);
                y += textLayout.getAscent();

                textLayout.draw(theGraphics, 2 * myGamePanel.getSpriteSize(), y);
                y += textLayout.getDescent() + textLayout.getLeading();
            }
        }
    }
}
