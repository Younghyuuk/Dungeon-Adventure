package Model;

import View.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class MonsterDataBaseTest {

    private MonsterDataBase myMonsterDataBase;

    @BeforeEach
    public void setup() {
        GamePanel mockGamePanel = new GamePanel(); // You need to instantiate the actual GamePanel or use a mock if available
        myMonsterDataBase = new MonsterDataBase(mockGamePanel);

        // Create an in-memory database for testing
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");
            myMonsterDataBase.createTable();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateTable() {
        // The table creation should not throw an exception
        assertDoesNotThrow(() -> myMonsterDataBase.createTable());
    }

    @Test
    public void testGetMonster() {
        // Insert a sample monster into the in-memory database

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");
            MonsterDataBase mockDataBase = new MonsterDataBase(null);
            mockDataBase.createTable();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retrieve the monster using the database
        Monster retrievedMonster = myMonsterDataBase.getMonster("Ogre");

        assertNotNull(retrievedMonster);

    }

    @Test
    public void testGetRandomMonster() {
        // Insert sample monsters into the in-memory database
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");
            MonsterDataBase mockDataBase = new MonsterDataBase(null);
            mockDataBase.createTable();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retrieve a random monster using the database
        Monster randomMonster = myMonsterDataBase.getRandomMonster();

        assertNotNull(randomMonster);
    }
}

