import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class TimeLimitedTask extends Task {
    private LocalDateTime deadline;

    public TimeLimitedTask(String name, String description, String taskCreator, LocalDateTime deadline) {
        super(name, description, taskCreator);
        this.deadline = deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public String getDetails() {
        return "Basic Task name: " + name + " Description: " + description + " Deadline: "+ deadline + " Task Creator: " + taskCreator ;
    }

    @Override
    public void updateFileds(Scanner scanner) {
        System.out.println("Enter New Deadline (YYYY-MM-DD HH:MM): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.deadline = LocalDateTime.parse(scanner.nextLine(), formatter);
    }
}