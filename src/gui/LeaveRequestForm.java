package gui;

import javax.swing.*;
import com.toedter.calendar.JDateChooser; // ✅ Import JDateChooser for date selection
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import oop.classes.actors.Employee;

public class LeaveRequestForm extends JFrame {
    private Employee loggedInEmployee; // ✅ Store logged-in Employee
    private JTextField txtEmployeeId;
    private JDateChooser dateChooser; // ✅ JDateChooser for date selection

    public LeaveRequestForm(Employee employee) {
        this.loggedInEmployee = employee; // ✅ Save the Employee object
        setTitle("Leave Request");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ✅ Close only this window
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel for UI elements
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Labels and Fields
        JLabel lblEmployeeId = new JLabel("Employee ID:");
        lblEmployeeId.setBounds(20, 20, 100, 25);
        panel.add(lblEmployeeId);

        txtEmployeeId = new JTextField();
        txtEmployeeId.setBounds(130, 20, 200, 25);
        txtEmployeeId.setEditable(false);
        panel.add(txtEmployeeId);

        JLabel lblLeaveType = new JLabel("Leave Type:");
        lblLeaveType.setBounds(20, 60, 100, 25);
        panel.add(lblLeaveType);

        String[] leaveTypes = {"Sick Leave", "Vacation Leave", "Emergency Leave"};
        JComboBox<String> cbLeaveType = new JComboBox<>(leaveTypes);
        cbLeaveType.setBounds(130, 60, 200, 25);
        panel.add(cbLeaveType);

        JLabel lblDate = new JLabel("Leave Date:");
        lblDate.setBounds(20, 100, 100, 25);
        panel.add(lblDate);

        dateChooser = new JDateChooser(); // ✅ Use JDateChooser instead of JTextField
        dateChooser.setBounds(130, 100, 200, 25);
        panel.add(dateChooser);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(130, 150, 100, 30);
        panel.add(btnSubmit);

        add(panel, BorderLayout.CENTER);

        // ✅ Auto-populate Employee ID from the Employee object
        if (loggedInEmployee != null) {
            txtEmployeeId.setText(String.valueOf(loggedInEmployee.getEmployeeID()));
        } else {
            txtEmployeeId.setText("Not Logged In");
        }

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String employeeId = txtEmployeeId.getText();
                String leaveType = (String) cbLeaveType.getSelectedItem();

                // ✅ Get the selected date from JDateChooser
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String leaveDate = (dateChooser.getDate() != null) ? sdf.format(dateChooser.getDate()) : "";

                if (employeeId.isEmpty() || leaveDate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Leave request submitted!\n" +
                            "Employee ID: " + employeeId + "\nLeave Type: " + leaveType + "\nDate: " + leaveDate,
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            }
        });

        setVisible(true);
    }

    // ✅ Corrected `main` method to pass an `Employee` object
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Employee testEmployee = new Employee(12345, "John", "Doe", "john.doe@example.com", "password", "EMPLOYEE");
            new LeaveRequestForm(testEmployee).setVisible(true);
        });
    }
}