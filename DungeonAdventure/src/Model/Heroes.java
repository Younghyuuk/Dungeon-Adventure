package Model;

import Control.Keyboard;
import View.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Heroes extends DungeonCharacter {

    private double myBlockChance;
    public int speed;
    public int x, y;

    GamePanel myGamePanel;
    Keyboard myKeyInputs;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "up";
    public int spriteCounter = 0;
    public int spriteNum = 1;

    protected Heroes(int theHp, String theChName, int theAttackSpeed, int theMinDamage, int theMaxDamage,
                     double theHitChance, double theBlockChance, GamePanel theGamePanel, Keyboard theKeyBoard) {

        super(theHp, theChName, theAttackSpeed, theMinDamage, theMaxDamage, theHitChance);

        myGamePanel = theGamePanel;
        myKeyInputs = theKeyBoard;
        myBlockChance = theBlockChance;

        //testing
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {
        if (myKeyInputs.up ){
            direction = "up";
            y -= speed;
        }
        else if (myKeyInputs.down){
            direction = "down";
            y += speed;
        }
        else if (myKeyInputs.left){
            direction = "left";
            x -= speed;
        }
        else if (myKeyInputs.right){
            direction = "right";
            x += speed;
        }
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = spriteNum == 1 ? 2 : 1;
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D theGraphics) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                } else {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                } else {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                } else {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                } else {
                    image = right2;
                }
                break;
        }
        theGraphics.drawImage(image, x, y, myGamePanel.getSpriteSize(),myGamePanel.getSpriteSize(),null);
    }
    public abstract void getHeroesImage();



    public abstract void regularAttack(DungeonCharacter theOpp);
    public abstract void specialSkill(DungeonCharacter theOpp);
    public double getBlockChance() {
        return myBlockChance;
    }
    public void setBlockChance(double theBlockChance) {
        myBlockChance = theBlockChance;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()); // Include common properties from DungeonCharacter
        // Add hero-specific properties
        sb.append("Chance to Block: ").append(getBlockChance()).append("\n");
        return sb.toString();
    }
}
