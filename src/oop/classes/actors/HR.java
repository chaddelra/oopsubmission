package oop.classes.actors;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
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

    @Override
    public boolean approveLeaveRequest(int leaveID) {
        if (!leaveRequests.containsKey(leaveID)) {
            System.out.println("HR: Leave request not found.");
            return false;
        }
        LeaveRequest request = leaveRequests.get(leaveID);
        request.setStatus(ApprovalStatus.APPROVED);
        System.out.println("HR approved leave ID: " + leaveID);
        return true;
    }

    @Override
    public boolean rejectLeaveRequest(int leaveID) {
        if (!leaveRequests.containsKey(leaveID)) {
            System.out.println("HR: Leave request not found.");
            return false;
        }
        leaveRequests.remove(leaveID);
        System.out.println("HR rejected leave request ID: " + leaveID);
        return true;
    }

    @Override
    public boolean approveAttendance(int attendanceID) {
        System.out.println("HR approved attendance ID: " + attendanceID);
        return true;
    }

    @Override
    public boolean denyAttendance(int attendanceID, String reason) {
        System.out.println("HR denied attendance ID: " + attendanceID + " - Reason: " + reason);
        return false;
        
    }
    
    public void addLeaveRequest(LeaveRequest request) {
    leaveRequests.put(request.getRequestId(), request); // 🔥 Now HR can track requests
    }
}
