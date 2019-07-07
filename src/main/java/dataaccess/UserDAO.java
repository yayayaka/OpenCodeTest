package dataaccess;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeMap;

// Чтение-запись таблицы User
public class UserDAO {

    static {
        CreatorDB creatorDB = new CreatorDB();
        creatorDB.createDB();
    }

    public User get(String name, String password) {
        try (ConnectorDB connector = new ConnectorDB()) {
            Connection conn = connector.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Users " +
                    "WHERE user_name = ? AND password = ? ");
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            User user = new User();
            if (result.next()) {
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                user.setPassword(result.getString(3));
                user.setRoundsTotal(result.getInt(4));
                user.setStepsTotal(result.getInt(5));
                return user;
            }
            else
                return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean set(String name, String password) {
        try (ConnectorDB connector = new ConnectorDB()) {
            Connection conn = connector.getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO Users " +
                    "(user_name, password, rounds_total, steps_total)" +
                    "VALUES " +
                    "(?, ?, 0, 0) ");
            statement.setString(1, name);
            statement.setString(2, password);
            int result = statement.executeUpdate();
            if (result == 0)
                return false;
            else
                return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean remove(String name, String password) {
        try (ConnectorDB connector = new ConnectorDB()) {
            Connection conn = connector.getConnection();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM Users " +
                    "WHERE user_name = ? AND password = ? ");
            statement.setString(1, name);
            statement.setString(2, password);
            int result = statement.executeUpdate();
            if (result == 0)
                return false;
            else
                return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean isExists(String name) {
        try (ConnectorDB connector = new ConnectorDB()) {
            Connection conn = connector.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Users " +
                    "WHERE user_name = ? ");
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            User user = new User();
            if (result.next()) {
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                return true;
            }
            else
                return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean editStats(String name, int addRounds, int addSteps) {
        try (ConnectorDB connector = new ConnectorDB()) {
            Connection conn = connector.getConnection();
            PreparedStatement statement = conn.prepareStatement("UPDATE Users " +
                    "SET rounds_total = (rounds_total + ?), steps_total = (steps_total + ?) " +
                    "WHERE user_name = ? ");
            statement.setInt(1, addRounds);
            statement.setInt(2, addSteps);
            statement.setString(3, name);
            if (statement.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public TreeMap<Integer, String> getStats() {
        TreeMap users = new TreeMap();
        try (ConnectorDB connector = new ConnectorDB()) {
            Connection conn = connector.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT user_name, " +
                    "rounds_total, steps_total FROM Users ");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String userName = result.getString(1);
                int roundsTotal = result.getInt(2);
                int stepsTotal = result.getInt(3);
                int tryNumberAvr = Math.round(((float)stepsTotal)/roundsTotal);
                users.put(tryNumberAvr, userName);
            }
        } catch (SQLException e) {
            return null;
        }
        return users;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList users = new ArrayList();
        try (ConnectorDB connector = new ConnectorDB()) {
            Connection conn = connector.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Users ");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                user.setPassword(result.getString(3));
                user.setRoundsTotal(result.getInt(4));
                user.setStepsTotal(result.getInt(5));
                users.add(user);
            }
        } catch (SQLException e) {
            return null;
        }
        return users;
    }
}
