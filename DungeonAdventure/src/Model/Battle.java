package Model;

import java.io.Serial;
import java.io.Serializable;

/**
 * Battle class that returns Strings in an array
 * for GUi class to interact with
 *
 * @author Andrew Chon, Halim Lee, Marrok Young
 * @version July 19, 2023
 */
public class Battle implements Serializable {
    /**
     * The serial ID associated with this 'GameData' object.
     */
    @Serial
    private static final long serialVersionUID = 123456789L;
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
     * Starts the battle of hero vs monster taking into account of attackspeed, block chance
     * and damage.
     */

    public void startBattle() {
        StringBuilder sb = new StringBuilder();
        sb.append(myHero.getChName()).append(" engages in battle with ").append(myMonster.getChName()).append(". \n");

        while (myHero.isAlive() && myMonster.isAlive()) {

            //If hero is faster this sequence happens
            if (myHero.getAttackSpeed() > myMonster.getAttackSpeed()) {
                sb.append(heroAttack());

                if(myMonster.isAlive()) {
                    if (Math.random() > myHero.getBlockChance()) {
                        sb.append(monsterAttack());
                    } else {
                        sb.append("Hero blocks attack. \n");
                    }
                }

                //If Monster is faster this sequence happens
            } else {

//                sb.append(myMonster.getChName()).append("s turn \n");

                if (Math.random() > myHero.getBlockChance()) {
                    sb.append(monsterAttack());

                } else {
                    sb.append("Hero blocks attack. \n");

                }
                if(myHero.isAlive()) {
                    sb.append(heroAttack());
                }
            }


        }


        if (myHero.isAlive()) {
            sb.append(myHero.getChName()).append(" defeats ").append((myMonster.getChName())).
                    append(" with ").append(myHero.getHp()).append(" hp! \n");
        } else {
            sb.append(myHero.getChName()).append(" has been defeated by ").
                    append((myMonster.getChName())).append("! \n");
        }
        addToLog(sb.toString());
    }


    /**
     * Gives sequence of attacks and possible special skills used by the hero to
     * attack monster while giving logs about the attacks.
     *
     * @return String of hero attack log
     */
    String heroAttack() {
        StringBuilder attackLog = new StringBuilder();
        if (Math.random() <= 0.7) {

            attackLog.append(myHero.regularAttack(myMonster));

        } else {
            attackLog.append(myHero.specialSkill(myMonster));
            if (myHero.getChName().equals("Priestess")) {
                attackLog.append(myHero.getChName()).append(" new hp is ").append(myHero.getHp()).append(" \n");
            } else if (myHero.getChName().equals("Warrior")) {
                attackLog.append(myMonster.getChName()).append(" new hp is ").append(myMonster.getHp()).append(" \n");
            } else {
                attackLog.append(myMonster.getChName()).append(" new hp is ").append(myMonster.getHp()).append(" \n");
            }

        }

        return attackLog.toString();
    }

    /**
     * Give the log of monsters attacks and special ability making it all into a String
     * a helper method for the startBattle method.
     *
     * @return String of monster attacks
     */
    String monsterAttack() {
        StringBuilder attackLog = new StringBuilder();
        attackLog.append(myMonster.regularAttack(myHero));
        attackLog.append(myMonster.heal());
        return attackLog.toString();
    }

    /**
     * Gets the battleLog.
     *
     * @return string[] batteLog
     */
    public String[] getMyBattleLog() {
        return myBattleLog;
    }

    /**
     * Adds String messages to myBattleLog array.
     *
     * @param theMessage to add String into battle log
     */
    void addToLog(String theMessage) {
        myBattleLog[myIndex++] = theMessage;
    }

}

