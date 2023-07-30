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

    /**
     * Index of the myBattleLog array.
     */
    private int myIndex;


    /**
     * Private constructor so Battle class can't be
     * changed of what it has.
     */
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
        myIndex = 0;
        startBattle();
    }


    /**
     * Starts the battle of hero vs monster taking into account of attackspeed
     * and damage.
     *
     * @return myBattleLog array of logs of the battle
     */

    public String[] startBattle() {
        StringBuilder sb = new StringBuilder();
        sb.append(myHero.getChName()).append(" engages in battle with ").append(myMonster.getChName()).append("\n");
        sb.append("==============================================").append("\n");
//        addToLog(myHero.getChName() + " engages in battle with " + myMonster.getChName());
//        addToLog("=============================================");

        while (myHero.isAlive() && myMonster.isAlive()) {
            if (myHero.getAttackSpeed() > myMonster.getAttackSpeed()) {
                heroAttack();

                sb.append(myMonster.getChName()).append( " attacks ").append(myHero.getChName()).append("\n");
//                addToLog((myMonster.getChName() + " attacks " + myHero.getChName()));
                if (Math.random() < myHero.getBlockChance()) {
                    monsterAttack();
                } else {
                    sb.append("Hero blocks attack").append("\n");
//                    addToLog("Hero blocks attack");
                }

            } else {
              sb.append(myMonster.getChName()).append(" attacks ").append(myHero.getChName()).append("\n");
//                addToLog((myMonster.getChName() + " attacks " + myHero.getChName()));
                if (Math.random() < myHero.getBlockChance()) {
                    monsterAttack();
                }
                heroAttack();
            }
//            if (myMonster.isAlive()) {
//                monsterAttack();
//            }

        }
        sb.append("=============================================").append("\n");
//        addToLog("=============================================");
        if (myHero.isAlive()) {
            sb.append(myHero.getChName()).append(" defeats ").append(myMonster.getChName()).append("\n");
//            addToLog(myHero.getChName() + " defeats " + myMonster.getChName());
        } else {
            sb.append(myHero.getChName()).append(" has been defeated by ").append(myMonster.getChName()).append("\n");
//            addToLog(myHero.getChName() + " has been defeated by " + myMonster.getChName());
        }
        addToLog(sb.toString());
        return myBattleLog;
    }


    /**
     * Gives sequence of attacks and possible special skills used by the hero to
     * attack monster while adding to myBattleLog.
     */
    private void heroAttack() {
        StringBuilder attackLog = new StringBuilder();
        attackLog.append(myHero.getChName()).append(" attacks ").append(myMonster.getChName()).append("\n");
        if(Math.random() < 0.5) {
            myHero.regularAttack(myMonster);
        } else {
            myHero.specialSkill(myMonster);
        }
        attackLog.append(myHero.getChName()).append(" tries to hit ").append(myMonster.getChName()).append("\n");
        attackLog.append(myMonster.getChName()).append(" has ").append(myMonster.getHp()).append(" hit points remaining.\n");

        addToLog(attackLog.toString());
    }

    /**
     * Gives the sequence of attacks and heals monster uses while also
     * adding to myBattleLog.
     */
    private void monsterAttack() {
        StringBuilder attackLog = new StringBuilder();


        myMonster.regularAttack(myHero);
        attackLog.append(myMonster.getChName()).append(" tries to hit ").append(myHero.getChName()).append("\n");
        attackLog.append(myHero.getChName()).append(" has ").append(myHero.getHp()).append(" hit points remaining.\n");

        attackLog.append(myMonster.getChName()).append(" tries to heal, original health is ").
                append(myMonster.getHp()).append("\n");
        myMonster.heal();
        attackLog.append(myMonster.getChName()).append(" new hp is ").append(myMonster.getHp());

        addToLog(attackLog.toString());
    }

    public String[] getMyBattleLog() {
        return myBattleLog;
    }

    /**
     * Adds String messages to myBattleLog array.
     *
     * @param theMessage to add String into battle log
     */
    private void addToLog(String theMessage) {
        myBattleLog[myIndex++] = theMessage;
    }

}

