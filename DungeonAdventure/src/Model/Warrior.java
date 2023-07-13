package Model;

import Control.Keyboard;
import View.GamePanel;

import java.awt.*;

public class Warrior extends Heroes{

    private static final double CRUSHING_BLOW_CHANCE = 0.4; // Chance of a successful crushing blow
    private static final int CRUSHING_BLOW_MIN_DAMAGE = 75;
    private static final int CRUSHING_BLOW_MAX_DAMAGE = 175;

    protected Warrior(GamePanel theGamePanel, Keyboard theKeyboard) {
        super(125, "Warrior", 4,
                35, 60,
                0.8, 0.2, theGamePanel, theKeyboard);
    }

    @Override
    public void regularAttack(DungeonCharacter theOpp) {
        attackBehavior(theOpp);
    }

    // Crushing Blow that does 75 to 175 points of damage but only has a 40% chance of succeeding
    @Override
    public void specialSkill(DungeonCharacter theOpp) {
        if (Math.random() <= CRUSHING_BLOW_CHANCE) {
            int damage = genDamage(CRUSHING_BLOW_MIN_DAMAGE, CRUSHING_BLOW_MAX_DAMAGE);
            theOpp.subtractHp(damage);
            System.out.println("Warrior delivers crushing blow to " + theOpp.getChName()+
                    " for " + damage + "damage.");
        } else {
            System.out.println("Crushing blow failed!");
        }
    }

    @Override
    public void getHeroesImage() {

    }

    // dont need at least not for now
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(super.toString()); // Include properties from Hero and DungeonCharacter
//        // Add warrior-specific properties if any
//        return sb.toString();
//    }


}
