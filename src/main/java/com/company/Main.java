package com.company;


import com.company.books.Book;
import com.company.dbhelper.DbConnection;
import com.company.login.Auth;
import com.company.menu.StudentMenu;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // CRUD - Create, Read, Update and Delete

//        StudentMenu.menu();
        if (Auth.signUp()) {
            StudentMenu.menu();
        } else {
            System.out.println("Invalid login credentials");
        }

    }

    // Task: 20 min.
    // Create a BookController and also Create a Book Menu.
    // using the template shown and we'll demo them.

    // 15 mins:
    // Attempt to add 3 student's scores into the scores table. Create a method
    // called addStudentScores() and use that to perform the task.
    // HINT: Use the id of an existing student

    // 20 min
    //  Attempt to create a deleteScore() method in your Students controller
    // to delete a particular student's score by the id. It should delete the
    // entire row.

    public static List<String> filterLessThanFive(List<Book> books) {
        // an anonymous function - lambda
//        books.removeIf(book -> book.getPrice() < 5);
//        return books;

        // Define a list of string for the booknames
        List<String> bookNames = new ArrayList<>();

        // Use a loop to filter the books less than 5
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getPrice() < 5) books.remove(books.get(i));
        }

        // add each remaining book name to the bookNames list.
        books.forEach(book -> bookNames.add(book.getName()));

        // return the list of book names.
        return bookNames;
    }
}
