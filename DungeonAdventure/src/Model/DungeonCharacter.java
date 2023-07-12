package Model;

public abstract class DungeonCharacter {
    private int myHp;
    private String myChName;
    private int myAttackSpeed;
    private int myMinDamage;
    private int myMaxDamage;
    private double myHitChance;

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
    public int getHp() {
        return myHp;
    }
    public String getChName() {
        return myChName;
    }

    public int getMinDamage() {
        return myMinDamage;
    }

    public int getMaxDamage() {
        return myMaxDamage;
    }

    public int getAttackSpeed() {
        return myAttackSpeed;
    }

    public double getHitChance() {
        return myHitChance;
    }

    public void setHp(int theHp) {
        myHp = theHp;
    }

    public void attackBehavior(DungeonCharacter theMonster) {
        if (Math.random() <= myHitChance) {

        }
    }
    public void subtractHp(int theDamage) {
        myHp -= theDamage;
    }

    public int genDamage() {
        return (int)(Math.random() * (myMaxDamage - myMinDamage + 1)) + myMinDamage;
    }
}
