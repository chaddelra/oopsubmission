package oop.classes.management;

/**
 * SessionManager is used to store the logged-in user's Employee ID
 * so it can be accessed throughout the application.
 */
public class SessionManager {
    private static int loggedInEmployeeId;

    public static void setLoggedInEmployeeId(int employeeId) {
        loggedInEmployeeId = employeeId;
    }

    public static int getLoggedInEmployeeId() {
        return loggedInEmployeeId;
    }
}