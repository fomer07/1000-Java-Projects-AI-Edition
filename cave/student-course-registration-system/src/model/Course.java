package model;

public class Course {
    private String id;
    private String name;
    private int credit;
    private String instructor;
    private int capacity;
    private int enrolledCount;

    public Course(String id, String name, int credit, String instructor, int capacity) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.instructor = instructor;
        this.capacity = capacity;
        this.enrolledCount = 0;
    }

    public boolean isAvailable() {
        return enrolledCount < capacity;
    }

    public void enroll() {
        enrolledCount++;
    }

    public void drop(){
        enrolledCount--;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | Credit: %d | Instructor: %s | Capacity: %d/%d",
                id, name, credit, instructor, enrolledCount, capacity);
    }

}
