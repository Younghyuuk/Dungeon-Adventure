package Model;

import View.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.io.Serializable;

/**
 * Creates the abstract class DungeonCharacter which other
 * classes extends from like Hero and Monster.
 *
 * @author Halim Lee, Marrok Young, Andrew Chon.
 * @version July 2023.
 */

public abstract class DungeonCharacter implements Serializable {
    /**
     * The serial ID associated with this 'GameData' object.
     */
    @Serial
    private static final long serialVersionUID = 123456789L;

//    private static final long serialversionUID = 12345L;

    /**
     * The int myHp that tracks the character health points.
     */
    private int myHp;
    /**
     * The hp the character initially starts with.
     */
    private final transient int myDefaultHp;
    /**
     * The string name of the character created.
     */
    private String myChName;
    /**
     * The attack speed of the character.
     */
    private int myAttackSpeed;
    /**
     * The minimum amount of damage a character can do to an enemy.
     */
    private int myMinDamage;
    /**
     * The max amount of damage a character can do to an enemy.
     */
    private int myMaxDamage;
    /**
     * The chance that the character has to hitting its target a double.
     */
    private double myHitChance;
    /**
     * The main game panel.
     */
    public transient GamePanel myGamePanel;
    /**
     * The characters direction images.
     */
    public transient BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    /**
     * A String that dictates the characters directions.
     */
    public transient String myDirection = "down";
    /**
     * The speed of the character.
     */
    public transient int mySpeed;
    /**
     * The characters collision boolean.
     */
    public boolean myCollision = false;
    /**
     * A rectangle that dictates the characters collision area.
     */
    public Rectangle mySolidArea;
    /**
     * A counter for sprite animation.
     */
    public transient int mySpriteCounter = 0;
    /**
     * A number for sprite animation logic.
     */
    public transient int mySpriteNum = 1;
    /**
     * The characters x coordinate.
     */
    public int myWorldXCoordinate;
    /**
     * The characters y coordinate.
     */
    public int myWorldYCoordinate;

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
    protected DungeonCharacter(int theHp, String theChName, int theAttackSpeed,
                               int theMinDamage, int theMaxDamage,
                               double theHitChance, GamePanel theGamePanel) {
        myHp = theHp;
        myChName = theChName;
        myAttackSpeed = theAttackSpeed;
        myMinDamage = theMinDamage;
        myMaxDamage = theMaxDamage;
        myHitChance = theHitChance;
        myGamePanel = theGamePanel;
        myDefaultHp = theHp;
    }
public void setGamePanel(GamePanel theGamePanel){
        myGamePanel = theGamePanel;
}
    /**
     * Sets the collision state of the character.
     * @param theBool the collision state.
     */
    public void setMyCollision(final boolean theBool) {
        myCollision = theBool;
    }

    /**
     * Returns the character's direction.
     * @return a String that indicates the direction.
     */
    public String getMyDirection() {
        return myDirection;
    }

    /**
     * Returns the collision area.
     * @return a rectangle.
     */
    public Rectangle getMySolidArea() {
        return mySolidArea;
    }

    /**
     * Returns the player's speed.
     * @return a integer representing the players speed.
     */
    public int getMySpeed(){
        return mySpeed;
    }

    public void setMySpeed(final int theSpeed) {
        mySpeed = theSpeed;
    }

    /**
     * Returns the character's X coordinate.
     * @return the x coordinate.
     */
    public int getMyWorldXCoordinate() {
        return myWorldXCoordinate;
    }

    /**
     * Returns the character's y coordinate.
     * @return the y coordinate.
     */
    public int getMyWorldYCoordinate() {
        return myWorldYCoordinate;
    }

    /**
     * Sets the characters x coordinate.
     * @param theX the wanted x coordinate.
     */
    public void setMyWorldXCoordinate(int theX) {
        myWorldXCoordinate = theX;
    }

    /**
     * Sets the character's y coordinate.
     * @param theY the wanted y coordinate.
     */
    public void setMyWorldYCoordinate(int theY) {
        myWorldYCoordinate = theY;
    }

