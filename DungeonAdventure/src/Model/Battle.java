package Model;

/**
 * Battle class that returns Strings in an array
 * for GUi class to interact with
 *
 * @author Andrew Chon, Halim Lee, Marrok Young
 * @version July 19, 2023
 */
public class Battle {

    /**
     * Hero class so all other types of Heroes can be called.
     */
    private Heroes myHero;

    /**
     * Monster class field so that any type of Monster can be called in battle.
     */
    private Monster myMonster;

    /**
     * String array of battle logs to be used by the GUI.
     */
    private String[] myBattleLog;

    // Empty constructor so others can't change
    private Battle() {

    }

    /**
     * Main constructor to set all Hero and Monster classes
     * it also sets the battle log and starts battle.
     *
     * @param theHero    hero class of Thief, Warrior, or Priestess
     * @param theMonster Monster class of Ogre, Skeleton, or Gremlin
     */
    public Battle(Heroes theHero, Monster theMonster) {
        myHero = theHero;
        myMonster = theMonster;
        myBattleLog = new String[100];
        startBattle();
    }


    /**
     * @return
     */

    // change to String[] return later
    public String startBattle() {
        StringBuilder battleLog = new StringBuilder();

        battleLog.append(myHero.getChName()).append(" engages in battle with ").append(myMonster.getChName()).append("!\n");
        battleLog.append("=============================================\n");

        while (myHero.isAlive() && myMonster.isAlive()) {
            battleLog.append(performAttack(myHero, myMonster));
            if (myMonster.isAlive()) {
                battleLog.append(performAttack(myHero, myMonster));
            }
        }

        battleLog.append("=============================================\n");

        if (myHero.isAlive()) {
            battleLog.append(myHero.getChName()).append(" defeats ").append(myMonster.getChName()).append("!\n");
        } else {
            battleLog.append(myHero.getChName()).append(" has been defeated by ").append(myMonster.getChName()).append("!\n");
        }

        return battleLog.toString();
    }

//    private String performAttack(DungeonCharacter theAttacker, DungeonCharacter theDefender) {
//        StringBuilder attackLog = new StringBuilder();
//
//        attackLog.append(theAttacker.getChName()).append(" attacks ").append(theDefender.getChName()).append("\n");
//
//        if (Math.random() <= theAttacker.getHitChance()) {
//            int damage = theAttacker.genDamage(theAttacker.getMinDamage(), theAttacker.getMaxDamage());
//            theDefender.subtractHp(damage);
//            attackLog.append(theAttacker.getChName()).append(" hits ").append(theDefender.getChName()).append(" for ")
//                    .append(damage).append(" damage!\n");
//            attackLog.append(theDefender.getChName()).append(" has ").append(theDefender.getHp())
//                    .append(" hit points remaining.\n");
//        } else {
//            attackLog.append(theAttacker.getChName()).append(" misses the attack!\n");
//        }
//
//        return attackLog.toString();
//    }

    private String performAttack(Heroes theHero, Monster theMon) {
        StringBuilder attackLog = new StringBuilder();

        // Heroes attack
        if (theHero.getAttackSpeed() > theMon.getAttackSpeed()) {
            attackLog.append(theHero.getChName()).append(" attacks ").append(theMon.getChName()).append("\n");

            if (Math.random() <= theHero.getHitChance()) {
                theHero.regularAttack(theMon);
                theHero.specialSkill(theMon);
                attackLog.append(theHero.getChName()).append(" hits ").append(theMon.getChName()).append("\n");
                attackLog.append(theMon.getChName()).append(" has ").append(theMon.getHp()).append(" hit points remaining.\n");
            } else {
                attackLog.append(theHero.getChName()).append(" misses the attack!\n");
            }
            // Monster attack
        } else {
            attackLog.append(theMon.getChName()).append(" attacks ").append(theHero.getChName()).append("\n");

            if (Math.random() <= theMon.getHitChance()) {
                theMon.regularAttack(theHero);
                theMon.heal();

                attackLog.append(theMon.getChName()).append(" hits ").append(theHero.getChName()).append("\n");
                attackLog.append(theHero.getChName()).append(" has ").append(theHero.getHp()).append(" hit points remaining.\n");

                //add a heal chance attackLog

            } else {
                attackLog.append(theMon.getChName()).append(" misses the attack!\n");
            }
        }



        return attackLog.toString();
    }

}

