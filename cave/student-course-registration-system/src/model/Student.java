package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String id;
    private String name;
    private String email;
    private String password;
    private List<Course> enrolledCourses;

    public Student(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.enrolledCourses = new ArrayList<>();
    }

    public boolean enrollCourse(Course course) {
        if (enrolledCourses.size() >= 5 || enrolledCourses.contains(course)) {
            return false;
        }
        enrolledCourses.add(course);
        return true;
    }

    public boolean dropCourse(Course course) {
        return enrolledCourses.remove(course);
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", id, name);
    }

}
