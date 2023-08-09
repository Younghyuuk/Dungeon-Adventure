package Model;

import Control.Keyboard;
import View.GamePanel;

import java.io.*;

public class GameData implements Serializable {
    private static final long serialVersionUID = -123456;

    //    public Heroes myHero;
    public int myScreensMiddleX;

    public int myScreensMiddleY;

    public int myHeroHp;
    private Dungeon dungeon;
    private Heroes hero;

//    public GameData(Dungeon dungeon, Heroes hero) {
//        this.dungeon = dungeon;
//        this.hero = hero;
//    }

    // Deserialization method
    public static GameData loadFromFile(String fileName) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))) {
            GameData gameData = (GameData) input.readObject();
            System.out.println("Game data loaded successfully.");
            return gameData;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Delete later
    public static void main(String[] args) {
        // Sample usage
        Dungeon dungeon = new Dungeon();
        GamePanel gp = new GamePanel();
        Keyboard kb = new Keyboard(gp);
        Heroes hero = new Priestess(gp, kb);

        GameData gameData = new GameData();
        gameData.saveToFile("game_data.save");

        GameData loadedGameData = GameData.loadFromFile("game_data.save");
        if (loadedGameData != null) {
            Dungeon loadedDungeon = loadedGameData.getDungeon();
            Heroes loadedHero = loadedGameData.getHero();

            // Now you have access to the loaded dungeon and hero
            // Resume the game or perform any necessary actions
        }
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public Heroes getHero() {
        return hero;
    }

    // Serialization method
    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            System.out.println("Game data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

