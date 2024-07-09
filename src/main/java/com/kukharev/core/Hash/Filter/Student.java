/*
A Java program processes a list of students, it filters Student objects from a list of objects that should contain students who scored at least 60% in a given subject.
The Student class has the following fields: id (int), name (String) and grades (Map<String, Double> where key is subject and value is percentage score).
In the student list filtering method, returns a list of IDs that have been successfully checked in the given subject.
 */

package com.kukharev.core.Hash.Filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Student {
    // Student's ID
    int id;
    // Student's name
    String name;
    // Map of subject names to grades
    Map<String, Double> grades;

    /**
     * Constructor for creating a new Student object
     *
     * @param id the student's ID
     * @param name the student's name
     * @param grades a map of subject names to grades
     */
    public Student(int id, String name, Map<String, Double> grades) {
        this.id = id;
        this.name = name;
        this.grades = grades;
    }

    /**
     * Method to filter students based on their grades in a given subject
     *
     * @param students a list of Student objects
     * @param subject the subject to filter by
     * @return a list of IDs of students who scored at least 60% in the given subject
     */
    public static List<Integer> filterStudents(List<Student> students, String subject) {
        // Use Stream API to filter students
        return students.stream()
                // Filter students who scored at least 60% in the given subject
                .filter(s -> s.grades.getOrDefault(subject, 0.0) >= 60)
                // Map each student to their ID
                .map(s -> s.id)
                // Collect the IDs into a list
                .collect(Collectors.toList());
    }

    // Main method for example how to use the filterStudents method
    public static void main(String[] args) {
        // Create a list of Student objects
        List<Student> students = new ArrayList<>();

        // Create a map of grades for the first student
        Map<String, Double> grades1 = new HashMap<>();
        grades1.put("Math", 70.0);
        grades1.put("English", 80.0);
        grades1.put("History", 60.0);

        // Create a map of grades for the second student
        Map<String, Double> grades2 = new HashMap<>();
        grades2.put("Math", 90.0);
        grades2.put("English", 70.0);
        grades2.put("History", 50.0);

        // Create a map of grades for the third student
        Map<String, Double> grades3 = new HashMap<>();
        grades3.put("Math", 40.0);
        grades3.put("English", 60.0);
        grades3.put("History", 80.0);

        // Create Student objects
        Student student1 = new Student(1, "John", grades1);
        Student student2 = new Student(2, "Jane", grades2);
        Student student3 = new Student(3, "Bob", grades3);

        // Add students to the list
        students.add(student1);
        students.add(student2);
        students.add(student3);

        // Specify the subject to filter by
        String subject = "Math"; // or any other subject

        // Filter students who scored at least 60% in the given subject
        List<Integer> filteredStudentIds = filterStudents(students, subject);

        // Print the IDs of the filtered students
        System.out.println("Student IDs who scored at least 60% in " + subject + ": " + filteredStudentIds.toString());
    }
}