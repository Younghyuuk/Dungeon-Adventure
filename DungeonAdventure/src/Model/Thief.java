package Model;

public class Thief extends Heroes{
    private static final double SURPRISE_ATTACK_CHANCE = 0.4;
    private static final double SURPRISE_FAIL_CHANCE = 0.2;

    protected Thief() {
        super(75, "Thief", 6, 20, 40, 0.8, 0.4);
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
