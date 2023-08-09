package View;

import Control.Keyboard;
import Data.SaveLoad;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * This class acts as the GUI for the DungeonAdventure game.
 */
public class GamePanel extends JPanel implements Runnable, Serializable{
    @Serial
    private static final BigInteger serialVersionUID = new BigInteger("501648749617479875");

    /**
     * The actual size of a sprite (16x16).
     */
    private final static int ORIGINAL_SPRITE_SIZE = 16;
    /**
     * The multiplier to make the sprites fit onto the screen properly.
     */
    private final static int SCALE = 3;
    /**
     * The size we want the sprites to be to fit the screen.
     */
    private final int mySpriteSize = ORIGINAL_SPRITE_SIZE * SCALE;
    /**
     * The amount of tiles to fit into a row of the screen.
     */
    private final int myMaxScreenRow = 12;
    /**
     * The amount of tiles to fit into a column of the screen.
     */
    private final int myMaxScreenCol = 16;
    /**
     * The width of the screen based on the tile size and the max amount of tiles in a column.
     */
    private final int myScreenWidth = myMaxScreenCol * mySpriteSize;
    /**
     * The height of the screen based on the tile size and the max amount of tiles in a row.
     */
    private final int myScreenHeight = myMaxScreenRow * mySpriteSize;
    /**
     * The amount of frames per second this game will have.
     */
    private final static int FPS = 60;
    /**
     * The dungeon object that the game panel will use to load the game correctly.
     */
    private Dungeon myDungeon = new Dungeon();
    /**
     * The width of the game column-wise. <br>
     * Determined by the dungeon width (the amount of rooms in each column) * the width of a room.
     */
    private final int myWorldCol = myDungeon.getDungeonWidth() * myDungeon.getRooms()[0][0].getRoomWidth();
    /**
     * The height of the game row-wise. <br>
     * Determined by the dungeon height (the amount of rooms in each row) * the height of a room.
     */
    private final int myWorldRow = myDungeon.getDungeonHeight() * myDungeon.getRooms()[0][0].getRoomHeight();
    /**
     * The object that will manage all the background tiles in the dungeon (the walls, floor, and doors
     */
    private transient TileManager myTileM = new TileManager(this);
    /**
     * The thread to use to run the game.
     */
    private transient final Thread gameThread = new Thread(this);
    /**
     * The object that will check for collisions between the player and everything in the dungeon.
     */
    private final Collision myCollision = new Collision(this);
    /**
     * The object that will check for player key inputs to move the character.
     */
    private final transient Keyboard myKeyInputs = new Keyboard(this);
    /**
     * The object that will create the title page that is shown before starting a game.
     */
    private final transient TitlePage myTitlePage = new TitlePage(this);
    /**
     * The object that will create a character selection page that is shown after the title page.
     */
    private final transient CharacterSelectionPage myCharacterSelectionPage = new CharacterSelectionPage(this);
    /**
     * The object that will tell the player the rules about the game.
     */
    private final transient AboutPage myAboutPage = new AboutPage(this);
    /**
     * The object that will display the results of a battle between the character and a monster.
     */
    private transient BattlePage myBattlePage = new BattlePage(this);
    /**
     * The page that will display if the player dies before collecting all 4 pillars.
     */
    private final transient GameOver myGameOverPage = new GameOver(this);
    /**
     * The page that will display if the player collects all 4 pillars before dying.
     */
    private final transient WinPage myWinPage = new WinPage(this);
    /**
     * Stores every move made by the character and monster as well as their health
     * and if their moves landed or not.
     */
    private String[] myBattleLog;
    /**
     * The amount of pillars that the player has picked up.
     */
    private int winCount = 0;
    /**
     * The object that initiates all the entities within the dungeon.
     */
    InitiateEntities myIE = new InitiateEntities(this);
    /**
     * The list of all pillars stored within the game.
     */
    List<FourPillars> myPillarArray = myIE.getMyFourPillarsArray();
    /**
     * The list of all the monsters stored within the game.
     */
    List<Monster> myMonsterArray = myIE.getMyMonsterArray();
    /**
     * A list of every item contained within the dungeon.
     */
    private List<Item> myItemArray = myIE.getMyItemArray();
    /**
     * The hero the player will play as.
     */
    private Heroes myHero;
    /**
     * The specific class the player picked.
     */
    private int myHeroNum;
    /**
     * Represents the state that the game is in. <br>
     * 0 - Title page <br>
     * 1 - Character selection <br>
     * 2 - Play <br>
     * 3 - Battle <br>
     * 4 - Game over <br>
     * 5 - Game won
     */
    private int myGameState;
    /**
     * Number representing that the game is in the title page state of the game.
     */
    private final static int TITLE_STATE = 0;
    /**
     * Number representing that the game is in the character selection state of the game.
     */
    private final static int CHARACTER_STATE = 1;
    /**
     * Number representing that the game is in the game play state of the game.
     */
    private final static int PLAY_STATE = 2;
    /**
     * Number representing that the game is in the battle state of the game.
     */
    private final static int BATTLE_STATE = 3;
    /**
     * Number representing that the game is in the game over page state of the game.
     */
    private final static int GAME_OVER_STATE = 4;
    /**
     * Number representing that the game is in the game won page state of the game.
     */
    private final static int WIN_STATE = 5;
    /**
     * Boolean representing whether the player is looking at the about section of the game.
     */


