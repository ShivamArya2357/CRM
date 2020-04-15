package com.luv2code.testdb;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDatabaseConnection {

    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
        String userName = "springstudent";
        String password = "springstudent";
        try {
            System.out.println("Connecting to the database: " + dbUrl);
            Connection connection = DriverManager.getConnection(dbUrl, userName, password);
            System.out.println("Connection successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
