package Model;

public class Warrior extends Heroes{

    // tester to make sure all values match up delete later
    public static void main(String[] args) {
        Warrior warrior = new Warrior("Warrior");
        System.out.print(warrior.toString());
    }

    //======================================================

    private static final double CRUSHING_BLOW_CHANCE = 0.4; // Chance of a successful crushing blow
    private static final int CRUSHING_BLOW_MIN_DAMAGE = 75;
    private static final int CRUSHING_BLOW_MAX_DAMAGE = 175;

    protected Warrior(String theChName) {
        super(125, theChName, 4, 35, 60, 0.8, 0.2);
    }


    @Override
    public void regularAttack(DungeonCharacter theOpp) {
        attackBehavior(theOpp);
    }


    // Crushing Blow that does 75 to 175 points of damage but only has a 40% chance of succeeding
    @Override
    public void specialSkill(DungeonCharacter theOpp) {
        if (Math.random() <= CRUSHING_BLOW_CHANCE) {
            int damage = genDamage(CRUSHING_BLOW_MIN_DAMAGE, CRUSHING_BLOW_MAX_DAMAGE);
            theOpp.subtractHp(damage);
            System.out.println("Warrior delivers crushing blow to " + theOpp.getChName()+
                    " for " + damage + "damage.");
        } else {
            System.out.println("Crushing blow failed!");
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()); // Include properties from Hero and DungeonCharacter
        // Add warrior-specific properties if any
        return sb.toString();
    }


}
