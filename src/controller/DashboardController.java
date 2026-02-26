package controller;

import app.AppContext;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import service.TaskService;

public class DashboardController {

    private final TaskService taskService;

    @FXML
    private Label totalLabel;

    @FXML
    private Label completedLabel;

    @FXML
    private Label percentageLabel;

    @FXML
    private PieChart pieChart;

    public DashboardController(AppContext context) {
        this.taskService = context.getTaskService();
    }

    @FXML
    public void initialize() {
        refreshStats();
    }

    private void refreshStats() {

        int total = taskService.countTotal();
        int completed = taskService.countCompleted();

        double percentage = total == 0 ? 0 : (completed * 1.0) / total;

        totalLabel.setText(String.valueOf(total));
        completedLabel.setText(String.valueOf(completed));
        percentageLabel.setText(
                "Completion: " + String.format("%.1f", percentage * 100) + "%"
        );

        PieChart.Data done = new PieChart.Data("Completed", completed);
        PieChart.Data remaining = new PieChart.Data("Remaining", total - completed);

        pieChart.setData(
                FXCollections.observableArrayList(done, remaining)
        );
    }
}