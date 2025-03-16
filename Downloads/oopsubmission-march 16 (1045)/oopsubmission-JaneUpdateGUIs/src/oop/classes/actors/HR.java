package oop.classes.actors;

import java.util.*;
import oop.classes.empselfservice.LeaveRequest;
import oop.classes.enums.ApprovalStatus;
import oop.classes.management.AttendanceTracking;
import oop.classes.management.LeaveRequestManagement;

public class HR extends Employee implements AttendanceTracking, LeaveRequestManagement {
    private Map<Integer, LeaveRequest> leaveRequests = new HashMap<>();

    public HR(int employeeID, String firstName, String lastName, String email, String password, String role) {
        super(employeeID, firstName, lastName, email, password, role);
        this.leaveRequests = new HashMap<>();
    }

    @Override
    public List<LeaveRequest> getAllLeaveRequests() {
        return new ArrayList<>(leaveRequests.values()); // ✅ Fix: Implemented required method
    }

    //approve leaves
    @Override
    public boolean approveLeaveRequest(int leaveID) {
        System.out.println("HR approved leave ID: " + leaveID);
        return true;
    }

    //deny leaves
    @Override
    public boolean rejectLeaveRequest(int leaveID) {
        System.out.println("HR rejected leave request ID: " + leaveID);
        return true;
    }

    //approve attendance
    @Override
    public boolean approveAttendance(int attendanceID) {
        System.out.println("HR approved attendance ID: " + attendanceID);
        return true;
    }

    //deny attendance
    @Override
    public boolean denyAttendance(int attendanceID, String reason) {
        System.out.println("HR denied attendance ID: " + attendanceID + " - Reason: " + reason);
        return false;
        
    }
    
    public void addLeaveRequest(LeaveRequest request) {
    leaveRequests.put(request.getRequestId(), request); //  HR can track requests
    }
}
