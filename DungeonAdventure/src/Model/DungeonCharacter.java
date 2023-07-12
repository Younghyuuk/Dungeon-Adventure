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
        if (Math.random() <= getHitChance()) {
            int damage = genDamage(getMinDamage(), getMaxDamage());
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

    public int genDamage(int theMin, int theMax) {
        return (int) (Math.random() * ((theMin - theMax + 1))) + getMinDamage();
    }

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
