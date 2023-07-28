package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MonsterDataBase {


    private static final String DB_URL = "jdbc:sqlite:monsters.db";
    private static final String MONSTERS_TABLE = "monsters";

    private static MonsterDataBase myInstance;
    private Connection connection;

    public MonsterDataBase() {
        createMonstersTable();
    }

    public static MonsterDataBase getInstance() {
        if (myInstance == null) {
            myInstance = new MonsterDataBase();
        }
        return myInstance;
    }

    private void createMonstersTable() {
        // ... (Same as before, create the monsters table if it doesn't exist)
        try {
            connection = DriverManager.getConnection(DB_URL);
            String sql = "CREATE TABLE IF NOT EXISTS Monsters (id TEXT PRIMARY KEY, name TEXT, type TEXT, hp INT, " +
                    "attackSpeed INT, chanceToHit REAL, minDamage INT, maxDamage INT, chanceToHeal REAL, minHeal INT, maxHeal INT)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

    }

    public void saveMonsterToDatabase(Monster monster) {
        try {
            connection = DriverManager.getConnection(DB_URL);
            String sql = "INSERT INTO Monsters (id, name, type, hp, attackSpeed, chanceToHit, minDamage, maxDamage, " +
                    "chanceToHeal, minHeal, maxHeal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, monster.getId().toString());
            statement.setString(2, monster.getChName());
            statement.setString(3, monster.getClass().getSimpleName());
            statement.setInt(4, monster.getHp());
            statement.setInt(5, monster.getAttackSpeed());
            statement.setDouble(6, monster.getHitChance());
            statement.setInt(7, monster.getMinDamage());
            statement.setInt(8, monster.getMaxDamage());
            statement.setDouble(9, monster.getChanceHeal());
            statement.setInt(10, monster.getMinHeal());
            statement.setInt(11, monster.getMaxHeal());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public List<Monster> getMonsterFromDatabase() {
        List<Monster> monsters = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(DB_URL);
            String sql = "SELECT * FROM Monsters";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                int hp = resultSet.getInt("hp");
                int attackSpeed = resultSet.getInt("attackSpeed");
                double chanceToHit = resultSet.getDouble("chanceToHit");
                int minDamage = resultSet.getInt("minDamage");
                int maxDamage = resultSet.getInt("maxDamage");
                double chanceToHeal = resultSet.getDouble("chanceToHeal");
                int minHeal = resultSet.getInt("minHeal");
                int maxHeal = resultSet.getInt("maxHeal");

                Monster monster;
                if ("Skeleton".equals(type)) {
                    monster = new Skeleton(id);
                } else if ("Ogre".equals(type)) {
                    monster = new Ogre(id);
                } else if ("Gremlin".equals(type)) {
                    monster = new Gremlin(id);
                } else {
                    // Handle other monster types or throw an exception if needed
                    continue;
                }

                monsters.add(monster);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return monsters;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


