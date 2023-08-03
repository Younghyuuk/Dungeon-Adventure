package Model;


import View.GamePanel;
import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class MonsterDataBase {

    private static final String DB_URL = "jdbc:sqlite:monsters.db";
    private GamePanel myGamePanel;

    public MonsterDataBase(GamePanel theGamePanel) {
        myGamePanel = theGamePanel;
//        initializeDatabase();
    }

    /**
     * Create the monsters table if it doesn't exist.
     */
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS monsters ( " +
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


    /**
     * Initialize the monsters table with some default values.
     * This method is for demonstration purposes only.
     */
    public void initializeDatabase() {
        createTable();

        String[] monsterNames = {"Ogre", "skeleton", "Gremlin"};
        int[] hpValues = {200, 100, 70};
        int[] attackSpeedValues = {2, 3, 5};
        int[] minDamageValues = {30, 30, 15};
        int[] maxDamageValues = {60, 50, 30};
        double[] hitChanceValues = {0.6, 0.8, 0.8};
        int[] minHealValues = {30, 30, 20};
        int[] maxHealValues = {60, 50, 40};
        double[] chanceHealValues = {0.1, 0.3, 0.4};

        for (int i = 0; i < monsterNames.length; i++) {
            String name = monsterNames[i];
            int hp = hpValues[i];
            int attackSpeed = attackSpeedValues[i];
            int minDamage = minDamageValues[i];
            int maxDamage = maxDamageValues[i];
            double hitChance = hitChanceValues[i];
            int minHeal = minHealValues[i];
            int maxHeal = maxHealValues[i];
            double chanceHeal = chanceHealValues[i];

            Monster monster = null;
            switch (name) {
                case "Ogre" -> monster = new Ogre(hp, attackSpeed, minDamage, maxDamage, hitChance,
                        chanceHeal, minHeal, maxHeal, myGamePanel);
                case "skeleton" -> monster = new Skeleton(hp, attackSpeed, minDamage, maxDamage, hitChance,
                        chanceHeal, minHeal, maxHeal, myGamePanel);
                case "Gremlin" -> monster = new Gremlin(hp, attackSpeed, minDamage, maxDamage, hitChance,
                        chanceHeal, minHeal, maxHeal, myGamePanel);
            }

            if (monster != null) {
                insertMonster(monster);
            }
        }
    }



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

    /**
     * Retrieve a monster from the database based on its name.
     *
     * @param name The name of the monster to retrieve.
     * @return The retrieved monster.
     */
    public Monster getMonster(String name) {
        String query = "SELECT * FROM monsters WHERE name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int hp = rs.getInt("hp");
                    int attackSpeed = rs.getInt("attackSpeed");
                    int minDamage = rs.getInt("minDamage");
                    int maxDamage = rs.getInt("maxDamage");
                    double hitChance = rs.getDouble("hitChance");
                    int minHeal = rs.getInt("minHeal");
                    int maxHeal = rs.getInt("maxHeal");
                    double chanceHeal = rs.getDouble("chanceHeal");

                    // Create the appropriate monster based on the name retrieved from the database
                    switch (name) {
                        case "Ogre" -> {
                            return new Ogre(hp, attackSpeed, minDamage, maxDamage, hitChance,
                                    chanceHeal, minHeal, maxHeal,myGamePanel);
                        }
                        case "Skeleton" -> {
                            return new Skeleton(hp, attackSpeed, minDamage, maxDamage, hitChance,
                                    chanceHeal, minHeal, maxHeal,myGamePanel);
                        }
                        case "Gremlin" -> {
                            return new Gremlin(hp, attackSpeed, minDamage, maxDamage, hitChance,
                                    chanceHeal, minHeal, maxHeal, myGamePanel);
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
     * Get a random monster from the database.
     *
     * @return The random monster.
     */
    public Monster getRandomMonster() {
        String query = "SELECT name FROM monsters ORDER BY RANDOM() LIMIT 1";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                String name = rs.getString("name");
                return getMonster(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}


