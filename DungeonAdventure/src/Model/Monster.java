package Model;


import java.util.UUID;

/**
 * Monster abstract class that extends DungeonCharacter
 */
public abstract class Monster extends DungeonCharacter {
    private final UUID myId;
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
    protected Monster(final int theHp, final String theChName, final int theAttackSpeed,
                      final int theMinDamage, final int theMaxDamage, final double theHitChance, final UUID theId) {
        super(theHp, theChName, theAttackSpeed, theMinDamage, theMaxDamage, theHitChance);
        myId = theId;
    }

    public UUID getId() {
        return myId;
    }

    public void saveToDatabase() {
        MonsterDataBase.getInstance().saveMonsterToDatabase(this);
    }

    // Factory method to get a monster by its ID from the database
    public static Monster getMonsterFromDatabase(UUID id) {
        return MonsterDataBase.getInstance().getMonsterFromDatabase(id);
    }

    /**
     * Monster attack.
     *
     * @param theOpp which the monster will target
     */
    public abstract void regularAttack(final DungeonCharacter theOpp);

    /**
     * Abstract method for monster child classes to heal.
     */
    public abstract void heal();
}
