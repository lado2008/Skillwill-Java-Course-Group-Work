import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class RepeatableTask extends Task{
    private LocalDateTime deadline;
    private int repeatCount;

    public RepeatableTask(String name, String description, String taskCreator, LocalDateTime deadline, int repeatCount) {
        super(name, description, taskCreator);
        this.deadline = deadline;
        this.repeatCount = repeatCount;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    @Override
    public String getDetails() {
        return "Basic Task name: " + name + " Description: " + description + " Deadline: "+ deadline + " Repeat Count: "+ repeatCount + " Task Creator: " + taskCreator ;
    }
    @Override
    public void updateFileds(Scanner scanner) {
        System.out.println("Enter New Repeat Count: ");
        this.repeatCount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter New Deadline (YYYY-MM-DD HH:MM): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.deadline = LocalDateTime.parse(scanner.nextLine(),formatter);
    }
}