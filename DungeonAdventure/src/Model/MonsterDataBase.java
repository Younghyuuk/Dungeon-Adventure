package Model;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MonsterDataBase {


    private static final String DB_URL = "jdbc:sqlite:monsters.db";

    //    private static final String MONSTERS_TABLE = "monsters";

    private static MonsterDataBase myInstance;
//    private Connection connection;

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
        String query = "CREATE TABLE IF NOT EXISTS monsters ( " +
                "id TEXT PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "hp INTEGER NOT NULL, " +
                "attackSpeed INTEGER NOT NULL, " +
                "minDamage INTEGER NOT NULL, " +
                "maxDamage INTEGER NOT NULL, " +
                "hitChance REAL NOT NULL, " +
                "minHeal INTEGER NOT NULL, " +
                "maxHeal INTEGER NOT NULL, " +
                "chanceHeal REAL NOT NULL )";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveMonster(Monster monster) {
        String query = "INSERT INTO monsters (name, hp, attackSpeed, minDamage, maxDamage, " +
                "hitChance, , chanceHeal, minHeal, maxHeal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

//            statement.setString(1, monster.getId().toString());
            statement.setString(1, monster.getChName());
            statement.setInt(2, monster.getHp());
            statement.setInt(3, monster.getAttackSpeed());
            statement.setInt(4, monster.getMinDamage());
            statement.setInt(5, monster.getMaxDamage());
            statement.setDouble(6, monster.getHitChance());
            statement.setDouble(7, monster.getChanceHeal());
            statement.setInt(8, monster.getMinHeal());
            statement.setInt(9, monster.getMaxHeal());


            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve a monster from the database based on its ID.
     *
     * @return The retrieved monster.
     */
    public Monster getMonster() {
        String query = "SELECT * FROM monsters WHERE name = ?";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

//            statement.setString(1, theId.toString());

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    int hp = rs.getInt("hp");
                    int attackSpeed = rs.getInt("attackSpeed");
                    int minDamage = rs.getInt("minDamage");
                    int maxDamage = rs.getInt("maxDamage");
                    double hitChance = rs.getDouble("hitChance");
                    double chanceHeal = rs.getDouble("chanceHeal");
                    int minHeal = rs.getInt("minHeal");
                    int maxHeal = rs.getInt("maxHeal");

                    // Create the appropriate monster based on the name retrieved from the database
                    switch (name) {
                        case "Ogre" -> {
                            return new Ogre(hp, attackSpeed, minDamage, maxDamage, hitChance,
                                    chanceHeal, minHeal, maxHeal);
                        }
                        case "Skeleton" -> {
                            return new Skeleton(hp, attackSpeed, minDamage, maxDamage, hitChance,
                                    chanceHeal, minHeal, maxHeal);
                        }
                        case "Gremlin" -> {
                            return new Gremlin(hp, attackSpeed, minDamage, maxDamage, hitChance,
                                    chanceHeal, minHeal, maxHeal);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Retrieve a random monster from the database.
     *
     * @return A random Monster object, or null if no monsters are found in the database.
     */
    public Monster randomMonster() {
        String query = "SELECT * FROM monsters ORDER BY RANDOM() LIMIT 1";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
//                UUID id = UUID.fromString(rs.getString("id"));
                String name = rs.getString("name");
                int hp = rs.getInt("hp");
                int attackSpeed = rs.getInt("attackSpeed");
                int minDamage = rs.getInt("minDamage");
                int maxDamage = rs.getInt("maxDamage");
                double hitChance = rs.getDouble("hitChance");
                double chanceHeal = rs.getDouble("chanceHeal");
                int minHeal = rs.getInt("minHeal");
                int maxHeal = rs.getInt("maxHeal");

                // Create the appropriate monster based on the name retrieved from the database
                switch (name) {
                    case "Ogre" -> {
                        return new Ogre(hp, attackSpeed, minDamage, maxDamage, hitChance,
                                chanceHeal, minHeal, maxHeal);
                    }
                    case "Skeleton" -> {
                        return new Skeleton(hp, attackSpeed, minDamage, maxDamage, hitChance,
                                chanceHeal, minHeal, maxHeal);
                    }
                    case "Gremlin" -> {
                        return new Gremlin(hp, attackSpeed, minDamage, maxDamage, hitChance,
                                chanceHeal, minHeal, maxHeal);
                    }
                    default -> {
                        System.out.println("Invalid monster name: " + name);
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



//    public void closeConnection() {
//        try {
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Get a connection to the database.
     *
     * @return The database connection.
     * @throws SQLException if a database access error occurs.
     */
    private Connection getConnection() throws SQLException {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(DB_URL);
        return ds.getConnection();
    }
}


