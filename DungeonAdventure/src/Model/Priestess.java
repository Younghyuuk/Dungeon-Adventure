package Model;

import Control.Keyboard;
import View.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

/**
 * Priestess class which has the ability to heal and
 * extends from the Heroes class.
 */
public class Priestess extends Heroes {
    private static final long serialversionUID = 1234567L;


    /**
     * Min heal that priestess can heal for.
     */
    private static final int MIN_HEAL = 45;

    /**
     * the max amount priestess can heal for.
     */
    private static final int MAX_HEAL = 65;

    /**
     * Hp of priestess.
     */
    private static final int HP = 100;

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
    private static final int MIN_DAMAGE = 30;

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
    public void drawHpBar(Graphics2D theGraphics) {
        int hp = this.getHp();

        if (hp >= 100) {
            theGraphics.drawImage(hp0, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 95) {
            theGraphics.drawImage(hp1, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 85) {
            theGraphics.drawImage(hp2, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 65) {
            theGraphics.drawImage(hp3, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 60) {
            theGraphics.drawImage(hp4, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 55) {
            theGraphics.drawImage(hp5, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 50) {
            theGraphics.drawImage(hp6, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 45) {
            theGraphics.drawImage(hp7, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 40) {
            theGraphics.drawImage(hp8, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 35) {
            theGraphics.drawImage(hp9, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 30) {
            theGraphics.drawImage(hp10, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 25) {
            theGraphics.drawImage(hp11, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 20) {
            theGraphics.drawImage(hp12, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 15) {
            theGraphics.drawImage(hp13, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 10) {
            theGraphics.drawImage(hp14, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 5) {
            theGraphics.drawImage(hp15, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else {
            theGraphics.drawImage(hp16, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        }
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
    public String regularAttack(final DungeonCharacter theOpp) {
        return attackBehavior(theOpp);
    }

    /**
     * Priestess special which is a healing ability to herself or allies.
     *
     * @param theAlly which the special skill will be aimed towards.
     */
    @Override
    public String specialSkill(final DungeonCharacter theAlly) {
        // set hp of Priestess and not the monster
        StringBuilder special = new StringBuilder();
        if (getHp() < 200) {
            int healPoints = genHeal();
            setHp(getHp() + healPoints);
            special.append(getChName()).append(" heals itself for ").append(healPoints).append("! \n");
        }
        return special.toString();
    }

    /**
     * Helper method to generate heal of the priestess
     * from her min and max heal using Math.random().
     *
     * @return range of heal from min to max
     */
    private int genHeal() {
        return (int) Math.floor(Math.random() * (MAX_HEAL - MIN_HEAL) +MIN_HEAL);
    }

}
