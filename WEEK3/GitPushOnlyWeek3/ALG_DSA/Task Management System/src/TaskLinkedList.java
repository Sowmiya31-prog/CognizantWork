class TaskLinkedList {
    private Task head; // Reference to the first task
    private int size; // Keep track of the number of tasks
    
    // Constructor
    public TaskLinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    /**
     * Add a new task at the beginning of the list
     * Time Complexity: O(1)
     */
    public void addTask(int taskId, String taskName, String status) {
        Task newTask = new Task(taskId, taskName, status);
        newTask.setNext(head);
        head = newTask;
        size++;
        System.out.println("Task added successfully: " + newTask);
    }
    
    /**
     * Add a new task at the end of the list
     * Time Complexity: O(n)
     */
    public void addTaskAtEnd(int taskId, String taskName, String status) {
        Task newTask = new Task(taskId, taskName, status);
        
        if (head == null) {
            head = newTask;
        } else {
            Task current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newTask);
        }
        size++;
        System.out.println("Task added at end: " + newTask);
    }
    
    /**
     * Search for a task by ID
     * Time Complexity: O(n)
     */
    public Task searchTask(int taskId) {
        Task current = head;
        while (current != null) {
            if (current.getTaskId() == taskId) {
                return current;
            }
            current = current.getNext();
        }
        return null; // Task not found
    }
    
    /**
     * Search for tasks by status
     * Time Complexity: O(n)
     */
    public void searchTasksByStatus(String status) {
        Task current = head;
        boolean found = false;
        System.out.println("\nTasks with status '" + status + "':");
        
        while (current != null) {
            if (current.getStatus().equalsIgnoreCase(status)) {
                System.out.println(current);
                found = true;
            }
            current = current.getNext();
        }
        
        if (!found) {
            System.out.println("No tasks found with status: " + status);
        }
    }
    
    /**
     * Delete a task by ID
     * Time Complexity: O(n)
     */
    public boolean deleteTask(int taskId) {
        if (head == null) {
            System.out.println("Task list is empty!");
            return false;
        }
        
        // If the task to delete is the first node
        if (head.getTaskId() == taskId) {
            head = head.getNext();
            size--;
            System.out.println("Task with ID " + taskId + " deleted successfully!");
            return true;
        }
        
        // Search for the task to delete
        Task current = head;
        while (current.getNext() != null) {
            if (current.getNext().getTaskId() == taskId) {
                current.setNext(current.getNext().getNext());
                size--;
                System.out.println("Task with ID " + taskId + " deleted successfully!");
                return true;
            }
            current = current.getNext();
        }
        
        System.out.println("Task with ID " + taskId + " not found!");
        return false;
    }
    
    /**
     * Traverse and display all tasks
     * Time Complexity: O(n)
     */
    public void traverseTasks() {
        if (head == null) {
            System.out.println("No tasks in the list!");
            return;
        }
        
        System.out.println("\n=== All Tasks ===");
        Task current = head;
        int position = 1;
        
        while (current != null) {
            System.out.println(position + ". " + current);
            current = current.getNext();
            position++;
        }
        System.out.println("Total tasks: " + size);
    }
    
    /**
     * Update task status
     * Time Complexity: O(n)
     */
    public boolean updateTaskStatus(int taskId, String newStatus) {
        Task task = searchTask(taskId);
        if (task != null) {
            String oldStatus = task.getStatus();
            task.setStatus(newStatus);
            System.out.println("Task ID " + taskId + " status updated from '" + 
                             oldStatus + "' to '" + newStatus + "'");
            return true;
        } else {
            System.out.println("Task with ID " + taskId + " not found!");
            return false;
        }
    }
    
    /**
     * Get the size of the task list
     * Time Complexity: O(1)
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Check if the list is empty
     * Time Complexity: O(1)
     */
    public boolean isEmpty() {
        return head == null;
    }
    
    /**
     * Clear all tasks
     * Time Complexity: O(1)
     */
    public void clearAllTasks() {
        head = null;
        size = 0;
        System.out.println("All tasks cleared!");
    }
}