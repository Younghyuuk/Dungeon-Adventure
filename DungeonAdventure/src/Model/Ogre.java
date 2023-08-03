package Model;

import View.GamePanel;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class Ogre extends Monster {
//    /**
//     * Min heal that Ogre can heal for.
//     */
//    private static final int MIN_HEAL = 30;
//
//    /**
//     * the max amount ogre can heal for.
//     */
//    private static final int MAX_HEAL = 60;
//
//    /**
//     * Chance at which ogre has to heal.
//     */
//    private static final double CHANCE_HEAL = 0.1;
//
//    /**
//     * Hp of ogre.
//     */
//    private static final int HP = 200;
//
//    /**
//     * Setting Ogre name.
//     */
//    private static final String NAME = "Ogre";
//
//    /**
//     * Setting this characters attack speed to 2.
//     */
//    private static final int ATTACK_SPEED = 2;
//
//    /**
//     * Minimum damage of Ogre.
//     */
//    private static final int MIN_DAMAGE = 30;
//
//    /**
//     * Maximum damage of Ogre.
//     */
//    private static final int MAX_DAMAGE = 60;
//
//    /**
//     * Hit chance of Ogre.
//     */
//    private static final double HIT_CHANCE = 0.6;


    /**
     * The constructor of Monster that sets up the stats for Ogre class.
     */
    protected Ogre(int theHp, int theAttackSpeed, int theMinDamage, int theMaxDamage, double theHitChance,
                   double theChanceHeal, int theMinHeal, int theMaxHeal, GamePanel theGamePanel) {
        super(theHp, "Ogre", theAttackSpeed, theMinDamage, theMaxDamage, theHitChance, theChanceHeal,
                theMinHeal, theMaxHeal, theGamePanel);
        getMonsterImage();
    }

    /**
     * Ogres attack with his specific damage stats.
     *
     * @param theOpp which the monster will target
     */
    @Override
    public void regularAttack(final DungeonCharacter theOpp) {
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
//     * potential heal of ogre.
//     */
//    @Override
//    public void heal() {
//        double random = Math.random();
//        if (random <= CHANCE_HEAL) {
//            int healPoints = (int) Math.floor(Math.random() * (MAX_HEAL - MIN_HEAL) + MIN_HEAL);
//            setHp(getHp() + healPoints);
//            System.out.println(getChName() + " heals itself for " + healPoints + " hit points.");
//        }
//    }
}
