import java.util.ArrayList;
import java.util.Scanner;


class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;
    int registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = 0;
    }

    public boolean isAvailable() {
        return registeredStudents < capacity;
    }

    public void registerStudent() {
        if (isAvailable()) {
            registeredStudents++;
        }
    }

    public void dropStudent() {
        if (registeredStudents > 0) {
            registeredStudents--;
        }
    }

    public String toString() {
        return courseCode + ": " + title + " (" + registeredStudents + "/" + capacity + " slots filled) - " + schedule;
    }
}


class Student {
    String studentID;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (course.isAvailable()) {
            course.registerStudent();
            registeredCourses.add(course);
            System.out.println("Successfully registered for " + course.title);
        } else {
            System.out.println("Course " + course.title + " is full.");
        }
    }

    public void dropCourse(String courseCode) {
        for (Course course : registeredCourses) {
            if (course.courseCode.equals(courseCode)) {
                course.dropStudent();
                registeredCourses.remove(course);
                System.out.println("Successfully dropped " + course.title);
                return;
            }
        }
        System.out.println("You are not registered for this course.");
    }

    public void listRegisteredCourses() {
        System.out.println("\nCourses registered by " + name + ":");
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            for (Course course : registeredCourses) {
                System.out.println(course);
            }
        }
    }
}

public class Task_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("CS101", "Introduction to Computer Science",
                "Basic concepts of computer science", 3, "Mon-Wed 10:00-11:30"));
        courses.add(new Course("MATH201", "Calculus I",
                "Differential and integral calculus", 2, "Tue-Thu 12:00-13:30"));
        courses.add(new Course("PHYS101", "General Physics I",
                "Fundamentals of physics", 2, "Mon-Wed 14:00-15:30"));

        // Sample student database
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("S001", "John Doe"));
        students.add(new Student("S002", "Jane Smith"));

        Student currentStudent = students.get(0); // Simulate the first student is logged in

        int choice;
        do {
            System.out.println("\n--- Course Registration System ---");
            System.out.println("1. List Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. List Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAvailableCourses(courses);
                    break;
                case 2:
                    registerForCourse(scanner, currentStudent, courses);
                    break;
                case 3:
                    dropCourse(scanner, currentStudent);
                    break;
                case 4:
                    currentStudent.listRegisteredCourses();
                    break;
                case 5:
                    System.out.println("Thank you for using the Course Registration System.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void listAvailableCourses(ArrayList<Course> courses) {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void registerForCourse(Scanner scanner, Student student, ArrayList<Course> courses) {
        System.out.print("Enter the course code to register: ");
        String courseCode = scanner.next();
        Course courseToRegister = findCourseByCode(courseCode, courses);
        if (courseToRegister != null) {
            student.registerCourse(courseToRegister);
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void dropCourse(Scanner scanner, Student student) {
        System.out.print("Enter the course code to drop: ");
        String courseCode = scanner.next();
        student.dropCourse(courseCode);
    }

    private static Course findCourseByCode(String courseCode, ArrayList<Course> courses) {
        for (Course course : courses) {
            if (course.courseCode.equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
}


