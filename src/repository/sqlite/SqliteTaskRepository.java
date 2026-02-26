package repository.sqlite;

import database.DatabaseManager;
import model.Task;
import repository.TaskRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteTaskRepository implements TaskRepository {

    private final DatabaseManager db;

    public SqliteTaskRepository(DatabaseManager db) {
        this.db = db;
    }

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tasks")) {

            while (rs.next()) {
                tasks.add(new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("completed") == 1
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    @Override
    public void save(String title) {
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO tasks(title, completed) VALUES(?,0)"
             )) {

            ps.setString(1, title);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCompletion(int id, boolean completed) {
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE tasks SET completed=? WHERE id=?"
             )) {

            ps.setInt(1, completed ? 1 : 0);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "DELETE FROM tasks WHERE id=?"
             )) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}