package Model;

public class Battle {

    private Heroes myHero;
    private Monster myMonster;

    private  Battle() {

    }

    public Battle(Heroes theHero, Monster theMonster) {
        myHero = theHero;
        myMonster = theMonster;
    }

    public String startBattle() {
        StringBuilder battleLog = new StringBuilder();

        battleLog.append(myHero.getChName()).append(" engages in battle with ").append(myMonster.getChName()).append("!\n");
        battleLog.append("=============================================\n");

        while (myHero.isAlive() && myMonster.isAlive()) {
            battleLog.append(performAttack(myHero, myMonster));
            if (myMonster.isAlive()) {
                battleLog.append(performAttack(myMonster, myHero));
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

    private String performAttack(DungeonCharacter theAttacker, DungeonCharacter theDefender) {
        StringBuilder attackLog = new StringBuilder();

        attackLog.append(theAttacker.getChName()).append(" attacks ").append(theDefender.getChName()).append("\n");

        if (Math.random() <= theAttacker.getHitChance()) {
            int damage = theAttacker.genDamage(theAttacker.getMinDamage(), theAttacker.getMaxDamage());
            theDefender.subtractHp(damage);
            attackLog.append(theAttacker.getChName()).append(" hits ").append(theDefender.getChName()).append(" for ")
                    .append(damage).append(" damage!\n");
            attackLog.append(theDefender.getChName()).append(" has ").append(theDefender.getHp())
                    .append(" hit points remaining.\n");
        } else {
            attackLog.append(theAttacker.getChName()).append(" misses the attack!\n");
        }

        return attackLog.toString();
    }
}

