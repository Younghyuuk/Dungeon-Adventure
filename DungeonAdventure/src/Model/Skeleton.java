package Model;

import View.GamePanel;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

/**
 * Skeleton class that extends monster one of the mobs
 * for the game.
 *
 * @author Halim Lee, Marrok Young, Andrew Chon.
 * @version July 2023.
 */
public class Skeleton extends Monster {

    /**
     * The constructor of DungeonCharacter that initializes the hp, name, attack speed,
     * minimum damage, maximum damage, and the hitchance of the character.
     *
     */
    protected Skeleton(int theHp, int theAttackSpeed, int theMinDamage, int theMaxDamage, double theHitChance,
                       double theChanceHeal, int theMinHeal, int theMaxHeal, GamePanel theGamePanel) {
        super(theHp, "Skeleton", theAttackSpeed, theMinDamage, theMaxDamage, theHitChance, theChanceHeal, theMinHeal, theMaxHeal,theGamePanel);
        getMonsterImage();
    }

    /**
     * Skeletons normal attack.
     *
     * @param theOpp which the monster will target
     */
    @Override
    public String regularAttack(DungeonCharacter theOpp) {
        return attackBehavior(theOpp);
    }

    /**
     * Returns the monsters image.
     */
    @Override
    public void getMonsterImage() {
        try {
            up1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele0.png")));
            up2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele1.png")));
            down1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele0.png")));
            down2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele1.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele0.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele1.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele0.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele1.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
