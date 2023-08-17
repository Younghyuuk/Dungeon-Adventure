package Model;

import View.GamePanel;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

/**
 * An Ogre class that extends from Monster.
 *
 * @author Halim Lee, Marrok Young, Andrew Chon.
 * @version July 2023.
 */
public class Ogre extends Monster {

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
    public String regularAttack(final DungeonCharacter theOpp) {

        return attackBehavior(theOpp);
    }

    /**
     * Override method that returns the monsters image.
     */
    @Override
    public void getMonsterImage() {
        try {
            up1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre0.png")));
            up2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre1.png")));
            down1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre0.png")));
            down2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre1.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre0.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre1.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre0.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/ogre/ogre1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
