package Model;

public class Thief extends Heroes{
    private static final double SURPRISE_ATTACK_CHANCE = 0.4;
    private static final double SURPRISE_FAIL_CHANCE = 0.2;

    protected Thief(int theHp, String theChName, int theAttackSpeed, int theMinDamage, int theMaxDamage, double theHitChance, double theBlockChance) {
        super(theHp, theChName, theAttackSpeed, theMinDamage, theMaxDamage, theHitChance, theBlockChance);
    }

    @Override
    public void regularAttack(DungeonCharacter theOpp) {
        attackBehavior(theOpp);
    }

    // needs fixing
    @Override
    public void specialSkill(DungeonCharacter theOpp) {
        if (Math.random() <= SURPRISE_ATTACK_CHANCE) {
           if (Math.random() <= SURPRISE_FAIL_CHANCE) {

           } else {
               attackBehavior(theOpp);
               attackBehavior(theOpp);
           }
        }
    }
}
