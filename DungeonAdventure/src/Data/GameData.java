package Data;

import Model.Dungeon;
import Model.Heroes;
import Model.Room;
import View.GamePanel;

import java.io.*;

/**
 * This class saves all the game data.
 */
public class GameData implements Serializable {
    /**
     * The serial ID associated with this 'GameData' object.
     */
    @Serial
    private static final long serialVersionUID = 123456789L;
    /**
     * The hero object to save.
     */
    private Heroes myHero;
    /**
     * The dungeon object to save.
     */
    private Dungeon myDungeon;
    /**
     * The game panel object that contains all the game data.
     */
    private transient GamePanel myGp;
    /**
     * The middle x-coordinate of the screen.
     */
    private int myScreensMiddleX;
    /**
     * The middle y-coordinate of the screen.
     */
    private int myScreensMiddleY;
    /**
     * The world x-coordinate of the hero.
     */
    private int myWorldX;
    /**
     * The world y-coordinate of the hero.
     */
    private int myWorldY;
    /**
     * The HP of the specific hero to save.
     */
    private int myHeroHp;
    /**
     * The name of the specific hero to save.
     */
    private String myHeroName;
    /**
     * The speed at which the hero can attack to save.
     */
    private int myHeroAttackSpeed;
    /**
     * The minimum damage an attack by the hero can do to save.
     */
    private int myHeroMinDamage;
    /**
     * The maximum damage an attack by the hero can do to save.
     */
    private int myHeroMaxDamage;
    /**
     * The block chance of the hero to save.
     */
    private double myHeroBlock;
    /**
     * The chance that a hero's attack hits.
     */
    private double myHeroHit;

    private Room[][] myRooms;
    private int[][] myDoors;

//    /**
//     * The 2D array of rooms that makes up the dungeon.
//     */
//    private Room[][] myDungeonRooms;
//    /**
//     * The 2D boolean array that represents all the visited rooms in the dungeon.
//     */
//    private boolean[][] myVisitedRooms;

    /**
     * Constructs the game data that will be saved.
     */
    public GameData() {
        myGp = new GamePanel();
    }

    /**
     * Gets the game panel associated with this object.
     *
     * @return The game panel to save.
     */
    public GamePanel getMyGp() {
        return myGp;
    }

    /**
     * Gets the dungeon associated with this object.
     *
     * @return The dungeon to save.
     */
    public Dungeon getMyDungeon() {
        return myDungeon;
    }

    public Room[][] getMyRooms() {
        return myRooms;
    }

    /**
     * Gets 'myHero'.
     *
     * @return The 'Heroes' object represented by 'myHero'.
     */
    public Heroes getMyHero() {
        return myHero;
    }

    /**
     * Gets the hero's HP.
     *
     * @return The hero's HP.
     */
    public int getMyHeroHp() {
        return myHeroHp;
    }

    /**
     * Gets the name of the hero.
     *
     * @return The name of the hero as a String.
     */
    public String getMyHeroName() {
        return myHeroName;
    }

    /**
     * Gets the attack speed of the hero.
     *
     * @return The attack speed of the hero.
     */
    public int getMyHeroAttackSpeed() {
        return myHeroAttackSpeed;
    }

    /**
     * Gets the minimum amount of damage the hero can inflict.
     *
     * @return The minimum damage the hero can inflict.
     */
    public int getMyHeroMinDamage() {
        return myHeroMinDamage;
    }

    /**
     * Gets the maximum amount of damage the hero can inflict.
     *
     * @return The maximum damage the hero can inflict.
     */
    public int getMyHeroMaxDamage() {
        return myHeroMaxDamage;
    }

    /**
     * Gets the block chance of the hero.
     *
     * @return The block chance of the hero.
     */
    public double getMyHeroBlock() {
        return myHeroBlock;
    }

    /**
     * Gets the hit chance of the hero.
     *
     * @return The hit chance of the hero.
     */
    public double getMyHeroHit() {
        return myHeroHit;
    }

    /**
     * Gets the screens middle x-coordinate.
     *
     * @return The middle x-coordinate of the screen.
     */
    public int getMyScreensMiddleX() {
        return myScreensMiddleX;
    }

    /**
     * Gets the screens middle y-coordinate.
     *
     * @return The middle y-coordinate of the screen.
     */
    public int getMyScreensMiddleY() {
        return myScreensMiddleY;
    }

    /**
     * Gets the world x-coordinate of the player.
     *
     * @return The world-x coordinate of the player.
     */
    public int getMyWorldX() {
        return myWorldX;
    }

