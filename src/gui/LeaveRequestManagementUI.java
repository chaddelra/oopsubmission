
package gui;

import javax.swing.*;

public class LeaveRequestManagementUI extends JFrame {
    public LeaveRequestManagementUI() {
        setTitle("Leave Request Management");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblRequestList = new JLabel("Pending Leave Requests:");
        lblRequestList.setBounds(20, 20, 200, 25);
        panel.add(lblRequestList);

        String[] columnNames = {"Employee ID", "Leave Type", "Date", "Status"};
        Object[][] data = {
            {"1001", "Sick Leave", "2025-03-15", "Pending"},
            {"1002", "Vacation Leave", "2025-03-20", "Pending"},
            {"1003", "Emergency Leave", "2025-03-18", "Pending"}
        };

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 50, 440, 100);
        panel.add(scrollPane);

        JButton btnApprove = new JButton("Approve");
        btnApprove.setBounds(100, 180, 100, 30);
        panel.add(btnApprove);

        JButton btnDeny = new JButton("Deny");
        btnDeny.setBounds(250, 180, 100, 30);
        panel.add(btnDeny);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LeaveRequestManagementUI().setVisible(true);
        });
    }
}
