package Model;

import Control.Keyboard;
import View.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

/**
 * Warrior class that extends Heroes.
 */
public class Warrior extends Heroes {

    private static final long serialversionUID = 123456799L;
    /**
     * The chance at which Warriors special hits.
     */
    private static final double CRUSHING_BLOW_CHANCE = 0.4;

    /**
     * The minimum damage of the crushing blow.
     */
    private static final int CRUSHING_BLOW_MIN_DAMAGE = 75;

    /**
     * The maximum damage of crushing blow.
     */
    private static final int CRUSHING_BLOW_MAX_DAMAGE = 175;

    /**
     * Hp of Warrior.
     */
    private static final int HP = 150;

    /**
     * The name of the character.
     */
    private static final String NAME = "Warrior";

    /**
     * Setting this characters attack speed.
     */
    private static final int ATTACK_SPEED = 4;

    /**
     * Minimum damage of warrior.
     */
    private static final int MIN_DAMAGE = 35;

    /**
     * Maximum damage of warrior.
     */
    private static final int MAX_DAMAGE = 65;

    /**
     * Hit chance of warrior.
     */
    private static final double HIT_CHANCE = 0.8;

    /**
     * Block chance of warrior.
     */
    private static final double BLOCK_CHANCE = 0.5;

    /**
     * Warrior constructor that sets all the stats for
     * the character along with some background gui.
     *
     * @param theGamePanel game panel of character
     * @param theKeyboard  keyboard input of character
     */
    public Warrior(final GamePanel theGamePanel, final Keyboard theKeyboard) {
        super(150, NAME, ATTACK_SPEED,
                MIN_DAMAGE, MAX_DAMAGE,
                HIT_CHANCE, BLOCK_CHANCE, theGamePanel, theKeyboard);
        getHeroesImage();
    }

    /**
     * Regular attack of warrior with his stats.
     *
     * @param theOpp opponent in which the attack will be targeted towards.
     */
    @Override
    public String regularAttack(final DungeonCharacter theOpp) {

        return attackBehavior(theOpp);
    }


    /**
     * Crushing blow method that does 75 to 175 points of damage
     * but has a 40% chance of hitting.
     *
     * @param theOpp which the special skill will be aimed towards.
     */
    @Override
    public String specialSkill(final DungeonCharacter theOpp) {
        StringBuilder special = new StringBuilder();
        if (Math.random() <= CRUSHING_BLOW_CHANCE) {
            int damage = genDamage(CRUSHING_BLOW_MIN_DAMAGE, CRUSHING_BLOW_MAX_DAMAGE);
            theOpp.subtractHp(damage);
            special.append("Warrior delivers CRUSHING BLOW to ").append(theOpp.getChName()).append(" for ")
                    .append(damage).append("! \n");
        } else {
            special.append("Crushing blow failed! \n");

        }
        return special.toString();
    }

    @Override
    public void drawHpBar(Graphics2D theGraphics) {
        int hp = this.getHp();
        if (hp >= 150) {
            theGraphics.drawImage(hp0, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 140) {
            theGraphics.drawImage(hp1, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 130) {
            theGraphics.drawImage(hp2, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 120) {
            theGraphics.drawImage(hp3, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 110) {
            theGraphics.drawImage(hp4, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 100) {
            theGraphics.drawImage(hp5, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 90) {
            theGraphics.drawImage(hp6, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 80) {
            theGraphics.drawImage(hp7, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 70) {
            theGraphics.drawImage(hp8, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 60) {
            theGraphics.drawImage(hp9, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 50) {
            theGraphics.drawImage(hp10, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 40) {
            theGraphics.drawImage(hp11, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 30) {
            theGraphics.drawImage(hp12, getMyScreenMiddleX(), getMyScreensMiddleY() - myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), myGamePanel.getSpriteSize(), null);
        } else if (hp >= 20) {
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
            up1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_up1.png")));
            up2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_up2.png")));
            down1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_down1.png")));
            down2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_down2.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_left1.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_left2.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_right1.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/warrior/warrior_right2.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
