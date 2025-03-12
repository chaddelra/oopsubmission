package oop.classes.actors;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import oop.classes.empselfservice.LeaveRequest;
import oop.classes.enums.ApprovalStatus;
import oop.classes.management.AttendanceTracking;
import oop.classes.management.LeaveRequestManagement;

public class ImmediateSupervisor extends Employee implements AttendanceTracking, LeaveRequestManagement {
    private Map<Integer, LeaveRequest> leaveRequests = new HashMap<>();

    // Constructor for login
    public ImmediateSupervisor(int employeeID, String firstName, String lastName, String email, String password, String role) {
        super(employeeID, firstName, lastName, email, password, role);
    }

    // Constructor with all parameters
    public ImmediateSupervisor(int employeeID, String firstName, String lastName, String address, String phoneNo,
                               String email, String password, String role, LocalDate birthday,
                               String sssNo, String philhealthNo, String pagibigNo,
                               String tinNo, String position, String department,
                               Employee supervisor, String empStatus,
                               double basicSalary, double riceSubsidy, double phoneAllowance,
                               double clothingAllowance, double grossSemiMthlyRate,
                               double hourlyRate, double vacationLeave, double sickLeave, double emergencyLeave,
                               double maternityLeave, double paternityLeave, double specialLeave) {
        super(employeeID, firstName, lastName, email, password, role,
                birthday, address, phoneNo, sssNo, philhealthNo, position,
                pagibigNo, empStatus, basicSalary, riceSubsidy, phoneAllowance,
                clothingAllowance, grossSemiMthlyRate, hourlyRate);
    }

    // ✅ View leave requests of employees under supervisor
    @Override
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequests.values().stream()
                .filter(request -> request.getStatus() == ApprovalStatus.PENDING_SUPERVISOR)
                .collect(Collectors.toList());
    }

    // ✅ Approve leave request
    @Override
    public boolean approveLeaveRequest(int leaveID) {
        if (!leaveRequests.containsKey(leaveID)) {
            System.out.println("Supervisor: Leave request not found.");
            return false;
        }
        LeaveRequest request = leaveRequests.get(leaveID);
        request.setStatus(ApprovalStatus.PENDING_HR);
        System.out.println("Supervisor approved leave ID: " + leaveID + ", sent to HR for final approval.");
        return true;
    }

    // ✅ Deny leave request
    @Override
    public boolean rejectLeaveRequest(int leaveID) {
        if (!leaveRequests.containsKey(leaveID)) {
            System.out.println("Supervisor: Leave request not found.");
            return false;
        }
        leaveRequests.remove(leaveID);
        System.out.println("Supervisor rejected leave request ID: " + leaveID);
        return true;
    }

    // ✅ Approve attendance
    @Override
    public boolean approveAttendance(int attendanceID) {
        System.out.println("Supervisor approved attendance ID: " + attendanceID);
        return true;
    }

    // ✅ Deny attendance
    @Override
    public boolean denyAttendance(int attendanceID, String reason) {
        System.out.println("Supervisor denied attendance ID: " + attendanceID + " - Reason: " + reason);
        return false;
    }
    public void addLeaveRequest(LeaveRequest request) {
    leaveRequests.put(request.getRequestId(), request); // 🔥 Supervisor tracks requests
}
}
