public class TaskManagementSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();
        
        System.out.println("=== Task Management System Demo ===\n");
        
        // Adding tasks
        System.out.println("1. Adding Tasks:");
        taskList.addTask(101, "Complete Project Documentation", "In Progress");
        taskList.addTask(102, "Review Code", "Pending");
        taskList.addTask(103, "Fix Bug #234", "Completed");
        taskList.addTaskAtEnd(104, "Prepare Presentation", "Not Started");
        taskList.addTaskAtEnd(105, "Update Database", "In Progress");
        
        // Traversing tasks
        System.out.println("\n2. Traversing All Tasks:");
        taskList.traverseTasks();
        
        // Searching tasks
        System.out.println("\n3. Searching Tasks:");
        Task foundTask = taskList.searchTask(103);
        if (foundTask != null) {
            System.out.println("Found: " + foundTask);
        } else {
            System.out.println("Task not found!");
        }
        
        // Search by status
        taskList.searchTasksByStatus("In Progress");
        
        // Updating task status
        System.out.println("\n4. Updating Task Status:");
        taskList.updateTaskStatus(102, "Completed");
        taskList.updateTaskStatus(104, "In Progress");
        
        // Display updated list
        taskList.traverseTasks();
        
        // Deleting tasks
        System.out.println("\n5. Deleting Tasks:");
        taskList.deleteTask(103);
        taskList.deleteTask(999); // Non-existent task
        
        // Final list
        System.out.println("\n6. Final Task List:");
        taskList.traverseTasks();
        
        // Performance demonstration
        System.out.println("\n7. Performance Info:");
        System.out.println("Total tasks: " + taskList.getSize());
        System.out.println("Is empty: " + taskList.isEmpty());
        
        // Clear all tasks
        System.out.println("\n8. Clearing All Tasks:");
        taskList.clearAllTasks();
        taskList.traverseTasks();
    }
}

/*
=== TIME COMPLEXITY ANALYSIS ===

1. ADD OPERATION:
   - addTask() (at beginning): O(1) - Direct insertion at head
   - addTaskAtEnd(): O(n) - Need to traverse to the end

2. SEARCH OPERATION:
   - searchTask(): O(n) - May need to traverse entire list
   - searchTasksByStatus(): O(n) - Need to check all nodes

3. DELETE OPERATION:
   - deleteTask(): O(n) - May need to traverse to find the task

4. TRAVERSE OPERATION:
   - traverseTasks(): O(n) - Visit each node once

5. UPDATE OPERATION:
   - updateTaskStatus(): O(n) - First need to search, then update

6. UTILITY OPERATIONS:
   - getSize(): O(1) - We maintain size counter
   - isEmpty(): O(1) - Check if head is null
   - clearAllTasks(): O(1) - Just set head to null

=== ADVANTAGES OF LINKED LISTS OVER ARRAYS FOR DYNAMIC DATA ===

1. DYNAMIC SIZE:
   - Linked lists can grow/shrink during runtime
   - Arrays have fixed size (in traditional arrays)

2. MEMORY EFFICIENCY:
   - No memory waste - only allocate what's needed
   - Arrays may have unused allocated space

3. INSERTION/DELETION:
   - O(1) insertion at beginning for linked lists
   - Arrays require shifting elements O(n)

4. NO MEMORY REALLOCATION:
   - Linked lists don't need contiguous memory
   - Dynamic arrays may need to reallocate entire array

5. FLEXIBILITY:
   - Easy to implement complex data structures
   - Can easily modify structure during runtime

DISADVANTAGES:
1. No random access - O(n) to access by index
2. Extra memory overhead for pointers
3. Not cache-friendly due to non-contiguous memory
4. No backward traversal in singly linked lists
*/