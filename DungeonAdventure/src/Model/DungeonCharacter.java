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

    public void setHp(int theHp) {
        myHp = theHp;
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

    public void attackBehavior(DungeonCharacter theOpp) {
        if (Math.random() <= myHitChance) {
            int damage = genDamage();
            theOpp.subtractHp(damage);
            System.out.println(getChName() + " hits " + theOpp.getChName() +
                    " for " + damage + " damage.");
        } else {
            System.out.println("Aw " + getChName() + "misses horribly!");
        }
    }

    public void subtractHp(int theDamage) {
        myHp -= theDamage;
    }

    private int genDamage() {
        return (int) (Math.random() * (damageRange())) + myMinDamage;
    }

    private int damageRange() {
        return (myMaxDamage - myMinDamage + 1);
    }
}
