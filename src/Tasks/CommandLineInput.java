package Tasks;

import Exceptions.NoSuchCommandException;

import java.time.LocalDateTime;
import java.util.Scanner;

import static Tasks.TaskRepository.getInstance;
import static java.lang.String.format;

public class CommandLineInput {
    private static final Scanner scanner = new Scanner(System.in);

    //Don't Allow this class to be instantiated
    private CommandLineInput() {
    }

    public static void run() {
        String command;
        while (!(command = scanner.next()).equalsIgnoreCase("exit")) {
            try {
                switch (command) {
                    case "add" -> add();
                    case "update" -> update();
                    case "delete" -> delete();
                    case "mark-in-progress" -> markInProgress();
                    case "mark-done" -> markDone();
                    case "list" -> list();
                    case "help" -> help();
                    default -> throw new NoSuchCommandException(command + "is not a valid command");
                }
            } catch (NoSuchCommandException _) {
            }
        }
        exit();
    }

    public static void add() {
        String description = scanner.nextLine().trim().replace("\"", "");
        Task task = new Task(++Task.CURRENT_TASK_NUMBER, description, LocalDateTime.now());
        getInstance().getCurrentRepository().add(task);
    }

    public static void update() {
        final int id = Integer.parseInt(scanner.next().trim());
        Task task = getInstance().getTask(id);

        String description = scanner.nextLine().trim().replace("\"", "");
        task.setUpdatedAt(LocalDateTime.now());
        task.setDescription(description);

    }

    public static void delete() {
        final int id = Integer.parseInt(scanner.next().trim());
        getInstance().removeTask(id);
    }

    public static void markInProgress() {
        final int id = Integer.parseInt(scanner.next().trim());
        Task task = getInstance().getTask(id);

        task.setUpdatedAt(LocalDateTime.now());
        task.setStatus(TaskStatus.IN_PROGRESS);
    }

    public static void markDone() {
        int id = Integer.parseInt(scanner.next().trim());
        Task task = getInstance().getTask(id);

        task.setUpdatedAt(LocalDateTime.now());
        task.setStatus(TaskStatus.DONE);
    }

    public static void list() {
        String option = scanner.nextLine().trim();
        if (!option.isEmpty() && (option.startsWith("-") || option.startsWith("--")) ) {
            switch (option) {
                case "--all", "-a" -> listAll();
                case "--done", "-d" -> listDone();
                case "--todo", "-t" -> listTodo();
                case "--in-progress", "-i" -> listInProgress();
                case "--help", "-h" -> listHelp();
                default -> listInvalidCommand(option);
            }
        }else {
            listAll();
        }
    }


    public static void listAll() {
        TaskPrinter.printRepo(getInstance().getCurrentRepository());
    }

    public static void listDone() {
        TaskPrinter.printRepo(getInstance().getCurrentRepository(), TaskStatus.DONE);
    }

    public static void listInProgress() {
        TaskPrinter.printRepo(getInstance().getCurrentRepository(), TaskStatus.IN_PROGRESS);
    }

    public static void listTodo() {
        TaskPrinter.printRepo(getInstance().getCurrentRepository(), TaskStatus.TODO);
    }

    public static void listHelp() {
        String helpString = "Usage: list [Option]\n" +
                "List information about the Task(s) (All Task by default).\n" +
                "\nMandatory arguments to long option are mandatory for short options too.\n" +
                format("\t-a, %-20s List Task(s) in current repository.\n", "--all") +
                format("\t-d, %-20s List Task(s) with status DONE\n", "--done") +
                format("\t-i, %-20s List Task(s) with status IN_PROGRESS\n", "--in-progress") +
                format("\t-t, %-20s List Task(s) with status TODO\n", "--todo");
        System.out.println(helpString);
    }

    public static void listInvalidCommand(String command) {
        command = command.replace("--", "");
        System.out.printf("list: invalid option -- '%s'", command);
        System.out.println("\nTry ' list --help' for more information.");
    }

    public static void help() {
        String helpString = "Usage: [cmd] [TaskID]\n" +
                format("\t%-20s [TaskId] Add a task to the current sessions task repository.\n", "add") +
                format("\t%-20s [TaskId] Update the Description for a task.\n", "update") +
                format("\t%-20s [TaskId] Delete Task from current Task Repository.\n", "delete") +
                format("\t%-20s [TaskId] Mark the given Task as in-progress.\n", "mark-in-progress") +
                format("\t%-20s [TaskId] Mark the given Task as Done.\n", "mark-done") +
                format("\t%-20s [option] List all Task that match the given option", "list");
        System.out.println(helpString);
    }

    //currently does nothing
    public static void exit() {
    }
}