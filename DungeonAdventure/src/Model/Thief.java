package Model;

public class Thief extends Heroes{

    protected Thief(int theHp, String theChName, int theAttackSpeed, int theMinDamage, int theMaxDamage, double theHitChance, double theBlockChance) {
        super(theHp, theChName, theAttackSpeed, theMinDamage, theMaxDamage, theHitChance, theBlockChance);
    }

    @Override
    public void regularAttack(DungeonCharacter theOpp) {

    }

    @Override
    public void specialSkill(DungeonCharacter theOpp) {

    }
}