    public SaveLoad saveLoad = new SaveLoad(this);

    private boolean myAboutState = false;
    /**
     * Creates and sets up the game panel.
     */
    public GamePanel() {
//        myGameData = new GameData(this);
        setPreferredSize(new Dimension(myScreenWidth, myScreenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(myKeyInputs);
        setFocusable(true);
        myGameState = TITLE_STATE;
        setMyHero(1);
    }

    /**
     * Sets up a brand-new game!
     */
    public void setNewGame(){
        if (!myMonsterArray.isEmpty()) {
            myMonsterArray.clear();
        }
        myIE.createMonster();
        myMonsterArray = myIE.getMyMonsterArray();
        for (FourPillars pillars :myPillarArray){
            pillars.setFound(false);
        }
        for (Item item : myItemArray) {
            item.setFound(false);
        }
        winCount = 0;
    }

    /**
     * Resets everything in the game.
     */
    public void resetGame(){
        setMyHero(myHeroNum);
        for (Monster mon : myMonsterArray){
            mon.resetHP();
        }
        for (FourPillars pillars :myPillarArray){
            pillars.setFound(false);
        }
        for (Item item : myItemArray) {
            item.setFound(false);
        }
        winCount = 0;
    }

    /**
     * Sets the in class battle log to the input battle log.
     *
     * @param theBattleLog The battle log to set this class's battle log to.
     */
    public void setMyBattleLog(String[] theBattleLog){
        myBattleLog = theBattleLog;
    }

    /**
     * Gets the character selection page.
     *
     * @return The character selection page.
     */
    public CharacterSelectionPage getMyCharacterSelectionPage(){
        return myCharacterSelectionPage;
    }

    /**
     * Gets the current state that the game is in.
     *
     * @return The state the game is in.
     */
    public int getMyGameState() {
        return myGameState;
    }

    /**
     * Gets the win page for the game.
     *
     * @return The win page.
     */
    public WinPage getMyWinPage(){
        return myWinPage;
    }

    /**
     * Gets the title page for the game.
     *
     * @return The title page.
     */
    public TitlePage getMyTitlePage() {
        return myTitlePage;

    }

    /**
     * Gets the game over screen for the game.
     *
     * @return The game over screen.
     */
    public GameOver getMyGameOver(){
        return myGameOverPage;
    }

    /**
     * Sets the in class dungeon to the input dungeon.
     *
     * @param theDungeon The input dungeon to set this class's dungeon to.
     */
    public void setMyDungeon(Dungeon theDungeon){
        myDungeon = theDungeon;
    }

    /**
     * Sets the current game state.
     *
     * @param theGameState The input game state to change the game state to.
     */
    public void setMyGameState(int theGameState) {
        myGameState = theGameState;
    }

    /**
     * Starts the game.
     */
    public void startGame() {
        gameThread.start();
    }

    /**
     * Gets the sprite size.
     *
     * @return The sprite size.
     */
    public int getSpriteSize() {
        return mySpriteSize;
    }

    /**
     * Gets the width of the game screen.
     *
     * @return The screen width.
     */
    public int getMyScreenWidth() {
        return myScreenWidth;
    }

    /**
     * Gets the height of the game screen.
     *
     * @return the screen height.
     */
    public int getMyScreenHeight() {
        return myScreenHeight;
    }

    /**
     * Gets the total width of the world map.
     *
     * @return The total width, or column size, of the world map.
     */
    public int getMyWorldCol() {
        return myWorldCol;
    }

    /**
     * Gets the total height of the world map.
     *
     * @return The total height, or row size, of the world map.
     */
    public int getMyWorldRow() {
        return myWorldRow;
    }

    /**
     * Gets the dungeon this game panel is using.
     *
     * @return The dungeon used by this game panel.
     */
    public Dungeon getMyDungeon() {
        return myDungeon;
    }

    /**
     * Gets the current hero being used in this game panel.
     *
     * @return The hero used by this game panel.
     */
    public Heroes getMyHero() {
        return myHero;
    }

    /**
     * Gets the current collision object used by this game panel.
     *
     * @return The collision object used by this game panel.
     */
    public Collision getMyCollision() {
        return myCollision;
    }

    /**
     * Gets the tile manager used by this game panel.
     *
     * @return The TileManager used by this game panel.
     */
    public TileManager getMyTileM() {
        return myTileM;
    }

    /**
     * Gets the list of every monster contained within the dungeon.
     *
     * @return The list of all monsters in the dungeon.
     */
    public List<Monster> getMyMonsterArray(){
        return myMonsterArray;
    }

    /**
     * Gets the list of all four pillars of OO.
     *
     * @return The list of all four pillars of OO in the dungeon.
     */
    public List<FourPillars> getMyPillarArray(){
        return myPillarArray;
    }

    /**
     * Gets the list of every item contained within the dungeon.
     *
     * @return The list of the items in the game.
     */
    public List<Item> getMyItemArray() {
        return myItemArray;
    }
    /**
     * Gets the amount of pillars that the player has picked up.
     *
     * @return The amount of pillars the player has obtained.
     */
    public int getWinCount(){
        return winCount;
    }

    /**
     * Increases the amount of pillars that the player has obtained by 1.
     */
    public void incWinCount(){
        winCount++;
    }

    /**
     * Sets the boolean that determines if the player has the about page open.
     *
     * @param theAboutState The boolean that represents whether the player is looking at the about page.
     */
    public void setMyAboutState(boolean theAboutState) {
        myAboutState = theAboutState;
    }

    /**
     * Gets the boolean that represents whether the player is looking at the about page.
     *
     * @return True or false depending on if the player has the about page open.
     */
    public boolean getMyAboutState(){
        return myAboutState;
    }

    // For now, 1 is thief, 2 is Priestess, and 3 is Warrior.

    /**
     * Sets the hero that the player is using. <br>
     * 1 - Thief <br>
     * 2 - Warrior <br>
     * 3 - Priestess
     *
     * @param number The number representing which hero the player has chosen.
     */
    public void setMyHero(int number) {
        if (number == 1) {
            myHero = new Thief(this, myKeyInputs);
            myHeroNum= 1;
        }
        if (number == 2) {
            myHero = new Warrior(this, myKeyInputs);
            myHeroNum= 2;
        }
        if (number == 3) {
            myHero = new Priestess(this, myKeyInputs);
            myHeroNum= 3;
        }
    }


    @Override
    public void run() {
        // Game Loop. Repainting the screen 60 FPS. Credit given to ryisnow.
        double interval = 1000000000 / FPS;
        double delta = 0;
        long pastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - pastTime) / interval;
            pastTime = currentTime;

            // if more than a 16 millisecond has passed than repaint and update the panel;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    /**
     * Updates the game panel depending on the state of the game.
     */
    public void update() {
        if(myGameState == PLAY_STATE) {
            myHero.update();
            for (Monster mon : myMonsterArray){
                mon.update();
            }
        }
        if(winCount == 4){
            setMyGameState(5);
        }
//        System.out.println(myHero.getHp());
    }

    // This method updates our view.
    @Override
    public void paintComponent(Graphics theGraphics) {
        super.paintComponent(theGraphics);
        Graphics2D pen = (Graphics2D) theGraphics;

        if (myGameState == TITLE_STATE) {
            myTitlePage.draw(pen);
            pen.dispose();
        } else if (myGameState == CHARACTER_STATE) {
            myCharacterSelectionPage.draw(pen);
            pen.dispose();
        } else if (myGameState == PLAY_STATE) {
            myTileM.draw(pen);
            myHero.draw(pen);
            for (Monster mon : myMonsterArray){
                if (mon.isAlive()){
                    mon.draw(pen);
                }
            }
            for (FourPillars pill : myPillarArray){
                if(!pill.getFound()){
                    pill.draw(pen);
                }
            }
            for (Item item : myItemArray) {
                if(!item.getFound()) {
                    item.draw(pen);
                }
            }
            if (myAboutState) {
                myAboutPage.draw(pen);
            }
            pen.dispose();
        }
        else if (myGameState == BATTLE_STATE){
            myBattlePage.setMyBattleLog(myBattleLog);
            myBattlePage.draw(pen);
            pen.dispose();
        }
        else if(myGameState == GAME_OVER_STATE){
            myGameOverPage.draw(pen);
        }
        else {
            myWinPage.draw(pen);
        }
    }
}
