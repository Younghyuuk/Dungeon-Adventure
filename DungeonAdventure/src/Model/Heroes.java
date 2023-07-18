package Model;

import Control.Keyboard;
import View.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Heroes class which extends from DungeonCharacter
 * we will be creating 3 other subclasses which extends
 * off of Heroes which will be Priestess, Thief, and Warrior.
 */
public abstract class Heroes extends DungeonCharacter {

    /**
     * The double that gives the chance that the Hero will block.
     */
    private double myBlockChance;

    public int speed;



    GamePanel myGamePanel;
    Keyboard myKeyInputs;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public final int myMiddleX;
    public final int myMiddleY;
    public int myX, myY;
    /**
     * Heroes constructor that initializes the hp, name, attack speed, min damage, max damage,
     * hit chance, block chance, gamepanel, and keyboard of the Hero.
     *
     * @param theHp given hp to hero.
     * @param theChName given name to the hero.
     * @param theAttackSpeed the speed at which the hero attacks.
     * @param theMinDamage the minimum amount of damage hero can do.
     * @param theMaxDamage the maximum amount of damage a hero can do.
     * @param theHitChance the chance at which the hero attacks land.
     * @param theBlockChance the chance the hero has to blocking an attack.
     * @param theGamePanel the gamepanel of the hero.
     * @param theKeyBoard the keyboard input of the hero.
     */
    protected Heroes(int theHp, String theChName, int theAttackSpeed, int theMinDamage, int theMaxDamage,
                     double theHitChance, double theBlockChance, GamePanel theGamePanel, Keyboard theKeyBoard) {

        super(theHp, theChName, theAttackSpeed, theMinDamage, theMaxDamage, theHitChance);

        myGamePanel = theGamePanel;
        myKeyInputs = theKeyBoard;
        myBlockChance = theBlockChance;


        //testing
        myMiddleX = myGamePanel.getMyScreenWidth()/2 - (myGamePanel.getSpriteSize()/2);
        myMiddleY = myGamePanel.getMyScreenHeight()/2 - (myGamePanel.getSpriteSize()/2);

        myX = 7 * myGamePanel.getSpriteSize();
        myY = 6 * myGamePanel.getSpriteSize();
        speed = 4;
    }

    public void update() {
        if (myKeyInputs.up ){
            direction = "up";
            myY -= speed;
        }
        else if (myKeyInputs.down){
            direction = "down";
            myY += speed;
        }
        else if (myKeyInputs.left){
            direction = "left";
            myX -= speed;
        }
        else if (myKeyInputs.right){
            direction = "right";
            myX += speed;
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
        theGraphics.drawImage(image, myMiddleX, myMiddleY, myGamePanel.getSpriteSize(),myGamePanel.getSpriteSize(),null);
    }
    public abstract void getHeroesImage();


    /**
     * Abstract method in which we implement so that the hero classes
     * do a normal attack.
     *
     * @param theOpp opponent in which the attack will be targeted towards.
     */
    public abstract void regularAttack(DungeonCharacter theOpp);

    /**
     * The special skill which each class will have unique to be their own
     * whether they do a special attack or skill is dependent on the class.
     *
     * @param theTarget which the special skill will be aimed towards.
     */
    public abstract void specialSkill(DungeonCharacter theTarget);

    /**
     * Gets the block chance of the hero.
     *
     * @return double block chance
     */
    public double getBlockChance() {
        return myBlockChance;
    }

    /**
     * Sets the block chance of said hero.
     *
     * @param theBlockChance the amount of chance they have to block.
     */
    public void setBlockChance(double theBlockChance) {
        myBlockChance = theBlockChance;
    }

    /**
     * toString method of Heroes to test values.
     *
     * @return StringBuilder sb
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()); // Include common properties from DungeonCharacter
        // Add hero-specific properties
        sb.append("Chance to Block: ").append(getBlockChance()).append("\n");
        return sb.toString();
    }
}
