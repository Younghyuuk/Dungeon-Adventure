package Model;

import View.GamePanel;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

/**
 * Skeleton class that extends monster one of the mobs
 * for the game.
 */
public class Skeleton extends Monster{

//    /**
//     * Min heal that skeleton can heal for.
//     */
//    private static final int MIN_HEAL = 30;
//
//    /**
//     * the max amount skeleton can heal for.
//     */
//    private static final int MAX_HEAL = 50;
//
//    /**
//     * Chance at which skeleton has to heal.
//     */
//    private static final double CHANCE_HEAL = 0.3;
//
//    /**
//     * Hp of skeleton.
//     */
//    private static final int HP = 100;
//
//    /**
//     * Setting Skeleton name.
//     */
//    private static final String NAME = "Skeleton";
//
//    /**
//     * Setting this characters attack speed to 3.
//     */
//    private static final int ATTACK_SPEED = 3;
//
//    /**
//     * Minimum damage of Skeleton.
//     */
//    private static final int MIN_DAMAGE = 30;
//
//    /**
//     * Maximum damage of Skeleton.
//     */
//    private static final int MAX_DAMAGE = 50;
//
//    /**
//     * Hit chance of Skeleton.
//     */
//    private static final double HIT_CHANCE = 0.8;

    /**
     * The constructor of DungeonCharacter that initializes the hp, name, attack speed,
     * minimum damage, maximum damage, and the hitchance of the character.
     *
     */
    protected Skeleton(int theHp, int theAttackSpeed, int theMinDamage, int theMaxDamage, double theHitChance,
                       double theChanceHeal, int theMinHeal, int theMaxHeal, GamePanel theGamePanel) {
        super(theHp, "skeleton", theAttackSpeed, theMinDamage, theMaxDamage, theHitChance, theChanceHeal, theMinHeal, theMaxHeal,theGamePanel);
        getMonsterImage();
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

    @Override
    public void getMonsterImage() {
        try {
            img1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele0.png")));
            img2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele1.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




//    /**
//     * Heal potential for skeleton.
//     */
//    @Override
//    public void heal() {
//
//        double random = Math.random();
//        if (random <= CHANCE_HEAL) {
//            int healPoints = (int) Math.floor(Math.random() * (MAX_HEAL - MIN_HEAL) + MIN_HEAL);
//            setHp(getHp() + healPoints);
//            System.out.println(getChName() + " heals itself for " + healPoints + " hit points.");
//        }
//    }

}
