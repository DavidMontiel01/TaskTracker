package Tasks;

import java.time.LocalDateTime;

public class Task {
    private final int id;
    private final String description;
    private final LocalDateTime createdAt;
    private TaskStatus status;
    private LocalDateTime updatedAt;


    public Task(int id, String description, LocalDateTime timeCreated){
        this.id = id;
        this.description = description;
        this.createdAt = timeCreated;
        updatedAt = createdAt;
    }

    public int getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }

    public TaskStatus getStatus(){
        return status;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }

    public void setStatus(TaskStatus status){
        this.status = status;
    }

    public void setUpdatedAt(LocalDateTime updatedTime){
        this.updatedAt = updatedTime;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Task task)) return false;
        return id == task.id && description.equals(task.description)
                && createdAt.equals(task.createdAt) && status == task.status && updatedAt.equals(task.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + description.hashCode();
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + updatedAt.hashCode();
        return result;
    }
}
