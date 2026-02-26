package service;

import model.Task;
import repository.TaskRepository;

import java.util.List;

public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public void addTask(String title) {
        if (title == null || title.isBlank()) return;
        repository.save(title.trim());
    }

    public void toggleTask(Task task) {
        repository.updateCompletion(task.getId(), task.isCompleted());
    }

    public void deleteTask(Task task) {
        repository.delete(task.getId());
    }

    public int countCompleted() {
        return (int) repository.findAll()
                .stream()
                .filter(Task::isCompleted)
                .count();
    }

    public int countTotal() {
        return repository.findAll().size();
    }
}