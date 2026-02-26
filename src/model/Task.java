package model;

import javafx.beans.property.*;

public class Task {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final BooleanProperty completed = new SimpleBooleanProperty();

    public Task(int id, String title, boolean completed) {
        this.id.set(id);
        this.title.set(title);
        this.completed.set(completed);
    }

    public int getId() {
        return id.get();
    }

    public String getTitle() {
        return title.get();
    }

    public boolean isCompleted() {
        return completed.get();
    }

    public void setCompleted(boolean value) {
        this.completed.set(value);
    }

    public BooleanProperty completedProperty() {
        return completed;
    }

    public StringProperty titleProperty() {
        return title;
    }
}