class Task {
    private int taskId;
    private String taskName;
    private String status;
    private Task next; // Reference to the next task in the linked list
    
    // Constructor
    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }
    
    // Getters and Setters
    public int getTaskId() {
        return taskId;
    }
    
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    
    public String getTaskName() {
        return taskName;
    }
    
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Task getNext() {
        return next;
    }
    
    public void setNext(Task next) {
        this.next = next;
    }
    
    @Override
    public String toString() {
        return String.format("Task[ID: %d, Name: %s, Status: %s]", 
                           taskId, taskName, status);
    }
}