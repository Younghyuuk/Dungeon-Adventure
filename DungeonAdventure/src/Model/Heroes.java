package Model;

public abstract class Heroes extends DungeonCharacter {

    private double myBlockChance;

    protected Heroes(int theHp, String theChName, int theAttackSpeed, int theMinDamage, int theMaxDamage, double theHitChance, double theBlockChance) {
        super(theHp, theChName, theAttackSpeed, theMinDamage, theMaxDamage, theHitChance);
        myBlockChance = theBlockChance;
    }

    public double getBlockChance() {
        return myBlockChance;
    }
    public void setBlockChance(double theBlockChance) {
        myBlockChance = theBlockChance;
    }
    public abstract void regularAttack(DungeonCharacter opponent);

    public abstract void specialSkill(DungeonCharacter opponent);



}
