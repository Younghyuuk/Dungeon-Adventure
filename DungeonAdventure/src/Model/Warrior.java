package Model;

import Control.Keyboard;
import View.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

/**
 * Warrior class that extends Heroes.
 */
public class Warrior extends Heroes {

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
    private static final int MAX_DAMAGE = 60;

    /**
     * Hit chance of warrior.
     */
    private static final double HIT_CHANCE = 0.8;

    /**
     * Block chance of warrior.
     */
    private static final double BLOCK_CHANCE = 0.2;

    /**
     * Warrior constructor that sets all the stats for
     * the character along with some background gui.
     *
     * @param theGamePanel game panel of character
     * @param theKeyboard  keyboard input of character
     */
    public Warrior(final GamePanel theGamePanel, final Keyboard theKeyboard) {
        super(HP, NAME, ATTACK_SPEED,
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
                    .append(damage).append(" damage! \n");
        } else {
            special.append("Crushing blow failed \n");

        }
        return special.toString();
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
