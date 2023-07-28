package Model;

import org.sqlite.SQLiteDataSource;

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

    public void saveMonsterToDatabase(Monster monster) {
        String query = "INSERT INTO monsters (id, name, hp, attackSpeed, minDamage, maxDamage, " +
                "hitChance, minHeal, maxHeal, chanceHeal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, monster.getId().toString());
            pstmt.setString(2, monster.getChName());
            pstmt.setInt(3, monster.getHp());
            pstmt.setInt(4, monster.getAttackSpeed());
            pstmt.setInt(5, monster.getMinDamage());
            pstmt.setInt(6, monster.getMaxDamage());
            pstmt.setDouble(7, monster.getHitChance());
            pstmt.setInt(8, monster.getMinHeal());
            pstmt.setInt(9, monster.getMaxHeal());
            pstmt.setDouble(10, monster.getChanceHeal());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve a monster from the database based on its ID.
     *
     * @param id The ID of the monster to retrieve.
     * @return The retrieved monster.
     */
    public Monster getMonster(UUID id) {
        String query = "SELECT * FROM monsters WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, id.toString());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    int hp = rs.getInt("hp");
                    int attackSpeed = rs.getInt("attackSpeed");
                    int minDamage = rs.getInt("minDamage");
                    int maxDamage = rs.getInt("maxDamage");
                    double hitChance = rs.getDouble("hitChance");
                    int minHeal = rs.getInt("minHeal");
                    int maxHeal = rs.getInt("maxHeal");
                    double chanceHeal = rs.getDouble("chanceHeal");

                    // Create the appropriate monster based on the name retrieved from the database
                    if (name.equals("Ogre")) {
                        return new Ogre(id, hp, attackSpeed, minDamage, maxDamage, hitChance,
                                chanceHeal, minHeal, maxHeal);
                    } else if (name.equals("Skeleton")) {
                        return new Skeleton(id, hp, attackSpeed, minDamage, maxDamage, hitChance,
                                chanceHeal, minHeal, maxHeal);
                    } else if (name.equals("Gremlin")) {
                        return new Gremlin(id, hp, attackSpeed, minDamage, maxDamage, hitChance,
                                chanceHeal, minHeal, maxHeal);
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


