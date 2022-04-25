package com.company;


import com.company.books.Book;
import com.company.dbhelper.DbConnection;
import com.company.menu.StudentMenu;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        StudentMenu.menu();

    }

    // Task: 20 min.
    // Create a BookController and also Create a Book Menu.
    // using the template shown and we'll demo them.

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
