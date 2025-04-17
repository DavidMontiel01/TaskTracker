package Tasks;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaskPrinter {
    private static final String BORDER = "+------+------------------------------------------+------------------+------------------+------------------+";
    private static final String HEADER = "| ID   | DESCRIPTION                              | STATUS           | DATE CREATED     | DATE UPDATED     |";
    private static final String FORMAT = "| %-5s| %-40s | %-16s | %-16s | %-16s |\n";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm");
    private static final int MAX_DESC_LENGTH = 40;

    /**
     * {@code TaskPrinter} should never be instantiated, because we only need to use the methods and not any specific instance of {@code this} class
     */
    private TaskPrinter(){}

    private static void printTask(Task task){
        System.out.println(BORDER);
        System.out.printf(FORMAT, task.getId(), truncate(task.getDescription()), task.getStatus(), task.getCreatedAt().format(DATE_TIME_FORMATTER), task.getUpdatedAt().format(DATE_TIME_FORMATTER));
        System.out.println(BORDER);
    }

    public static void printRepo(List<Task> taskList){
        System.out.println(BORDER);
        System.out.println(HEADER);
        taskList.forEach(TaskPrinter::printTask);
    }

    public static void printRepo(List<Task> taskList, TaskStatus status){
        System.out.println(BORDER);
        System.out.println(HEADER);
        taskList.stream().filter(t -> t.getStatus().equals(status)).forEach(TaskPrinter::printTask);
        System.out.println("\n");
    }

    private static String truncate(String description){
        StringBuilder truncatedString = new StringBuilder();
        if(description.length() >= MAX_DESC_LENGTH){
            truncatedString.append(description, 0, description.length()-3).append("...");
        }
        else{
            return description;
        }
        return truncatedString.toString();
    }
}
