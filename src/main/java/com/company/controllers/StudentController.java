package com.company.controllers;


import com.company.objects.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.company.dbhelper.DbConnection.getConnection;

public class StudentController {

    // Initialize the scanner
    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    // Add student controller
    public static boolean addStudent() {
        // Prompt the user for data
        System.out.print("Enter the name of the student: ");
        String name = scanner.next();

        System.out.print("Enter the age of the student: ");
        int age = scanner.nextInt();

        try {
            // INSERT INTO students(name, age) VALUES('name', age);
            ps = getConnection().prepareStatement("INSERT INTO students(name, age) VALUES('" + name + "', " + age + ")");

            ps.execute(); // execute the sql command
            return true; // return true if successful
        } catch (SQLException e) {
            System.out.println("Database Error");
            return false;
        }
    }

    // Get student By Id controller
    public static Student getStudentById() {
        // Prompt the user to enter the id of the student they want
        // to retrieve
        System.out.print("Enter the id of the student: ");
        int id = scanner.nextInt();

        try {

            ps = getConnection().prepareStatement("SELECT * FROM students WHERE id=" + id);
            rs = ps.executeQuery();

            // Define variables to temporarily hold
            // each field in the result set.
            int studentId, age;
            String name;

            // Instantiate the student object to return at the end of the method
            // execution
            Student student = new Student();

            // Loop through the result set and add the necessary
            // values to the student object.
            while (rs.next()) {
                studentId = rs.getInt("id");
                name = rs.getString("name");
                age = rs.getInt("age");
                student.setName(name);
                student.setId(studentId);
                student.setAge(age);
            }

            return student;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void editStudent() {
        // Prompt the user to enter the id of the student they want
        // to retrieve
        System.out.print("Enter the id of the student: ");
        int id = scanner.nextInt();

        System.out.print("What field would you like to edit? (name, age) : ");
        String fieldToEdit = scanner.next();

        System.out.print("What value do you want to edit it to?: ");
        String update = scanner.next();

        try {
            ps = getConnection().prepareStatement("UPDATE students SET " + fieldToEdit + " = '" + update + "' WHERE id=" + id);
            ps.execute();
            System.out.println("Successfully updated student data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent() {
        // Prompt the user to enter the id of the student they want
        // to retrieve
        System.out.print("Enter the id of the student: ");
        int id = scanner.nextInt();

        try {
            // Because of the relationship with the scores table, we have to first delete the
            // students score before we delete the student's data.
            deleteScore(id);
            ps = getConnection().prepareStatement("DELETE FROM students WHERE id=" + id);
            ps.execute();
            System.out.println("Deleted Student and related scores successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean addStudentScores() {
        // Prompt the user for data
        System.out.print("Enter the id of the student: ");
        int id = scanner.nextInt();

        System.out.print("Enter the mathematics score of the student: ");
        int maths = scanner.nextInt();

        System.out.print("Enter the english score of the student: ");
        int english = scanner.nextInt();

        System.out.print("Enter the physics score of the student: ");
        int physics = scanner.nextInt();

        System.out.print("Enter the chemistry score of the student: ");
        int chem = scanner.nextInt();

        try {
            // INSERT INTO students(studentid, mathematics, english, physics, chemistry) VALUES(studentid, maths, english, physics, chem);
            ps = getConnection().prepareStatement("INSERT INTO scores(studentid, mathematics, english, physics, chemistry) " +
                    "VALUES(" + id + ", " + maths + ", " + english + ", " + physics + ", " + chem + ")");

            ps.execute(); // execute the sql command

            return true;

        } catch (SQLException e) {
            System.out.println("Database Error");
            return false;

        }
    }

    public static void editScore() {
        // Prompt the user to enter the id of the student they want
        // to retrieve
        System.out.print("Enter the id of the student: ");
        int id = scanner.nextInt();

        System.out.print("What field would you like to edit? (mathematics, english, physics, chemistry) : ");
        String fieldToEdit = scanner.next();

        System.out.print("What value do you want to edit it to?: ");
        int update = scanner.nextInt();

        try {
            ps = getConnection().prepareStatement("UPDATE scores SET " + fieldToEdit + " = " + update + " WHERE id=" + id);
            ps.execute();
            System.out.println("Successfully updated student data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteScore() {
        // Prompt the user to enter the id of the student they want
        // to retrieve
        System.out.print("Enter the id of the student: ");
        int id = scanner.nextInt();

        try {
            ps = getConnection().prepareStatement("DELETE FROM scores WHERE studentid=" + id);
            ps.execute();
            System.out.println("Successfully deleted student scores");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteScore(int id) {
        try {
            ps = getConnection().prepareStatement("DELETE FROM scores WHERE studentid=" + id);
            ps.execute();
            System.out.println("Succesfully deleted student scores");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
