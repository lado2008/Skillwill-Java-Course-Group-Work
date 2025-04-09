import java.util.Scanner;

public abstract class Task {
    protected String name;
    protected String description;
    protected String taskCreator;

    public Task(String name, String description, String taskCreator) {
        this.name = name;
        this.description = description;
        this.taskCreator = taskCreator;
    }
    //<editor-fold desc="Getter Methods">
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTaskCreator() {
        return taskCreator;
    }
    //</editor-fold>
    //<editor-fold desc="Setter Methods">
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskCreator(String taskCreator) {
        this.taskCreator = taskCreator;
    }
    //</editor-fold>

    public void updateFileds(Scanner scanner) {

    }

    public abstract String getDetails();
}