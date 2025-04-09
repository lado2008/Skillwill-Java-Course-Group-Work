import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static String username = "";
    private static Map<String, Task> tasks = new HashMap<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        while (username.isEmpty()){
            System.out.println("Enter your username: ");
            username = scanner.nextLine();
        }

        label:
        while (true) {
            System.out.println("Commands:\n 1. - Add Task,\n 2. - List All Task,\n 3. - Update Task,\n 4. - Delete Task,\n 5. - Get Specific Task,\n 6. - Exit");
            System.out.print("Enter command 1, 2, 3, 4, 5 or 6: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    addTask();
                    break;
                case "2":
                    listTasks();
                    break;
                case "3":
                    updateTask();
                    break;
                case "4":
                    deleteTask();
                    break;
                case "5":
                    getTaskDetails();
                    break;
                case "6":
                    System.out.println("Goodbye!");
                    break label;
                default:
                    System.out.println("Unknown command.");
                    break;
            }
        }
    }
    public static void addTask() {
        System.out.println("Enter task type ( 1. - basic, 2. - limited, 3. - repeatable): ");
        String type = scanner.nextLine();

        System.out.println("Task Name: ");
        String taskName = scanner.nextLine();

        while (taskName.isEmpty()) {
            System.out.println("Task Name: ");
            taskName = scanner.nextLine();
        }

        if (tasks.containsKey(taskName)) {
            System.out.println("Task with this name already exists!");
            return;
        }

        System.out.println("Task Description: ");
        String taskDesc = scanner.nextLine();

        if (type.equals("1")) {
            tasks.put(taskName, new BasiceTask(taskName, taskDesc, username));
        } else if (type.equals("2")) {
            boolean repeat = true;

            while (repeat) {
                try {
                    System.out.println("Deadline (YYYY-MM-DD HH:MM): ");
                    LocalDateTime deadline = LocalDateTime.parse(scanner.nextLine(), formatter);
                    tasks.put(taskName, new TimeLimitedTask(taskName, taskDesc, username, deadline));
                    repeat = false;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid Input Detected!");
                    repeat = true;
                }
            }
        } else if (type.equals("3")) {
            boolean repeat = true;
            while (repeat) {
                try {
                    System.out.println("How Many Times Should We Repeat Task?: ");
                    int repeatCount = Integer.parseInt(scanner.nextLine());
                    System.out.println("Repeated Task's Due Date (YYYY-MM-DD HH:MM): ");
                    LocalDateTime repeatTime = LocalDateTime.parse(scanner.nextLine(), formatter);
                    tasks.put(taskName, new RepeatableTask(taskName, taskDesc, username, repeatTime, repeatCount));
                    repeat = false;
                } catch (DateTimeParseException | NumberFormatException e) {
                    if (e instanceof DateTimeParseException){
                        System.out.println("Invalid Date Format! Restarting...");
                    } else {
                        System.out.println("Invalid Input Type! Restarting...");
                    }
                    repeat = true;
                }
            }
        } else {
            System.out.println("Invalid Task Type!");
        }
    }
    public static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No Task Found!");
        } else {
            for (String taskName : tasks.keySet()) {
                System.out.println(" - " + taskName);
            }
        }
    }
    public static void updateTask() {
        System.out.println("Enter the Task Name you want to Update: ");
        String taskName = scanner.nextLine();
        Task task = tasks.get(taskName);

        if (task == null) {
            System.out.println("Task Not Found!");
        }

        System.out.println("Enter New Description: ");
        String newDescription = scanner.nextLine();
        task.setDescription(newDescription);

        task.updateFileds(scanner);

        System.out.println("Task Updated");
    }
    public static void deleteTask() {
        System.out.println("Enter the Task name you want to delete: ");
        String tasName = scanner.nextLine();
        if (tasks.remove(tasName) != null) {
            System.out.println("Task Deleted!");
        } else {
            System.out.println("Task Not Found!");
        }
    }
    public static void getTaskDetails() {
        System.out.println("Enter the Task Name you want to see: ");
        String taskName = scanner.nextLine();
        Task task = tasks.get(taskName);

        if (task != null) {
            System.out.println(task.getDetails());
        } else {
            System.out.println("Task Not Found!");
        }
    }
}