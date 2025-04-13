package Tasks;

import java.time.*;
public class Task {
    public enum status {
        DONE("done"), IN_PROGRESS("in progress"), TODO("todo");

        private String status;

        status(String status){
            this.status = status;
        }

        public String getStatus(){
            return status;
        }
    }

    private final int id;
    private final String description;
    private final LocalDateTime dateCreated;
    public status status;
    private LocalDateTime dateModified;

    public Task(int id, String description){
        this.id = id;
        this.description = description;
        dateCreated =  dateModified = LocalDateTime.now() ;
    }

    public int getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }

    public LocalDateTime getDateCreated(){
        return dateCreated;
    }

    public int getStatus(){
        return status.ordinal();
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setStatus(status s){
        status = s;
    }

    public void setDateModified(LocalDateTime time){
        dateModified = time;
    }

}
