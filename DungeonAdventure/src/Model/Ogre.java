package Model;

import java.util.UUID;

public class Ogre extends Monster {


    /**
     * The constructor of Monster that sets up the stats for Ogre class.
     */
    protected Ogre(int theHp, int theAttackSpeed, int theMinDamage, int theMaxDamage, double theHitChance,
                      double theChanceHeal, int theMinHeal, int theMaxHeal) {
        super(theHp, "Ogre", theAttackSpeed, theMinDamage, theMaxDamage, theHitChance, theChanceHeal,
                theMinHeal, theMaxHeal);
    }

    /**
     * Ogres attack with his specific damage stats.
     *
     * @param theOpp which the monster will target
     */
    @Override
    public void regularAttack(final DungeonCharacter theOpp) {
        attackBehavior(theOpp);
    }


}
