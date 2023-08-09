package Model;

import View.GamePanel;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class Gremlin extends Monster {



    /**
     * The constructor of Monster that initializes the hp, name, attack speed,
     * minimum damage, maximum damage, and the hitchance of the character.
     */

    //Testing out a new Sqlite database implemented way
    protected Gremlin(int theHp, int theAttackSpeed, int theMinDamage, int theMaxDamage, double theHitChance,
                      double theChanceHeal, int theMinHeal, int theMaxHeal, GamePanel theGamePanel) {
        super(theHp, "Gremlin", theAttackSpeed, theMinDamage, theMaxDamage, theHitChance, theChanceHeal, theMinHeal, theMaxHeal,theGamePanel);
        getMonsterImage();
    }

    /**
     * Attack behavior of the gremlin.
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
            up1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem0.png")));
            up2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem1.png")));
            down1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem0.png")));
            down2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem1.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem0.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem1.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem0.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/gremlin/grem1.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
