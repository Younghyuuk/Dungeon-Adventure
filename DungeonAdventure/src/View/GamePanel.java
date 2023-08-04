package View;

import Control.Keyboard;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {

    // Actual GUI screen size.
    private final static int ORIGINAL_SPRITE_SIZE = 16;
    private final static int SCALE = 3;
    private final int mySpriteSize = ORIGINAL_SPRITE_SIZE * SCALE;
    private final int myMaxScreenRow = 12;
    private final int myMaxScreenCol = 16;
    private final int myScreenWidth = myMaxScreenCol * mySpriteSize;
    private final int myScreenHeight = myMaxScreenRow * mySpriteSize;
    private final static int FPS = 60;

    //We may need to pass this from mainframe and play with the gamestate to load new dungeons.=
    private Dungeon myDungeon = new Dungeon();
    // these values will be dependent on Dungeon Maze array size.
    /**
     * The width of the world map as a 2d array
     */
    private final int myWorldCol = myDungeon.getDungeonWidth() * myDungeon.getRooms()[0][0].getRoomWidth();

    /**
     * The height of the world map as a 2d array
     */
    private final int myWorldRow = myDungeon.getDungeonHeight() * myDungeon.getRooms()[0][0].getRoomHeight();

    private TileManager myTileM = new TileManager(this);
    private final Thread gameThread = new Thread(this);
    private final Collision myCollision = new Collision(this);
    private final Keyboard myKeyInputs = new Keyboard(this);
    private final TitlePage myTitlePage = new TitlePage(this);
    private final CharacterSelectionPage myCharacterSelectionPage = new CharacterSelectionPage(this);
    private final AboutPage myAboutPage = new AboutPage(this);
    private BattlePage myBattlePage = new BattlePage(this);
    private final GameOver myGameOverPage = new GameOver(this);
    private final WinPage myWinPage = new WinPage(this);
    private String[] myBattleLog;
    private int winCount = 0;
    InitiateEntites myIE = new InitiateEntites(this);
    List<FourPillars> myPillarArray = myIE.getMyFourPillarsArray();
    List<Monster> myMonsterArray = myIE.getMyMonsterArray();

    private Heroes myHero;
    private int myHeroNum;
    private int myGameState;
    private final static int TITLE_STATE = 0;
    private final static int CHARACTER_STATE = 1;
    private final static int PLAY_STATE = 2;
    private final static int BATTLE_STATE = 3;
    private final static int GAME_OVER_STATE = 4;
    private final static int WIN_STATE = 5;
    private int count = 0;
    private boolean myAboutState = false;



    public GamePanel() {
        setPreferredSize(new Dimension(myScreenWidth, myScreenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(myKeyInputs);
        setFocusable(true);
        myGameState = TITLE_STATE;
        setMyHero(1);
    }
    public void setNewGame(){
        if (!myMonsterArray.isEmpty()) {
            myMonsterArray.clear();
        }
        myIE.createMonster();
        myMonsterArray = myIE.getMyMonsterArray();
        for (FourPillars pillars :myPillarArray){
            pillars.setFound(false);
        }
        winCount = 0;
    }
    public void resetGame(){
        setMyHero(myHeroNum);
        for (Monster mon : myMonsterArray){
            mon.resetHP();
        }
        for (FourPillars pillars :myPillarArray){
            pillars.setFound(false);
        }
        winCount = 0;
    }

    public void setMyBattleLog(String[] theBattleLog){
        myBattleLog = theBattleLog;
    }
    public CharacterSelectionPage getMyCharacterSelectionPage(){
        return myCharacterSelectionPage;
    }
    public int getMyGameState() {
        return myGameState;
    }
    public WinPage getMyWinPage(){
        return myWinPage;
    }
    public TitlePage getMyTitlePage() {
        return myTitlePage;

    }
    public GameOver getMyGameOver(){
        return myGameOverPage;
    }
    public void setMyDungeon(Dungeon theDungeon){
        myDungeon = theDungeon;
    }
    public void setMyGameState(int theGameState) {
        myGameState = theGameState;
    }

    public void startGame() {
        gameThread.start();
    }

    public int getSpriteSize() {
        return mySpriteSize;
    }

    public int getMyScreenWidth() {
        return myScreenWidth;
    }

    public int getMyScreenHeight() {
        return myScreenHeight;
    }

    public int getMyWorldCol() {
        return myWorldCol;
    }

    public int getMyWorldRow() {
        return myWorldRow;
    }

    public Dungeon getMyDungeon() {
        return myDungeon;
    }

    public Heroes getMyHero() {
        return myHero;
    }

    public Collision getMyCollision() {
        return myCollision;
    }

    public TileManager getMyTileM() {
        return myTileM;
    }
    public List<Monster> getMyMonsterArray(){
        return myMonsterArray;
    }
    public List<FourPillars> getMyPillarArray(){
        return myPillarArray;
    }
    public int getWinCount(){
        return winCount;
    }
    public void incWinCount(){
        winCount++;
    }
    public void setMyAboutState(boolean theAboutState) {
        myAboutState = theAboutState;
    }
    public boolean getMyAboutState(){
        return myAboutState;
    }

    // For now, 1 is thief, 2 is Priestess, and 3 is Warrior.
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

    // This method will update our model.
    public void update() {
        if(myGameState == PLAY_STATE) {
            myHero.update();
            for (Monster mon : myMonsterArray){
                mon.update();
            }
        }
        if (!myHero.isAlive()){
            setMyGameState(4);
        }
        if(winCount == 4){
            setMyGameState(5);
        }
        System.out.println(winCount);
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
            if (myAboutState) {
                myAboutPage.draw(pen);
            }
            pen.dispose();
        }
        else if (myGameState == BATTLE_STATE){
            myTileM.draw(pen);
            myHero.draw(pen);
            myBattlePage.setMyBattleLog(myBattleLog);

            for (Monster mon : myMonsterArray) {
                if (mon.isAlive()) {
                    mon.draw(pen);
                }
            }
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
