public class BasiceTask extends Task {
    public BasiceTask(String name, String description, String taskCreator) {
        super(name, description, taskCreator);
    }

    @Override
    public String getDetails() {
        return "Basic Task name: " + name + " Description: " + description + " Task Creator: " + taskCreator;
    }


}
