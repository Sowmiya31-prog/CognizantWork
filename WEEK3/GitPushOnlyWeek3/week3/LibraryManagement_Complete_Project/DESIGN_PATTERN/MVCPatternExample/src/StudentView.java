class StudentView {
    
    public void displayStudentDetails(String studentName, String studentId, String studentGrade) {
        System.out.println("=== Student Details ===");
        System.out.println("Name: " + studentName);
        System.out.println("ID: " + studentId);
        System.out.println("Grade: " + studentGrade);
        System.out.println("=======================");
    }
    
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    public void displayHeader(String header) {
        System.out.println("\n" + header);
        System.out.println("=".repeat(header.length()));
    }
}

// StudentController.java - Controller Class
class StudentController {
    private Student model;
    private StudentView view;
    
    // Constructor
    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }
    
    // Methods to update model data
    public void setStudentName(String name) {
        model.setName(name);
    }
    
    public void setStudentId(String id) {
        model.setId(id);
    }
    
    public void setStudentGrade(String grade) {
        model.setGrade(grade);
    }
    
    // Methods to get model data
    public String getStudentName() {
        return model.getName();
    }
    
    public String getStudentId() {
        return model.getId();
    }
    
    public String getStudentGrade() {
        return model.getGrade();
    }
    
    // Method to update view
    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
    
    // Business logic methods
    public void updateStudentDetails(String name, String id, String grade) {
        if (name != null && !name.trim().isEmpty()) {
            model.setName(name);
        }
        if (id != null && !id.trim().isEmpty()) {
            model.setId(id);
        }
        if (grade != null && !grade.trim().isEmpty()) {
            model.setGrade(grade);
        }
    }
    
    public boolean isValidGrade(String grade) {
        return grade.matches("[A-F][+-]?") || grade.matches("\\d{1,3}");
    }
    
    public void displaySuccessMessage(String operation) {
        view.displayMessage("✓ " + operation + " completed successfully!");
    }
    
    public void displayErrorMessage(String error) {
        view.displayMessage("✗ Error: " + error);
    }
}

