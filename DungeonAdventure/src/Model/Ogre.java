package Model;

import View.GamePanel;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

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


    @Override
    public void getMonsterImage() {
        try {
            img1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele0.png")));
            img2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/skeleton/skele1.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