    /**
     * Gets the world y-coordinate of the player.
     *
     * @return The world-y coordinate of the player.
     */
    public int getMyWorldY() {
        return myWorldY;
    }

//    /**
//     * Gets the 2D room array that represents the dungeon.
//     *
//     * @return The 2D array of 'Room' objects.
//     */
//    public Room[][] getMyDungeonRooms() {
//        return myDungeonRooms;
//    }
//
//    /**
//     * Gets the 2D boolean array that represents all the visited rooms in the dungeon.
//     *
//     * @return The 2D array of visited rooms.
//     */
//    public boolean[][] getMyVisitedRooms() {
//        return myVisitedRooms;
//    }
    public int[][] getDoors() {
        return myDoors;
    }


    /**
     * Sets the dungeon to the specified 'Dungeon' object.
     *
     * @param theDungeon The dungeon to set 'myDungeon' to.
     */
    public void setMyDungeon(final Dungeon theDungeon) {
        myDungeon = theDungeon;
    }

    public void setDoors(int[][] theDoors) {
        myDoors = theDoors;
    }
    public void setRooms(Room[][] theRooms) {
        myRooms = theRooms;
    }

    /**
     * Sets the hero to the specified 'Heroes' object.
     *
     * @param theHero The hero to set 'myHero' to.
     */
    public void setMyHero(final Heroes theHero) {
        myHero = theHero;
    }

    /**
     * Sets the hero's HP to an input HP.
     *
     * @param theHP The input HP to change the current HP to.
     */
    public void setMyHeroHp(final int theHP) {
        myHeroHp = theHP;
    }

    /**
     * Sets the name of the hero.
     *
     * @param theName The input name to set as the new hero name.
     */
    public void setMyHeroName(final String theName) {
        myHeroName = theName;
    }

    /**
     * Sets the attack speed of the hero.
     *
     * @param theAttackSpeed The attack speed to set as the new hero attack speed.
     */
    public void setMyHeroAttackSpeed(final int theAttackSpeed) {
        myHeroAttackSpeed = theAttackSpeed;
    }

    /**
     * Sets the minimum damage able to be inflicted by the hero.
     *
     * @param theMinDamage The minimum damage to set as the new hero min attack damage.
     */
    public void setMyHeroMinDamage(final int theMinDamage) {
        myHeroMinDamage = theMinDamage;
    }

    /**
     * Sets the maximum damage able to be inflicted by the hero.
     *
     * @param theMaxDamage The maximum damage to set as the new hero max attack damage.
     */
    public void setMyHeroMaxDamage(final int theMaxDamage) {
        myHeroMaxDamage = theMaxDamage;
    }

    /**
     * Sets the block chance of the hero.
     *
     * @param theBlockChance The block chance to set as the new hero block chance.
     */
    public void setMyHeroBlock(final double theBlockChance) {
        myHeroBlock = theBlockChance;
    }

    /**
     * Sets the hit chance of the hero.
     *
     * @param theHitChance The hit chance to set as the new hero hit chance.
     */
    public void setMyHeroHit(final double theHitChance) {
        myHeroHit = theHitChance;
    }

    /**
     * Sets the middle x-coordinate of the screen.
     *
     * @param theScreenMiddleX The screen middle x to set as the new screen middle x.
     */
    public void setMyScreensMiddleX(final int theScreenMiddleX) {
        myScreensMiddleX = theScreenMiddleX;
    }

    /**
     * Sets the middle y-coordinate of the screen.
     *
     * @param theScreenMiddleY The screen middle y to set as the new screen middle y.
     */
    public void setMyScreensMiddleY(final int theScreenMiddleY) {
        myScreensMiddleY = theScreenMiddleY;
    }

    /**
     * Sets the world x-coordinate of the player.
     *
     * @param theWorldX The world x to set as the new x position for the player.
     */
    public void setMyWorldX(final int theWorldX) {
        myWorldX = theWorldX;
    }

    /**
     * Sets the world y-coordinate of the player.
     *
     * @param theWorldY The world y to set as the new y position for the player.
     */
    public void setMyWorldY(final int theWorldY) {
        myWorldY = theWorldY;
    }


//    /**
//     * Sets the 2D array of rooms.
//     *
//     * @param theDungeonRooms The 2D array of rooms to set as the new 2D 'Room' array.
//     */
//    public void setMyDungeonRooms(final Room[][] theDungeonRooms) {
//        myDungeonRooms = theDungeonRooms;
//    }
//
//    /**
//     * Sets the 2D array of visited rooms.
//     *
//     * @param theVisitedRooms The input visited rooms array to set as the new visited rooms array.
//     */
//    public void setMyVisitedRooms(final boolean[][] theVisitedRooms) {
//        myVisitedRooms = theVisitedRooms;
//    }


//    private Dungeon myDungeon;
//    //    private Heroes myHero;
//    private int myGameState;


}

