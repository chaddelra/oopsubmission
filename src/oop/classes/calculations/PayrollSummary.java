package oop.classes.calculations;
import oop.classes.actors.Employee;
import java.time.YearMonth;

/**
 * Calculates the overall payroll details = net pay
 * @author Admin
 */
public class PayrollSummary {
    private String employeeId; // Employee's unique ID
    private String employeeName; // Employee's full name
    private YearMonth payrollMonth; // Pay period (month and year)
    private double grossMonthlySalary; // Total salary before deductions
    private double netMonthlyPay; // Final salary after deductions
    private double sssDeduction; // SSS contribution deduction
    private double philHealthDeduction; // PhilHealth contribution deduction
    private double pagIbigDeduction; // Pag-IBIG fund deduction
    private double withholdingTax; // Withholding tax deduction
    private double totalDeductions; // Total of all deductions
    // Overtime pay multiplier for regular employees (Rank and File)
    private static final double REGULAR_OVERTIME_MULTIPLIER = 1.25;
    
    // Constructor initializes payroll details and calculates deductions
    public PayrollSummary(Employee employee) {
        this.employeeId = String.valueOf(employee.getEmployeeID()); // Map employeeID
        this.employeeName = employee.getFullName(); // Map full name
        this.grossMonthlySalary = employee.getBasicSalary(); // Map basic salary
        this.payrollMonth = YearMonth.now(); // Default to current month
        calculateDeductions();
        calculateNetPay();
    }
    
    // Constructor with payroll month specified
    public PayrollSummary(Employee employee, YearMonth payrollMonth) {
        this.employeeId = String.valueOf(employee.getEmployeeID());
        this.employeeName = employee.getFullName();
        this.grossMonthlySalary = employee.getBasicSalary();
        this.payrollMonth = payrollMonth;
        calculateDeductions();
        calculateNetPay();
    }
    
    // Getter for payroll month
    public YearMonth getPayrollMonth() {
        return payrollMonth;
    }
    
    // Setter for payroll month
    public void setPayrollMonth(YearMonth payrollMonth) {
        this.payrollMonth = payrollMonth;
    }
    
    // Calculates all mandatory deductions
    private void calculateDeductions() {
        this.sssDeduction = grossMonthlySalary * 0.045; // SSS (4.5% of salary)
        this.philHealthDeduction = grossMonthlySalary * 0.02; // PhilHealth (2% of salary)
        this.pagIbigDeduction = 100; // Fixed Pag-IBIG deduction
        this.withholdingTax = grossMonthlySalary * 0.10; // Withholding tax (10%)
        this.totalDeductions = sssDeduction + philHealthDeduction + pagIbigDeduction + withholdingTax;
    }
    
    // Computes net pay after all deductions
    private void calculateNetPay() {
        this.netMonthlyPay = grossMonthlySalary - totalDeductions;
    }
    
    // Calculate gross monthly salary based on attendance
    public void calculateGrossMonthlySalary(Employee employee, double hoursWorked, double overtimeHours) {
        double hourlyRate = employee.getHourlyRate();
        this.grossMonthlySalary = (hoursWorked * hourlyRate) + (overtimeHours * hourlyRate * REGULAR_OVERTIME_MULTIPLIER);
        calculateDeductions(); // Recalculate deductions
        calculateNetPay(); // Recalculate net pay
    }
    
    // Getters for accessing payroll details
    public String getEmployeeId() {
        return employeeId;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }
    
    public double getGrossMonthlySalary() {
        return grossMonthlySalary;
    }
    
    public double getNetMonthlyPay() {
        return netMonthlyPay;
    }
    
    public double getSssDeduction() {
        return sssDeduction;
    }
    
    public double getPhilHealthDeduction() {
        return philHealthDeduction;
    }
    
    public double getPagIbigDeduction() {
        return pagIbigDeduction;
    }
    
    public double getWithholdingTax() {
        return withholdingTax;
    }
    
    public double getTotalDeductions() {
        return totalDeductions;
    }
    
    // Returns a formatted summary of payroll details
    @Override
    public String toString() {
        return "PayrollSummary{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", payrollMonth=" + payrollMonth +
                ", grossMonthlySalary=" + grossMonthlySalary +
                ", netMonthlyPay=" + netMonthlyPay +
                ", sssDeduction=" + sssDeduction +
                ", philHealthDeduction=" + philHealthDeduction +
                ", pagIbigDeduction=" + pagIbigDeduction +
                ", withholdingTax=" + withholdingTax +
                ", totalDeductions=" + totalDeductions +
                '}';
    }
}