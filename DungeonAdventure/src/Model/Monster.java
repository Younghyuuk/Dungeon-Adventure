package Model;

/**
 * Monster abstract class that extends DungeonCharacter
 */
public abstract class Monster extends DungeonCharacter {

    /**
     * The constructor of DungeonCharacter that initializes the hp, name, attack speed,
     * minimum damage, maximum damage, and the hitchance of the character.
     *
     * @param theHp          amount of hp for character.
     * @param theChName      name of the character.
     * @param theAttackSpeed the attack speed character has.
     * @param theMinDamage   minimum amount of damage they can do.
     * @param theMaxDamage   maximum amount of damage they can do.
     * @param theHitChance   chance the character has to landing attack.
     */
    protected Monster(int theHp, String theChName, int theAttackSpeed,
                      int theMinDamage, int theMaxDamage, double theHitChance) {
        super(theHp, theChName, theAttackSpeed, theMinDamage, theMaxDamage, theHitChance);
    }

    /**
     * Abstract method for monster child classes to heal.
     */
    public abstract void heal();
}
