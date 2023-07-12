package Model;

public class Warrior extends Heroes{

    // this constructor still needs changes to hardcode in stats
    protected Warrior(int theHp, String theChName, int theRange, int theAttackSpeed, int theHitChance) {
        super(theHp, theChName, theRange, theAttackSpeed, theHitChance);
    }
}
