package repository;

import model.Task;
import java.util.List;

public interface TaskRepository {

    List<Task> findAll();

    void save(String title);

    void updateCompletion(int id, boolean completed);

    void delete(int id);
}