package Model;

import Control.Keyboard;
import View.GamePanel;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

/**
 * Priestess class which has the ability to heal and
 * extends from the Heroes class.
 */
public class Priestess extends Heroes {

    /**
     * Min heal that priestess can heal for.
     */
    private static final int MIN_HEAL = 25;

    /**
     * the max amount priestess can heal for.
     */
    private static final int MAX_HEAL = 55;

    /**
     * Hp of priestess 75.
     */
    private static final int HP = 75;

    /**
     * Setting Priestess name.
     */
    private static final String NAME = "Priestess";

    /**
     * Setting this characters attack speed to 5.
     */
    private static final int ATTACK_SPEED = 5;

    /**
     * Minimum damage of priestess.
     */
    private static final int MIN_DAMAGE = 25;

    /**
     * Maximum damage of priestess.
     */
    private static final int MAX_DAMAGE = 45;

    /**
     * Hit chance of priestess.
     */
    private static final double HIT_CHANCE = 0.7;

    /**
     * Block chance of priestess.
     */
    private static final double BLOCK_CHANCE = 0.3;

    /**
     * Priestess constructor which sets up all her stats
     * in hp, name, attack speed, damage, hit chance and block chance.
     * Also sets up background for the characters for the view.
     *
     * @param theGamePanel gamepanel of character
     * @param theKeyboard  keyboard input of character
     */
    public Priestess(final GamePanel theGamePanel, final Keyboard theKeyboard) {
        super(HP, NAME, ATTACK_SPEED, MIN_DAMAGE, MAX_DAMAGE, HIT_CHANCE, BLOCK_CHANCE, theGamePanel, theKeyboard);
        getHeroesImage();
    }

    @Override
    public void getHeroesImage() {
        try {
            up1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_up1.png")));
            up2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_up2.png")));
            down1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_down1.png")));
            down2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_down2.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_left1.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_left2.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_right1.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/priest/priest_right2.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getBlockChance() {
        return super.getBlockChance();
    }

    /**
     * Priestess normal attack which calculates her min and max
     * damage to get something in between.
     *
     * @param theOpp opponent in which the attack will be targeted towards.
     */
    @Override
    public void regularAttack(final DungeonCharacter theOpp) {
        attackBehavior(theOpp);
    }

    /**
     * Priestess special which is a healing ability to herself or allies.
     *
     * @param theAlly which the special skill will be aimed towards.
     */
    @Override
    public void specialSkill(final DungeonCharacter theAlly) {
        // set hp of Priestess and not the monster
        if (getHp() < 100) {
            setHp(getHp() + genHeal());
//            System.out.println(getChName() + " heals itself for " + genHeal() + " hit points.");
        }
    }

    /**
     * Helper method to generate heal of the priestess
     * from her min and max heal using Math.random().
     *
     * @return range of heal from min to max
     */
    private int genHeal() {
        return (int) Math.floor(Math.random() * (MAX_HEAL - MIN_HEAL) + MIN_HEAL);
    }

}
