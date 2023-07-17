package Model;

public class Gremlin extends Monster {
    /**
     * Min heal that Gremlin can heal for.
     */
    private static final int MIN_HEAL = 20;

    /**
     * the max amount gremlin can heal for.
     */
    private static final int MAX_HEAL = 40;

    /**
     * Chance at which gremlin has to heal.
     */
    private static final double CHANCE_HEAL = 0.4;

    /**
     * Hp of gremlin.
     */
    private static final int HP = 70;

    /**
     * Setting gremlin name.
     */
    private static final String NAME = "Gremlin";

    /**
     * Setting this characters attack speed to 5.
     */
    private static final int ATTACK_SPEED = 5;

    /**
     * Minimum damage of Gremlin.
     */
    private static final int MIN_DAMAGE = 15;

    /**
     * Maximum damage of Gremlin.
     */
    private static final int MAX_DAMAGE = 30;

    /**
     * Hit chance of Gremlin.
     */
    private static final double HIT_CHANCE = 0.8;

    /**
     * The constructor of Monster that initializes the hp, name, attack speed,
     * minimum damage, maximum damage, and the hitchance of the character.
     */
    protected Gremlin() {
        super(HP, NAME, ATTACK_SPEED, MIN_DAMAGE, MAX_DAMAGE, HIT_CHANCE);
    }

    /**
     * Attack behavior of the gremlin.
     *
     * @param theOpp which the monster will target
     */
    @Override
    public void regularAttack(DungeonCharacter theOpp) {
        attackBehavior(theOpp);
    }

    /**
     * The heal method specific to the gremlin with his heal stats.
     */
    @Override
    public void heal() {
        double random = Math.random();
        if (random <= CHANCE_HEAL) {
            int healPoints = (int) (Math.random() * (MAX_HEAL - MIN_HEAL + 1)) + MIN_HEAL;
            setHp(getHp() + healPoints);
            System.out.println(getChName() + " heals itself for " + healPoints + " hit points.");
        }
    }
}