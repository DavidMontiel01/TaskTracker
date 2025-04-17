package Tasks;

public enum TaskStatus {
    DONE("done"), IN_PROGRESS("in-progress"), TODO("todo");
    private final String status;

    TaskStatus(String status){
        this.status = status;
    }

    public String toString() {
        return status;
    }
}