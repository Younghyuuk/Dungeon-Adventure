package Model;

import java.util.UUID;

public class Gremlin extends Monster {



    /**
     * The constructor of Monster that initializes the hp, name, attack speed,
     * minimum damage, maximum damage, and the hitchance of the character.
     */

    //Testing out a new Sqlite database implemented way
    protected Gremlin(int theHp, int theAttackSpeed, int theMinDamage, int theMaxDamage, double theHitChance,
                      double theChanceHeal, int theMinHeal, int theMaxHeal) {
        super(theHp, "Gremlin", theAttackSpeed, theMinDamage, theMaxDamage, theHitChance, theChanceHeal, theMinHeal, theMaxHeal);
    }

    /**
     * Attack behavior of the gremlin.
     *
     * @param theOpp which the monster will target
     */
    @Override
    public void regularAttack(final DungeonCharacter theOpp) {
        attackBehavior(theOpp);
    }


}
