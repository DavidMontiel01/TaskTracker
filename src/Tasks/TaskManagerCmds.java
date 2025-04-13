package Tasks;

import java.util.Scanner;

public class TaskManagerCmds {
    private static final Scanner scanner = new Scanner(System.in);

    public TaskManagerCmds(){

    }

    public static void run(){
        String cmd;
        while(!(cmd = scanner.next().toLowerCase()).equals("exit")){
            switch(cmd){
                case "add" -> add();
                case "update" -> update();
                case "delete" -> delete();
                case "mark-done" -> markDone();
                case "mark-in-progress" -> markInProgress();
                case "list" -> listAll();
                default -> help();
            }
        }
    }
    public static void add(){
        String description = scanner.nextLine().trim();
        System.out.println(description);
    }
    public static void update(){

    }
    public static void delete(){

    }
    public static void markDone(){}
    public static void markInProgress(){}
    public static void list(){
        String option = scanner.next().trim();
    }
    public static void listAll(){
    }
    public static void listDone(){}
    public static void listNotDone(){}
    public static void listInProgress(){
    }
    public static void help(){

    }
}
