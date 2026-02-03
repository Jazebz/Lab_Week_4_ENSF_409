package edu.ucalgary.oop;

/**
 * GradeManager class handles operations related to managing a list of students,
 * including adding students, retrieving them by ID, and calculating the class average.
 */
public class GradeManager {
    private Student[] students; // Fixed-size array to store student objects
    private int currentSize;    // Tracks the number of students currently added

    // Constructor to initialize the student list.
    public GradeManager(int maxStudents) {
        this.students = new Student[maxStudents];
        this.currentSize = 0;
    }
    

    /**
     * Adds a new student to the list if the student ID is unique.
     * @param student The Student object to be added.
     * @throws IllegalArgumentException if a student with the same ID already exists.
     * @throws IllegalStateException if the student list is full.
     */
    public void addStudent(Student student) {
        if (this.currentSize >= this.students.length) {
            throw new IllegalStateException("Cannot add more students. The list is full.");
        }
    
        for (int i = 0; i < this.currentSize; i++) {
            if (this.students[i].getStudentID().equals(student.getStudentID())) {
                throw new IllegalArgumentException("Student with this ID already exists.");
            }
        }
        
    
        this.students[currentSize] = student; // Add student at the next available position
        this.currentSize++; // Increase the count of students
    }

    /**
     * Retrieves a student by their unique student ID.
     * @param studentID The unique ID of the student.
     * @return The Student object if found, otherwise null.
     */
    public Student getStudentByID(String studentID) {
        for (int i = 0; i < this.currentSize; i++) {
            if (this.students[i].getStudentID().equals(studentID)) {
                return this.students[i];
            }
        }
        return null;
    }

    /**
     * Calculates the average grade of all students in the class.
     * If any student has no grades available, they are skipped.
     * @return The class average grade.
     * @throws IllegalStateException if no valid grades are available for calculation.
     */
    public double calculateClassAverage() {
        double total = 0;  // Accumulator for total grades
        int count = 0;     // Counter for number of students with valid grades
        
        if (currentSize == 0) {
        	throw new IllegalStateException("There are no students in the sytsem");
        }
        else {

        	for (int i = 0; i < currentSize; i++) {
            	try {
                	total += students[i].calculateAverage();
                	count++;
            	} catch (IllegalStateException e) {
                	System.out.println("Skipping student " + students[i].getStudentID() + ": " + e.getMessage());
            	}
        	}        
        }
        return total/count;  // Return the calculated class average
    }

    /**
     * Returns the total number of students currently managed by the system.
     * @return The number of students in the list.
     */
    public int getTotalStudents() {
        return currentSize;
    }

}
