package Tasks;

import java.util.ArrayList;
import Exceptions.NoSuchTaskException;

/**
    Place where task are stored while the program is running, Only one instance of this class should ever exist at any time
 **/
public class TaskRepository {
    private static TaskRepository myCurrentTaskRepository;
    private final ArrayList<Task> currentRepository;

    private TaskRepository(){
        currentRepository = new ArrayList<>();
    }

    public static TaskRepository getInstance(){
        if(myCurrentTaskRepository == null){
            myCurrentTaskRepository = new TaskRepository();
        }
        return myCurrentTaskRepository;
    }

    public ArrayList<Task> getCurrentRepository(){
        return currentRepository;
    }

    public Task getTask(int id){
        return currentRepository.stream()
                .filter( t -> t.getId() == id).findFirst()
                .orElseThrow(() -> new NoSuchTaskException(String.format("No task with id: %d", id)));
    }

    public void removeTask(int id){
        Task task = currentRepository.stream()
                .filter(t -> t.getId() == id).findFirst()
                .orElseThrow(() -> new NoSuchTaskException(String.format("No task with id: %d", id)));
        currentRepository.remove(task);
    }

}
