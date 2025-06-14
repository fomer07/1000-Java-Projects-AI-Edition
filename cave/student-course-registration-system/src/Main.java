import model.Course;
import model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {

    private static Map<String, Student> students = new HashMap<>();
    private static Map<String, Course> courses = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Hello, World!");

        loadCourses(); // static catalog
        showMainMenu();
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=== Student Course Registration System ===");
            System.out.println("1. Register Student");
            System.out.println("2. Login");
            System.out.println("3. View Course Catalog");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    registerStudent();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    viewCourseCatalog();
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }


    private static void registerStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();

        if (students.containsKey(id)) {
            System.out.println("Student ID already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        Student student = new Student(id, name, email, password);
        students.put(id, student);
        System.out.println("Registration successful!");
    }

    private static void login() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        Student student = students.get(id);

        if (student != null && student.getPassword().equals(password)) {
            System.out.println("Welcome, " + student.getName() + "!");
            studentMenu(student);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void viewCourseCatalog() {
        System.out.println("\n--- Course Catalog ---");
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }

    private static void loadCourses() {
        courses.put("CS101", new Course("CS101", "Intro to Programming", 3, "Dr. Smith", 30));
        courses.put("CS102", new Course("CS102", "Data Structures", 4, "Prof. Johnson", 25));
        courses.put("MATH201", new Course("MATH201", "Discrete Math", 3, "Dr. Allen", 20));
        courses.put("ENG101", new Course("ENG101", "Academic Writing", 2, "Dr. Davis", 40));
        courses.put("PHYS101", new Course("PHYS101", "General Physics", 4, "Prof. Brown", 20));
    }

    private static void studentMenu(Student student) {
        while (true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. View Enrolled Courses");
            System.out.println("2. Enroll in a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    viewEnrolledCourses(student);
                    break;
                case "2":
                    enrollInCourse(student);
                    break;
                case "3":
                    dropCourse(student);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void viewEnrolledCourses(Student student) {
        List<Course> enrolled = student.getEnrolledCourses();
        if (enrolled.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
        } else {
            System.out.println("\nYour Courses:");
            for (Course c : enrolled) {
                System.out.println(c);
            }
        }
    }

    private static void enrollInCourse(Student student) {
        viewCourseCatalog();

        System.out.print("Enter Course ID to enroll: ");
        String courseId = scanner.nextLine().trim();

        Course course = courses.get(courseId);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (!course.isAvailable()) {
            System.out.println("Course is full.");
            return;
        }

        if (student.getEnrolledCourses().contains(course)) {
            System.out.println("Already enrolled in this course.");
            return;
        }

        if (student.getEnrolledCourses().size() >= 5) {
            System.out.println("You cannot enroll in more than 5 courses.");
            return;
        }

        boolean success = student.enrollCourse(course);
        if (success) {
            course.enroll();
            System.out.println("Enrolled in " + course.getName() + " successfully!");
        } else {
            System.out.println("Enrollment failed.");
        }
    }

    private static void dropCourse(Student student) {
        List<Course> enrolled = student.getEnrolledCourses();
        if (enrolled.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
            return;
        }

        System.out.println("Your Enrolled Courses:");
        for (Course c : enrolled) {
            System.out.println(c);
        }

        System.out.print("Enter Course ID to drop: ");
        String courseId = scanner.nextLine().trim();

        Course course = courses.get(courseId);

        if (course == null || !student.getEnrolledCourses().contains(course)) {
            System.out.println("You are not enrolled in this course.");
            return;
        }

        boolean success = student.dropCourse(course);
        if (success) {
            course.drop();
            System.out.println("Dropped " + course.getName() + " successfully.");
        } else {
            System.out.println("Failed to drop course.");
        }
    }
}