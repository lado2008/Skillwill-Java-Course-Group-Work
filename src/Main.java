import java.time.LocalDateTime;
import java.util.*;
import java.time.format.DateTimeFormatter;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static String username;
    private static Map<String, Task> tasks = new HashMap<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {

        System.out.println("Enter your username: ");
        username = scanner.nextLine();

        while (true) {
            System.out.println("Commands: 1. - add, 2. - list, 3. - update, 4. - delete, 5. - get, 6. - exit");
            System.out.print("Enter command 1, 2, 3, 4, 5 or 6: ");
            String input = scanner.nextLine();

            if (input.equals("1")) {
                addTask();
            } else if (input.equals("2")) {
                listTasks();
            } else if (input.equals("3")) {
                updateTask();
            } else if (input.equals("4")) {
                deleteTask();
            } else if (input.equals("5")) {
                getTaskDetails();
            } else if (input.equals("6")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Unknown command.");
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
                System.out.println("Deadline (YYYY-MM-DD HH:MM): ");
                LocalDateTime deadline = LocalDateTime.parse(scanner.nextLine(), formatter);
                tasks.put(taskName, new TimeLimitedTask(taskName, taskDesc, username, deadline));
            } else if (type.equals("3")) {
                System.out.println("Repeat Task: ");
                int repeatCount = Integer.parseInt(scanner.nextLine());
                System.out.println("Repeat Time (YYYY-MM-DD HH:MM): ");
                LocalDateTime repeatTime = LocalDateTime.parse(scanner.nextLine(), formatter);
                tasks.put(taskName, new RepeatableTask(taskName, taskDesc, username, repeatTime, repeatCount));
            } else {
                System.out.println("This type of task doesn't exists!");
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
