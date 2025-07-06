public class MVCPatternExample {
    
    public static void main(String[] args) {
        // Step 1: Create the model (Student)
        Student student = new Student("John Doe", "STU001", "A");
        
        // Step 2: Create the view
        StudentView view = new StudentView();
        
        // Step 3: Create the controller
        StudentController controller = new StudentController(student, view);
        
        // Step 4: Demonstrate the MVC pattern
        demonstrateMVCPattern(controller, view);
    }
    
    private static void demonstrateMVCPattern(StudentController controller, StudentView view) {
        // Display initial student details
        view.displayHeader("Initial Student Record");
        controller.updateView();
        
        // Update student details using controller
        view.displayHeader("Updating Student Information");
        
        // Update name
        controller.setStudentName("Jane Smith");
        controller.displaySuccessMessage("Name update");
        
        // Update ID
        controller.setStudentId("STU002");
        controller.displaySuccessMessage("ID update");
        
        // Update grade with validation
        String newGrade = "B+";
        if (controller.isValidGrade(newGrade)) {
            controller.setStudentGrade(newGrade);
            controller.displaySuccessMessage("Grade update");
        } else {
            controller.displayErrorMessage("Invalid grade format");
        }
        
        // Display updated student details
        view.displayHeader("Updated Student Record");
        controller.updateView();
        
        // Demonstrate bulk update
        view.displayHeader("Bulk Update Example");
        controller.updateStudentDetails("Alice Johnson", "STU003", "A-");
        controller.displaySuccessMessage("Bulk update");
        controller.updateView();
        
        // Demonstrate error handling
        view.displayHeader("Error Handling Example");
        String invalidGrade = "Z++";
        if (!controller.isValidGrade(invalidGrade)) {
            controller.displayErrorMessage("Invalid grade '" + invalidGrade + "' - must be A-F with optional +/- or numeric");
        }
        
        // Display final state
        view.displayHeader("Final Student Record");
        view.displayMessage("Student Name: " + controller.getStudentName());
        view.displayMessage("Student ID: " + controller.getStudentId());
        view.displayMessage("Student Grade: " + controller.getStudentGrade());
        
        // Summary
        view.displayHeader("MVC Pattern Benefits Demonstrated");
        view.displayMessage("✓ Model: Student class handles data storage");
        view.displayMessage("✓ View: StudentView class handles presentation");
        view.displayMessage("✓ Controller: StudentController class handles business logic");
        view.displayMessage("✓ Separation of concerns achieved");
        view.displayMessage("✓ Easy to maintain and extend");
    }
}
