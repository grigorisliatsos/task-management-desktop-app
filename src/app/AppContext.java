package app;

import service.TaskService;

public class AppContext {

    private final TaskService taskService;

    public AppContext(TaskService taskService) {
        this.taskService = taskService;
    }

    public TaskService getTaskService() {
        return taskService;
    }
}