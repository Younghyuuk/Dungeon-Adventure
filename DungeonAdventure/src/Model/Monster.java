package Model;


import View.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Monster abstract class that extends DungeonCharacter
 */
public abstract class Monster extends DungeonCharacter {
//    private final UUID myId;

    private final double myChanceHeal;
    private final int myMinHeal;
    private final int myMaxHeal;
    private final int myMaxHealth;

    public BufferedImage img1, img2;
    private int myXCords;
    private int myYCords;
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
    }
    public void setMyXCords(final int theXCords){
        myXCords = theXCords;
    }
    public void setMyYCords(final int theYCords){
        myYCords = theYCords;
    }

//    public UUID getId() {
//        return myId;
//    }

//    public void saveToDatabase() {
//        MonsterDataBase.getInstance().saveMonsterToDatabase(this);
//    }

    // Factory method to get a monster by its ID from the database
//    public static Monster getMonsterFromDatabase() {
//        return MonsterDataBase.getInstance().getMonsterFromDatabase();
//    }
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
    public void heal() {
        double random = Math.random();

        if (random <= getChanceHeal() && getHp() < myMaxHealth) {
            int healPoints = (int) Math.floor(Math.random() * (getMaxHeal() - getMinHeal()) + getMinHeal());
            setHp(getHp() + healPoints);
            System.out.println(getChName() + " heals itself for " + healPoints + " hit points.");
        }
    }
    public void draw(final Graphics2D theGraphics){

        int screenX = myXCords - myGamePanel.getMyHero().getMyWorldXCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleX();
        int screenY = myYCords - myGamePanel.getMyHero().getMyWorldYCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleY();

        // draw only the tiles that are width a certain screen size.
        if (myXCords + myGamePanel.getSpriteSize() > myGamePanel.getMyHero().getMyWorldXCoordinate() - myGamePanel.getMyHero().getMyScreensMiddleX() &&
                myXCords - myGamePanel.getSpriteSize() < myGamePanel.getMyHero().getMyWorldXCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleX() &&
                myYCords + myGamePanel.getSpriteSize() > myGamePanel.getMyHero().getMyWorldYCoordinate() - myGamePanel.getMyHero().getMyScreensMiddleY() &&
                myYCords - myGamePanel.getSpriteSize() < myGamePanel.getMyHero().getMyWorldYCoordinate() + myGamePanel.getMyHero().getMyScreensMiddleY()) {
            theGraphics.drawImage(img1, screenX, screenY, myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
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
