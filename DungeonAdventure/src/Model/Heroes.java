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
    }
    public abstract void draw(Graphics2D theGraphics);
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