    /**
     * Gets the hp that the character has currently.
     *
     * @return the int hp of the character.
     */
    public int getHp() {
        return myHp;
    }

    /**
     * Sets the new hp of the character.
     *
     * @param theHp new health that the character should acquire.
     */
    public void setHp(int theHp) {
        myHp = theHp;
    }

    /**
     * Gets the name of the character.
     *
     * @return String name of character
     */
    public String getChName() {
        return myChName;
    }

    public void setChName(String theChName) {
        myChName = theChName;
    }

    /**
     * Gets the minimum amount of damage.
     *
     * @return int minimum damage
     */
    public int getMinDamage() {
        return myMinDamage;
    }

    public void setMinDamage(int theMinDamage) {
        myMinDamage = theMinDamage;
    }
    /**
     * Gets the max amount of damage.
     *
     * @return int max damage
     */
    public int getMaxDamage() {
        return myMaxDamage;
    }


    public void setMaxDamage(int theMaxDamage) {
        myMaxDamage = theMaxDamage;
    }
    /**
     * Gets the attack speed.
     *
     * @return int attack speed
     */
    public int getAttackSpeed() {
        return myAttackSpeed;
    }


    public void setAttackSpeed(int theAttackSpeed) {
        myAttackSpeed = theAttackSpeed;
    }
    /**
     * Gets the hit chance.
     *
     * @return double hit chance
     */
    public double getHitChance() {
        return myHitChance;
    }

    public void setHitChance(double theHitChance) {
        myHitChance = theHitChance;
    }
    /**
     * This method attacks an opponent based on the characters
     * hit chance and damage range and deals that damage to theOpp.
     *
     * @param theOpp the opponent in which we are attacking
     */
    public String attackBehavior(DungeonCharacter theOpp) {
        StringBuilder attack = new StringBuilder();
        if (Math.random() <= getHitChance()) {
            int damage = genDamage(getMinDamage(), getMaxDamage());
            theOpp.subtractHp(damage);
            attack.append(getChName()).append(" hits ").append(theOpp.getChName()).append(" for ")
                            .append(damage).append(" damage! \n");
            attack.append(theOpp.getChName()).append(" has ").append(theOpp.getHp()).
                    append(" hp remaining. \n");
//            System.out.println(getChName() + " hits " + theOpp.getChName() +
//                    " for " + damage + " damage.");
        } else {
            attack.append("Aw ").append(getChName()).
                    append(" misses horribly! \n");
//            System.out.println("Aw " + getChName() + " misses horribly!");
        }
        return attack.toString();
    }

    /**
     * Subtracts the hp of the character based off
     * of the damage dealt.
     *
     * @param theDamage the set amount to subtract off of hp.
     */
    public void subtractHp(int theDamage) {
        myHp -= theDamage;
    }

    /**
     * Gives us the range at which the Math.random chooses to attack
     * between the minimum and maximum damage.
     *
     * @param theMin minimum amount of damage that can be done.
     * @param theMax maximum amount of damage that can be done.
     * @return the value that damage will be done between min and max.
     */
    public int genDamage(int theMin, int theMax) {
        return (int) Math.floor(Math.random() * (theMax-theMin) + theMin);
    }

    /**
     * Checks whether or not the character is alive or not for both
     * Heroes and Monsters.
     *
     * @return Hp greater than 0
     */
    public boolean isAlive() {
        return getHp() > 0;
    }

    /**
     * Resets the hp of the character back to its default.
     */
    public void resetHP(){
        myHp = myDefaultHp;
    }

    /**
     * Tester toString method to see if all character values
     * show up right.
     *
     * @return StringBuilder sb
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(getChName()).append("\n");
        sb.append("Hit Points: ").append(getHp()).append("\n");
        sb.append("Min Damage: ").append(getMinDamage()).append("\n");
        sb.append("Max Damage: ").append(getMaxDamage()).append("\n");
        sb.append("Attack Speed: ").append(getAttackSpeed()).append("\n");
        sb.append("Chance to Hit: ").append(getHitChance()).append("\n");
        return sb.toString();
    }
}
