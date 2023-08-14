package Model;


import View.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Random;

/**
 * Monster abstract class that extends DungeonCharacter
 */
public abstract class Monster extends DungeonCharacter {

//    private final UUID myId;
    private final double myChanceHeal;
    private final int myMinHeal;
    private final int myMaxHeal;
    private final int myMaxHealth;
    private int actionLockCounter = 0;

    /**
     * The constructor of DungeonCharacter that initializes the hp, name, attack speed,
     * minimum damage, maximum damage, and the hitchance of the character.
     *
     * @param theHp          amount of hp for character.
     * @param theChName      name of the character.
     * @param theAttackSpeed the attack speed character has.
     * @param theMinDamage   minimum amount of damage they can do.
     * @param theMaxDamage   maximum amount of damage they can do.
     * @param theHitChance   chance the character has to landing attack.
     */
    protected Monster(final int theHp, final String theChName, final int theAttackSpeed,
                      final int theMinDamage, final int theMaxDamage, final double theHitChance,
                      final double theChanceHeal, final int theMinHeal, final int theMaxHeal, GamePanel theGamePanel) {
        super(theHp, theChName, theAttackSpeed, theMinDamage, theMaxDamage, theHitChance, theGamePanel);
//        myId = theId;
        myChanceHeal = theChanceHeal;
        myMinHeal = theMinHeal;
        myMaxHeal = theMaxHeal;
        myMaxHealth = theHp;
        mySolidArea = new Rectangle(0, 0, myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize());
        mySpeed = 2;
    }

    public Rectangle getMySolidArea() {
        return mySolidArea;
    }
    public void resetSolidArea(){
        mySolidArea.x = 0;
        mySolidArea.y = 0;
    }

    /**
     * Monster attack.
     *
     * @param theOpp which the monster will target
     */
    public abstract String regularAttack(final DungeonCharacter theOpp);

    /**
     * Abstract method for monster child classes to heal.
     */
//    public abstract void heal();
    // new tester heal for all monster since all stats are from sqlite database
    public String heal() {
        double random = Math.random();
        StringBuilder healLog = new StringBuilder();
        if (random <= getChanceHeal() && getHp() < myMaxHealth) {
            int healPoints = (int) Math.floor(Math.random() * (getMaxHeal() - getMinHeal()) + getMinHeal());
            setHp(getHp() + healPoints);
            healLog.append(getChName()).append(" heals itself for ").append(healPoints).
                    append(" hit points! \n");
            healLog.append(getChName()).append(" new hp is ").append(getHp()).append(". \n");
        }
        return healLog.toString();
    }
    public void update() {
        setAction();
        myCollision = false;
        myGamePanel.getMyCollision().checkTile(this);
//        myGamePanel.getMyCollision().monsterToPlayer(this);
        if (!myCollision) {
            switch (myDirection) {
                case "up" -> myWorldYCoordinate -= mySpeed;
                case "down" -> myWorldYCoordinate += mySpeed;
                case "left" -> myWorldXCoordinate -= mySpeed;
                case "right" -> myWorldXCoordinate += mySpeed;
            }
        }
        mySpriteCounter++;
        if (mySpriteCounter > 12) {
            mySpriteNum = mySpriteNum == 1 ? 2 : 1;
            mySpriteCounter = 0;
        }
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 30) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                if (!myDirection.equals("up")) {
                    myDirection = "up";
                }

            }
            if (i > 25 && i <= 50) {
                if (!myDirection.equals("down")) {
                    myDirection = "down";
                }
            }
            if (i > 50 && i <= 75) {

                if (!myDirection.equals("right")) {
                    myDirection = "right";
                }
            }
            if (i > 75) {
                if (!myDirection.equals("left")) {
                    myDirection = "left";
                }
            }
            actionLockCounter = 0;
        }
    }
    public void draw(final Graphics2D theGraphics) {

        int screenX = myWorldXCoordinate - myGamePanel.getMyHero().getMyWorldXCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleX();
        int screenY = myWorldYCoordinate - myGamePanel.getMyHero().getMyWorldYCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleY();

        if (myWorldXCoordinate + myGamePanel.getSpriteSize() > myGamePanel.getMyHero().getMyWorldXCoordinate() - myGamePanel.getMyHero().getMyScreensMiddleX() &&
                myWorldXCoordinate - myGamePanel.getSpriteSize() < myGamePanel.getMyHero().getMyWorldXCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleX() &&
                myWorldYCoordinate + myGamePanel.getSpriteSize() > myGamePanel.getMyHero().getMyWorldYCoordinate() - myGamePanel.getMyHero().getMyScreensMiddleY() &&
                myWorldYCoordinate - myGamePanel.getSpriteSize() < myGamePanel.getMyHero().getMyWorldYCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleY()) {
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
            theGraphics.drawImage(image, screenX, screenY, myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        }
    }

    public abstract void getMonsterImage();

    public double getChanceHeal() {
        return myChanceHeal;
    }

    public int getMinHeal() {
        return myMinHeal;
    }

    public int getMaxHeal() {
        return myMaxHeal;
    }
}
