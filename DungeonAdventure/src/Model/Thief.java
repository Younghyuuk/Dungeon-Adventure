package Model;

import Control.Keyboard;
import View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Thief extends Heroes{
    private static final double SURPRISE_ATTACK_CHANCE = 0.4;
    private static final double SURPRISE_FAIL_CHANCE = 0.2;

    public Thief(GamePanel theGamePanel, Keyboard theKeyboard) {
        super(75, "Thief", 6, 20, 40, 0.8, 0.4, theGamePanel,theKeyboard);
        getPlayerImage();
    }

    @Override
    public void regularAttack(DungeonCharacter theOpp) {
        attackBehavior(theOpp);
    }

    // needs fixing
    @Override
    public void specialSkill(DungeonCharacter theOpp) {
        if (Math.random() <= SURPRISE_ATTACK_CHANCE) {
           if (Math.random() <= SURPRISE_FAIL_CHANCE) {

           } else {
               attackBehavior(theOpp);
               attackBehavior(theOpp);
           }
        }
    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/thief/thief_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/thief/thief_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/thief/thief_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/thief/thief_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/thief/thief_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/thief/thief_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/thief/thief_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/thief/thief_right2.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void draw(Graphics2D theGraphics) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }
        theGraphics.drawImage(image, x, y, myGamePanel.GAME_SPRITE_SIZE,myGamePanel.GAME_SPRITE_SIZE,null);
    }

}
