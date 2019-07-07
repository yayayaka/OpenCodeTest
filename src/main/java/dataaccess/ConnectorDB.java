package dataaccess;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Класс-коннектор к базе. Использована база HSQLDB с сохранением в оперативной памяти, т.е. при перезапуске
// программы все данные в базе будут утеряны.
public class ConnectorDB implements Closeable {
    private Connection conn;

    public ConnectorDB() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            conn = DriverManager.getConnection("jdbc:hsqldb:mem:./data/ProjectDB", "SA", "");
        } catch (ClassNotFoundException | SQLException e) {
            conn = null;
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    @Override
    public void close() {
        try {
            if (!conn.isClosed())
                conn.close();
        } catch (SQLException e) {
            conn = null;
        }
    }
}
