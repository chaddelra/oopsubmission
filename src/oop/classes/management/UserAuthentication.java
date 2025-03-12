/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.classes.management;

import CSV.CSVDatabaseProcessor;
import java.util.List;
import java.util.Map;
import oop.classes.actors.Accounting;
import oop.classes.actors.Employee;
import oop.classes.actors.HR;
import oop.classes.actors.IT;
import oop.classes.actors.ImmediateSupervisor;
import oop.classes.actors.User;


/**
 * This class authenticates the user credentials inputted in the log in
 * @author Admin
 */

    /**
     * Handles user authentication by validating credentials from a CSV database.
     * It determines the user's role and creates the appropriate user object.
     */
public class UserAuthentication {
   private final CSVDatabaseProcessor databaseProcessor;

    /**
     * Initializes the authentication system with a database processor.
     * @param databaseProcessor The CSV database handler.
     */
    public UserAuthentication(CSVDatabaseProcessor databaseProcessor) {
        this.databaseProcessor = databaseProcessor;
    }

     /**
     * Validates the given email and password against stored credentials.
     * @param email User's email.
     * @param password User's password.
     * @return A User object if authentication is successful; otherwise, null.
     */
    public User validateCredentials(String email, String password) {
        
        // Get all stored user credentials from the database
        List<Map<String, String>> userCredentialRecords = databaseProcessor.getAllUserCredentialRecords();
        
        // If no credentials exist, return null
        if (userCredentialRecords == null || userCredentialRecords.isEmpty()) {
            System.out.println("No user credentials found. Please check if the CSV file is loaded.");
            return null;
        }

        // Loop through the credentials and check if the input matches any stored record        
        for (Map<String, String> record : userCredentialRecords) {
            String storedEmail = record.get("Email");
            String storedPassword = record.get("Password");
        
        // Credentials match, retrieve employee details
            if (email.equals(storedEmail) && password.equals(storedPassword)) {
                int employeeID = Integer.parseInt(record.get("Employee ID"));
                return getUserByID(employeeID); // Retrieve the User object
            }
        }
        return null; // Return null if no matching credentials are found
    }

     /**
     * Retrieves an employee's details by their ID.
     * @param employeeID The employee's unique identifier.
     * @return A User object if the employee exists; otherwise, null.
     */
    private User getUserByID(int employeeID) {
        // Fetch the employee's record from the database
        Map<String, String> employeeRecord = databaseProcessor.getEmployeeRecordsByEmployeeId(String.valueOf(employeeID));

        if (employeeRecord != null) {
            return createUserFromRecord(employeeRecord); // Create the appropriate User object
        }
        return null; // Employee not found
    }

     /**
     * Creates a User object based on the employee record.
     * @param record The employee's data retrieved from the database.
     * @return A specific User object depending on their role.
     */
    private User createUserFromRecord(Map<String, String> record) {
        int employeeID = Integer.parseInt(record.get("Employee ID"));
        String firstName = record.get("First Name");
        String lastName = record.get("Last Name");
        String email = record.get("Email");
        String password = record.get("Password");
        String position = record.get("Position");

        // Determine the role based on the position
        String role = determineUserRole(position);

        // Create the appropriate User object based on the role
        switch (role) {
            case "HR":
                return new HR(employeeID, firstName, lastName, email, password, role);
            case "EMPLOYEE":
                return new Employee(employeeID, firstName, lastName, email, password, role);
            case "IT":
                return new IT(employeeID, firstName, lastName, email, password, role);
            case "IMMEDIATE_SUPERVISOR":
                return new ImmediateSupervisor(employeeID, firstName, lastName, email, password, role);
            case "ACCOUNTING":
                return new Accounting(employeeID, firstName, lastName, email, password, role);
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

     /**
     * Determines the user's role based on their job position.
     * @param position The job title of the user.
     * @return The role category (e.g., "HR", "EMPLOYEE", "IT", etc.).
     */
    private String determineUserRole(String position) {
        switch (position) {
            case "Chief Executive Officer":
            case "Chief Operating Officer":
            case "Chief Finance Officer":
            case "Chief Marketing Officer":
            case "Account Manager":
            case "Account Team Leader":
                return "IMMEDIATE_SUPERVISOR";

            case "IT Operations and Systems":
                return "IT";

            case "HR Manager":
            case "HR Team Leader":
            case "HR Rank and File":
                return "HR";

            case "Accounting Head":
            case "Payroll Manager":
            case "Payroll Team Leader":
            case "Payroll Rank and File":
                return "ACCOUNTING";

            case "Account Rank and File":
            case "Sales & Marketing":
            case "Supply Chain and Logistics":
            case "Customer Service and Relations":
                return "EMPLOYEE";

            default:
                throw new IllegalArgumentException("Unknown position: " + position);
        }
    }
}
