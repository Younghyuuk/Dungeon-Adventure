package Model;

import Control.Keyboard;
import View.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

/**
 * Thief class which extends from Heroes.
 */
public class Thief extends Heroes{

    /**
     * The chance at which the surprise attack succeeds.
     */
    private static final double SURPRISE_ATTACK_CHANCE = 0.4;

    /**
     * The chance at which it fails and thief gets caught.
     */
    private static final double SURPRISE_FAIL_CHANCE = 0.2;

    /**
     * Hp of thief.
     */
    private static final int HP = 75;

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
    private static final int MAX_DAMAGE = 40;

    /**
     * Hit chance of thief.
     */
    private static final double HIT_CHANCE = 0.8;

    /**
     * Block chance of thief.
     */
    private static final double BLOCK_CHANCE = 0.4;


    /**
     * Thief constructor which sets up all the stats for the character including
     * some background gui.
     *
     * @param theGamePanel gamepanel of character
     * @param theKeyboard keyboard input of character
     */
    public Thief(GamePanel theGamePanel, Keyboard theKeyboard) {
        super(HP, NAME, ATTACK_SPEED, MIN_DAMAGE, MAX_DAMAGE, HIT_CHANCE, BLOCK_CHANCE, theGamePanel,theKeyboard);
        getHeroesImage();
    }

    /**
     * The normal attack of thief with his damage stats.
     *
     * @param theOpp opponent in which the attack will be targeted towards.
     */
    @Override
    public void regularAttack(DungeonCharacter theOpp) {
        attackBehavior(theOpp);
    }

    /**
     * The special skill of Thief which if succeeds attacks twice if he fails no attack.
     *
     * @param theOpp which the special skill will be aimed towards.
     */
    @Override
    public void specialSkill(DungeonCharacter theOpp) {

        double random = Math.random();

        System.out.println(getChName() + " launches special skill surprise attack!");
        if (random <= SURPRISE_ATTACK_CHANCE) {
            if (random <= SURPRISE_FAIL_CHANCE) {
                System.out.println("Oh no! " + getChName() + " got caught in the surprise attack and misses the attack.");
            } else {
                System.out.println(getChName() + " successfully performs the surprise attack!");

                // Perform the extra attack
                System.out.println(getChName() + " launches the first attack");
                attackBehavior(theOpp);

                // Perform the second attack
                System.out.println(getChName() + " strikes again for the second attack");
                attackBehavior(theOpp);
            }
        } else {
            System.out.println(getChName() + " fails to execute the surprise attack.");
        }
    }
    @Override
    public void getHeroesImage(){
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
