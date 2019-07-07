package dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatorDB {
    public void createDB() {
        try (ConnectorDB connector = new ConnectorDB()) {
            Connection conn = connector.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE Users ( " +
                            "id INT NOT NULL PRIMARY KEY IDENTITY, " +
                            "user_name VARCHAR(20) NOT NULL UNIQUE, " +
                            "password VARCHAR(20) NOT NULL," +
                            "rounds_total INT NOT NULL," +
                            "steps_total INT NOT NULL " +
                            ") "
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
