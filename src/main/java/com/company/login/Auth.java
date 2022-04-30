package com.company.login;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static com.company.dbhelper.DbConnection.getConnection;

public class Auth {
    // Authentication
    // 45 mins.
    // Create a table called users that has the follwing fields with the following properties
    // id -> serial, primary key
    // username -> varchar(255), unique
    // password -> varchar(255)
    // role -> varchar(255)

    // Write a method to sign up users on this student management platform,
    // that allows them to have a username and password and a role.
    // The role is to be one of two items, (user and admin) (validate it such that anything else that
    // is entered is not passed to the db.
    // Make the username unique as well on your table.(you can choose to make the username an email instead)

    private static Scanner scanner = new Scanner(System.in); // To receive input
    private static PreparedStatement ps;

    public static boolean signUp() {

        // Prompt the user to enter the values
        System.out.print("Enter a username: ");
        String username = scanner.next();

        System.out.print("Enter a password");
        String password = scanner.next();

        System.out.print("Enter a role(user, admin): ");
        String role = scanner.next();

        if (role.equalsIgnoreCase("user") || role.equalsIgnoreCase("admin")) {
            try {
                // INSERT INTO users(username, password, role) VALUES('username', 'password', 'role');
                ps = getConnection().prepareStatement("INSERT INTO users(username, password, role) VALUES('" + username + "', '" + password + "', '" + role + "')");
                ps.execute();
                return true;
            } catch (SQLException e) {
                System.out.println("Username is probably used already. Try another one.");
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("The " + role + " role is invalid. Accepted values are shown in the prompt.");
            return false;
        }

    }

}
