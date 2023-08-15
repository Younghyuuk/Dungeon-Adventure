package View;

import java.awt.*;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.text.AttributedString;

public class BattlePage {
    /**
     * A game panel object.
     */
    private final GamePanel myGamePanel;
    /**
     * A array that holds the battle log strings.
     */
    private String[] myBattleLog;

    /**
     * A Constructor that creats a Battle Page Object.
     * @param theGamePanel the main gamePanel.
     */
    public BattlePage(final GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
    }

    /**
     * A mehtod that sets the battle log.
     * @param theBattle a battle log that was created during monster/player collision.
     */
    public void setMyBattleLog(String[] theBattle) {
        myBattleLog = theBattle;
    }

    /**
     * The method that draws the battle page.
     * @param theGraphics the pen used to draw the battle page.
     */
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
