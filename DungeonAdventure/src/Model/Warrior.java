package Model;

public class Warrior extends Heroes{
    protected Warrior(int theHp, String theChName, int theAttackSpeed, int theMinDamage, int theMaxDamage, double theHitChance, double theBlockChance) {
        super(theHp, theChName, theAttackSpeed, theMinDamage, theMaxDamage, theHitChance, theBlockChance);
    }

    @Override
    public void regularAttack(DungeonCharacter opponent) {

    }

    @Override
    public void specialSkill(DungeonCharacter opponent) {

    }


}
