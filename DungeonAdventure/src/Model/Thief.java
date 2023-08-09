package Model;

import Control.Keyboard;
import View.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

/**
 * Thief class which extends from Heroes.
 */
public class Thief extends Heroes {

    /**
     * The chance at which the surprise attack succeeds.
     */
    private static final double SURPRISE_ATTACK_CHANCE = 0.9;

    /**
     * The chance at which it fails and thief gets caught.
     */
    private static final double SURPRISE_FAIL_CHANCE = 0.1;

    /**
     * Hp of thief.
     */
    private static final int HP = 80;

    /**
     * The name of the character.
     */
    private static final String NAME = "Thief";

    /**
     * Setting this characters attack speed to 6.
     */
    private static final int ATTACK_SPEED = 6;

    /**
     * Minimum damage of thief.
     */
    private static final int MIN_DAMAGE = 20;

    /**
     * Maximum damage of thief.
     */
    private static final int MAX_DAMAGE = 50;

    /**
     * Hit chance of thief.
     */
    private static final double HIT_CHANCE = 0.8;

    /**
     * Block chance of thief.
     */
    private static final double BLOCK_CHANCE = 0.7;


    /**
     * Thief constructor which sets up all the stats for the character including
     * some background gui.
     *
     * @param theGamePanel gamepanel of character
     * @param theKeyboard  keyboard input of character
     */
    public Thief(final GamePanel theGamePanel, final Keyboard theKeyboard) {
        super(HP, NAME, ATTACK_SPEED, MIN_DAMAGE, MAX_DAMAGE, HIT_CHANCE, BLOCK_CHANCE, theGamePanel, theKeyboard);
        getHeroesImage();
    }

    /**
     * The normal attack of thief with his damage stats.
     *
     * @param theOpp opponent in which the attack will be targeted towards.
     */
    @Override
    public String regularAttack(final DungeonCharacter theOpp) {

        return attackBehavior(theOpp);
    }

    /**
     * The special skill of Thief which if succeeds attacks twice if he fails no attack.
     *
     * @param theOpp which the special skill will be aimed towards.
     */
    @Override
    public String specialSkill(final DungeonCharacter theOpp) {

        double random = Math.random();
        StringBuilder special = new StringBuilder();
        special.append("Thief launches special skill surprise attack! \n");
        if (random <= SURPRISE_ATTACK_CHANCE) {
            if (random <= SURPRISE_FAIL_CHANCE) {
                special.append("Oh no! Thief got caught in the surprise attack and misses! \n");
            } else {
                special.append("Thief hits the surprise attack! \n");


                // Perform the extra attack
                special.append("Thief launches the first attack. \n");
                int damage1 = genDamage(getMinDamage(), getMaxDamage());
                theOpp.setHp(damage1);
                special.append(theOpp.getChName()).append(" gets hit for ").append(damage1)
                        .append("! \n");

                // Perform the second attack
                special.append("Thief strikes again for the second attack \n");
                int damage2 = genDamage(getMinDamage(), getMaxDamage());
                theOpp.setHp(damage2);
                special.append(theOpp.getChName()).append(" gets hit for ").append(damage2)
                        .append("! \n");
            }
        } else {
            special.append("Thief fails the surprise attack! \n");

        }
        return special.toString();
    }

    @Override
    public void drawHpBar(Graphics2D theGraphics) {
        int hp = this.getHp();

        if (hp >= 80) {
            theGraphics.drawImage(hp0, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 75) {
            theGraphics.drawImage(hp1, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 70) {
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
            up1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_up1.png")));
            up2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_up2.png")));
            down1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_down1.png")));
            down2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_down2.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_left1.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_left2.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_right1.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_right2.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
