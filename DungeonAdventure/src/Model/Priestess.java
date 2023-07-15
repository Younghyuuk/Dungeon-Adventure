package Model;

import View.GamePanel;
import Control.Keyboard;

import java.awt.*;

/**
 * Priestess class which has the ability to heal and
 * extends from the Heroes class.
 */
public class Priestess extends Heroes{

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
     * @param theKeyboard keyboard input of character
     */
    public Priestess(GamePanel theGamePanel, Keyboard theKeyboard) {
        super(HP, NAME, ATTACK_SPEED, MIN_DAMAGE, MAX_DAMAGE, HIT_CHANCE, BLOCK_CHANCE, theGamePanel,theKeyboard);
    }
    @Override
    public void getHeroesImage() {

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
    public void regularAttack(DungeonCharacter theOpp) {
        attackBehavior(theOpp);
    }

    /**
     * Priestess special which is a healing ability to herself or allies.
     *
     * @param theAlly which the special skill will be aimed towards.
     */
    @Override
    public void specialSkill(DungeonCharacter theAlly) {
        theAlly.setHp(getHp() + genHeal());
    }

    /**
     * Helper method to generate heal of the priestess
     * from her min and max heal using Math.random().
     *
     * @return range of heal from min to max
     */
    private int genHeal() {
        return (int) (Math.random() * ((MIN_HEAL - MAX_HEAL + 1))) + MIN_HEAL;
    }

}
