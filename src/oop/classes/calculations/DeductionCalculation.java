package oop.classes.calculations;

import CSV.CSVDatabaseProcessor;
import java.time.YearMonth;
import java.util.Map;
import java.util.Objects;

/**
 * Calculates each deduction type and sums the total deductions
 * Deduction types:
 * 1) Government Mandated Contributions: SSS, PhilHealth, Pag-Ibig, & Withholding Tax
 * 2) Late Log-in Deduction: late time-in/log-in; beyond the grace period set (8:10AM; 8:11AM and onwards = late)
 * @author Admin
 */
public class DeductionCalculation {
    /**
     * Calculates the total deductions for a given payroll month.
     *
     * @param salaryCalculation The SalaryCalculation instance to use for gross pay calculation.
     * @param payrollMonth The payroll period (month and year).
     * @param employeeId The employee ID.
     * @param csvProcessor The CSV processor to fetch employee and attendance records.
     * @return The total deductions for the payroll month.
     */
    public double calculateTotalDeductions(
            SalaryCalculation salaryCalculation,
            YearMonth payrollMonth,
            String employeeId,
            CSVDatabaseProcessor csvProcessor) {
        
        Objects.requireNonNull(salaryCalculation, "SalaryCalculation cannot be null.");
        Objects.requireNonNull(payrollMonth, "Payroll Month cannot be null.");
        Objects.requireNonNull(employeeId, "Employee ID cannot be null.");
        Objects.requireNonNull(csvProcessor, "CSV Processor cannot be null.");

        // Retrieve employee details
        Map<String, String> employeeData = csvProcessor.getEmployeeRecordsByEmployeeId(employeeId);
        if (employeeData == null) {
            throw new IllegalArgumentException("Employee data not found for ID: " + employeeId);
        }
        
        // Get employee position
        String position = employeeData.get("Position");
        if (position == null) {
            throw new IllegalArgumentException("Position is missing in employee data for ID: " + employeeId);
        }

        // Calculate gross pay for the payroll month
        double grossPay = salaryCalculation.calculateGrossMonthlySalary(employeeId, payrollMonth, csvProcessor);
        
        // Retrieve hourly rate
        String hourlyRateStr = employeeData.get("Hourly Rate");
        if (hourlyRateStr == null || hourlyRateStr.isEmpty()) {
            throw new IllegalArgumentException("Hourly Rate is missing for employee ID: " + employeeId);
        }
        
        double hourlyRate;
        try {
            hourlyRate = Double.parseDouble(hourlyRateStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Hourly Rate format: " + hourlyRateStr, e);
        }
        
        // Calculate total late hours
        double lateHours = csvProcessor.getTotalLateHours(employeeId, payrollMonth);
        
        // Calculate late deductions
        double lateDeduction = calculateLateDeductions(lateHours, hourlyRate);
        
        // Calculate total deductions
        double totalDeductions = lateDeduction;
        
        // Apply government deductions
        totalDeductions += calculateSSS(grossPay);
        totalDeductions += calculatePhilHealth(grossPay);
        totalDeductions += calculatePagibig(grossPay);
        totalDeductions += calculateTax(grossPay);
        
        return totalDeductions;
    }
    
    //SSS Matrix
    public double calculateSSS(double grossPay) {
    // For salary below 3,250
    if (grossPay < 3250.00) {
        return 135.00;
    }
    
    double[][] sssTable = {
        {3750.00, 157.50}, {4250.00, 180.00}, {4750.00, 202.50}, {5250.00, 225.00},
        {5750.00, 247.50}, {6250.00, 270.00}, {6750.00, 292.50}, {7250.00, 315.00}, 
        {7750.00, 337.50}, {8250.00, 360.00}, {8750.00, 382.50}, {9250.00, 405.00},
        {9750.00, 427.50}, {10250.00, 450.00}, {10750.00, 472.50}, {11250.00, 495.00},
        {11750.00, 517.50}, {12250.00, 540.00}, {12750.00, 562.50}, {13250.00, 585.00},
        {13750.00, 607.50}, {14250.00, 630.00}, {14750.00, 652.50}, {15250.00, 675.00},
        {15750.00, 697.50}, {16250.00, 720.00}, {16750.00, 742.50}, {17250.00, 765.00},
        {17750.00, 787.50}, {18250.00, 810.00}, {18750.00, 832.50}, {19250.00, 855.00},
        {19750.00, 877.50}, {20250.00, 900.00}, {20750.00, 922.50}, {21250.00, 945.00},
        {21750.00, 967.50}, {22250.00, 990.00}, {22750.00, 1012.50}, {23250.00, 1035.00},
        {23750.00, 1057.50}, {24250.00, 1080.00}, {24750.00, 1102.50}, {Double.MAX_VALUE, 1125.00}
    };
    
    for (double[] bracket : sssTable) {
        if (grossPay <= bracket[0]) {
            return bracket[1];
        }
    }
    
    // Should never reach here due to the Double.MAX_VALUE bracket
    return 1125.00;
}
    
    //PhilHealth Matrix
    public double calculatePhilHealth(double grossPay) {
    // Define the minimum and maximum salary brackets
    double minSalary = 10000.0;
    double maxSalary = 60000.0;

    // Apply the salary brackets
    double premiumSalary;
    if (grossPay <= minSalary) {
        premiumSalary = minSalary;
    } else if (grossPay >= maxSalary) {
        premiumSalary = maxSalary;
    } else {
        premiumSalary = grossPay;
    }

    // Calculate the total premium (3% of the premium salary)
    double totalPremium = premiumSalary * 0.03;

    // Employee share is 50% of the total premium
    return totalPremium / 2;
}
    
    //Pagibig Matrix
    public double calculatePagibig(double grossPay) {
    if (grossPay >= 1000 && grossPay <= 1500) {
        return grossPay * 0.01; // 1% contribution
    } else if (grossPay > 1500) {
        return grossPay * 0.02; // 2% contribution
    } else {
        return 0; // No contribution if salary is below 1,000
    }
}
    
    //Withholding Tax Matrix
    public double calculateTax(double grossPay) {
        if (grossPay <= 20832) return 0;
        if (grossPay <= 33333) return (grossPay - 20833) * 0.20;
        if (grossPay <= 66667) return 2500 + (grossPay - 33333) * 0.25;
        if (grossPay <= 166667) return 10833 + (grossPay - 66667) * 0.30;
        if (grossPay <= 666667) return 40833.33 + (grossPay - 166667) * 0.32;
        return 200833.33 + (grossPay - 666667) * 0.35;
    }

    public double calculateLateDeductions(double lateHours, double hourlyRate) {
        return lateHours * hourlyRate;
    }
}