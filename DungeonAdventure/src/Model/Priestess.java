package Model;

public class Priestess extends Heroes{

    private static final int MIN_HEAL = 25;
    private static final int MAX_HEAL = 55;
    protected Priestess() {
        super(75, "Priestess", 5, 25, 45, 0.7, 0.3);
    }

    @Override
    public void regularAttack(DungeonCharacter theOpp) {
        attackBehavior(theOpp);
    }

    @Override
    public void specialSkill(DungeonCharacter opponent) {
        
    }
}
