package Model;

/**
 * Creates the abstract class DungeonCharacter which other
 * classes extends from like Hero and Monster.
 *
 * @author Halim Lee, Marrok Young, Andrew Chon.
 * @version July 2023.
 */

public abstract class DungeonCharacter {
    /**
     * The int myHp that tracks the character health points.
     */
    private int myHp;

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
                               double theHitChance) {
        myHp = theHp;
        myChName = theChName;
        myAttackSpeed = theAttackSpeed;
        myMinDamage = theMinDamage;
        myMaxDamage = theMaxDamage;
        myHitChance = theHitChance;
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

    /**
     * Gets the minimum amount of damage.
     *
     * @return int minimum damage
     */
    public int getMinDamage() {
        return myMinDamage;
    }

    /**
     * Gets the max amount of damage.
     *
     * @return int max damage
     */
    public int getMaxDamage() {
        return myMaxDamage;
    }

    /**
     * Gets the attack speed.
     *
     * @return int attack speed
     */
    public int getAttackSpeed() {
        return myAttackSpeed;
    }

    /**
     * Gets the hit chance.
     *
     * @return double hit chance
     */
    public double getHitChance() {
        return myHitChance;
    }

    /**
     * This method attacks an opponent based on the characters
     * hit chance and damage range and deals that damage to theOpp.
     *
     * @param theOpp the opponent in which we are attacking
     */
    public void attackBehavior(DungeonCharacter theOpp) {
        if (Math.random() <= getHitChance()) {
            int damage = genDamage(getMinDamage(), getMaxDamage());
            theOpp.subtractHp(damage);
//            System.out.println(getChName() + " hits " + theOpp.getChName() +
//                    " for " + damage + " damage.");
        } else {
//            System.out.println("Aw " + getChName() + " misses horribly!");
        }
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

    public boolean isAlive() {
        return getHp() > 0;
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
