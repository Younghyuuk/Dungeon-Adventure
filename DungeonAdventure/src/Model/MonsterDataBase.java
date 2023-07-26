package Model;

import java.sql.*;
import java.util.UUID;

public class MonsterDataBase {


    private static final String DATABASE_NAME = "monsters.db";
    private static final String MONSTERS_TABLE = "monsters";

    private static MonsterDataBase myInstance;
    private Connection connection;

    public MonsterDataBase() {
        try {
            // Connect to the SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);

            // Create the monsters table if it doesn't exist
            createMonstersTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createMonstersTable() throws SQLException {
        // ... (Same as before, create the monsters table if it doesn't exist)
    }

    public void saveMonsterToDatabase(Monster monster) {
        String insertSQL = "INSERT INTO " + MONSTERS_TABLE + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, monster.getId().toString());
            preparedStatement.setString(2, monster.getChName());
            preparedStatement.setInt(3, monster.getHp());
            preparedStatement.setInt(4, monster.getAttackSpeed());
            preparedStatement.setDouble(5, monster.getHitChance());
            preparedStatement.setInt(6, monster.getMinDamage());
            preparedStatement.setInt(7, monster.getMaxDamage());
//            preparedStatement.setDouble(8, monster.getChanceToHeal());
//            preparedStatement.setInt(9, monster.getMinHealPoints());
//            preparedStatement.setInt(10, monster.getMaxHealPoints());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static MonsterDataBase getInstance() {
        if (myInstance == null) {
            myInstance = new MonsterDataBase();
        }
        return myInstance;
    }

    public Monster getMonsterFromDatabase(UUID id) {
        String selectSQL = "SELECT * FROM " + MONSTERS_TABLE + " WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, id.toString());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Create and return the monster object from the retrieved data
//                    return new Monster(
//                            UUID.fromString(resultSet.getString("id")),
//                            resultSet.getString("name"),
//                            resultSet.getInt("hitPoints"),
//                            resultSet.getInt("attackSpeed"),
//                            resultSet.getDouble("chanceToHit"),
//                            resultSet.getInt("minDamage"),
//                            resultSet.getInt("maxDamage"),
//                            resultSet.getDouble("chanceToHeal"),
//                            resultSet.getInt("minHealPoints"),
//                            resultSet.getInt("maxHealPoints")
//                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If no monster with the given ID is found, return null
        return null;
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}


