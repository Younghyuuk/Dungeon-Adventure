package Model;

/**
 * Skeleton class that extends monster one of the mobs
 * for the game.
 */
public class Skeleton extends Monster{

    /**
     * Min heal that skeleton can heal for.
     */
    private static final int MIN_HEAL = 30;

    /**
     * the max amount skeleton can heal for.
     */
    private static final int MAX_HEAL = 50;

    /**
     * Chance at which skeleton has to heal.
     */
    private static final double CHANCE_HEAL = 0.3;

    /**
     * Hp of skeleton.
     */
    private static final int HP = 100;

    /**
     * Setting Skeleton name.
     */
    private static final String NAME = "Skeleton";

    /**
     * Setting this characters attack speed to 3.
     */
    private static final int ATTACK_SPEED = 3;

    /**
     * Minimum damage of Skeleton.
     */
    private static final int MIN_DAMAGE = 30;

    /**
     * Maximum damage of Skeleton.
     */
    private static final int MAX_DAMAGE = 50;

    /**
     * Hit chance of Skeleton.
     */
    private static final double HIT_CHANCE = 0.8;

    /**
     * The constructor of DungeonCharacter that initializes the hp, name, attack speed,
     * minimum damage, maximum damage, and the hitchance of the character.
     *
     */
    public Skeleton() {
        super(HP, NAME, ATTACK_SPEED, MIN_DAMAGE, MAX_DAMAGE, HIT_CHANCE);

    }

    /**
     * Skeletons normal attack.
     *
     * @param theOpp which the monster will target
     */
    @Override
    public void regularAttack(DungeonCharacter theOpp) {
        attackBehavior(theOpp);
    }

    /**
     * Heal potential for skeleton.
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
