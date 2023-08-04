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

    private final int myScreensMiddleX;
    private final int myScreensMiddleY;
    Keyboard myKeyInputs;
    /**
     * The double that gives the chance that the Hero will block.
     */
    private double myBlockChance;

    /**
     * Heroes constructor that initializes the hp, name, attack speed, min damage, max damage,
     * hit chance, block chance, gamepanel, and keyboard of the Hero.
     *
     * @param theHp          given hp to hero.
     * @param theChName      given name to the hero.
     * @param theAttackSpeed the speed at which the hero attacks.
     * @param theMinDamage   the minimum amount of damage hero can do.
     * @param theMaxDamage   the maximum amount of damage a hero can do.
     * @param theHitChance   the chance at which the hero attacks land.
     * @param theBlockChance the chance the hero has to blocking an attack.
     * @param theGamePanel   the gamepanel of the hero.
     * @param theKeyBoard    the keyboard input of the hero.
     */
    protected Heroes(final int theHp, final String theChName, final int theAttackSpeed, final int theMinDamage, final int theMaxDamage,
                     final double theHitChance, final double theBlockChance, final GamePanel theGamePanel, final Keyboard theKeyBoard) {

        super(theHp, theChName, theAttackSpeed, theMinDamage, theMaxDamage, theHitChance, theGamePanel);
        myKeyInputs = theKeyBoard;
        myBlockChance = theBlockChance;

        myScreensMiddleX = myGamePanel.getMyScreenWidth() / 2 - (myGamePanel.getSpriteSize() / 2);
        myScreensMiddleY = myGamePanel.getMyScreenHeight() / 2 - (myGamePanel.getSpriteSize() / 2);
        myWorldXCoordinate = (myGamePanel.getMyWorldCol() * myGamePanel.getSpriteSize()) / 2;
        myWorldYCoordinate = (myGamePanel.getMyWorldRow() * myGamePanel.getSpriteSize()) / 2;


        mySolidArea = new Rectangle(12, 12, myGamePanel.getSpriteSize() - 24, myGamePanel.getSpriteSize() - 24);
        mySpeed = 6
        ;
    }

    public int getMyScreensMiddleX() {
        return myScreensMiddleX;
    }

    public int getMyScreensMiddleY() {
        return myScreensMiddleY;
    }

    public void resetSolidArea() {
        mySolidArea.x = 12;
        mySolidArea.y = 12;
    }

    public void interactMonster(int i) {
        if (i != 999) {
            System.out.println("You hit the monster");
        }

    }

    public void update() {
        if (myKeyInputs.up) {
            myDirection = "up";
        } else if (myKeyInputs.down) {
            myDirection = "down";
        } else if (myKeyInputs.left) {
            myDirection = "left";
        } else if (myKeyInputs.right) {
            myDirection = "right";
        }

        // Checking Map Tile Collision
        myCollision = false;
        myGamePanel.getMyCollision().checkTile(this);
        //Check Collision with Monsters
        int monster = myGamePanel.getMyCollision().checkEntity(this, myGamePanel.getMyMonsterArray());
        interactMonster(monster);
        //Check Collision with Four Pillars
        int pillar = myGamePanel.getMyCollision().checkPillar(this, myGamePanel.getMyPillarArray());

        if (myKeyInputs.up || myKeyInputs.down || myKeyInputs.left || myKeyInputs.right) {
            if (!myCollision) {
                switch (myDirection) {
                    case "up" -> myWorldYCoordinate -= mySpeed;
                    case "down" -> myWorldYCoordinate += mySpeed;
                    case "left" -> myWorldXCoordinate -= mySpeed;
                    case "right" -> myWorldXCoordinate += mySpeed;
                }
            }
        }
        mySpriteCounter++;
        if (mySpriteCounter > 12) {
            mySpriteNum = mySpriteNum == 1 ? 2 : 1;
            mySpriteCounter = 0;
        }
    }

    public void draw(final Graphics2D theGraphics) {
        BufferedImage image = null;

        switch (myDirection) {
            case "up":
                if (mySpriteNum == 1) {
                    image = up1;
                } else {
                    image = up2;
                }
                break;
            case "down":
                if (mySpriteNum == 1) {
                    image = down1;
                } else {
                    image = down2;
                }
                break;
            case "left":
                if (mySpriteNum == 1) {
                    image = left1;
                } else {
                    image = left2;
                }
                break;
            case "right":
                if (mySpriteNum == 1) {
                    image = right1;
                } else {
                    image = right2;
                }
                break;
        }
        // Draw my hero in the middle of the viewable screen.
        theGraphics.drawImage(image, myScreensMiddleX, myScreensMiddleY, myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);

    }

    public abstract void getHeroesImage();


    /**
     * Abstract method in which we implement so that the hero classes
     * do a normal attack.
     *
     * @param theOpp opponent in which the attack will be targeted towards.
     */
    public abstract String regularAttack(DungeonCharacter theOpp);

    /**
     * The special skill which each class will have unique to be their own
     * whether they do a special attack or skill is dependent on the class.
     *
     * @param theTarget which the special skill will be aimed towards.
     */
    public abstract String specialSkill(DungeonCharacter theTarget);

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
