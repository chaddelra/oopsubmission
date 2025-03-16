
package gui;


//Java Swing Components
import javax.swing.*; //note to self: * means wildcard

//imports all classes inside java.awt (like Font & BorderLayout)
import java.awt.*;

//Others for time/date and user class
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import oop.classes.actors.User;


//public class  extends javax.swing.JFrame { --> no need to specify javax.swing.* since it was already impored up there!
public class AdminHR extends JFrame {
    private User loggedInUser; // Store the logged-in user's data

    public AdminHR(User user) {
        this.loggedInUser = user; // Initialize the loggedInUser with the passed User object
        initComponents(); // Initialize the GUI components (IDE-generated)

        // Update the text of lblWelcomeMessage and jLabelEmpName
        updateLabels();
        
        //Update the dateTimejLabel to current date & time
        updateDateTime();
        
        setTitle("MotorPH Admin Dashboard | Welcome, " + user.getFirstName() + " " + user.getLastName() + "!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
    }

    private void updateLabels() {
        // Update lblWelcomeMessage with "User role name + Dashboard"
        lblWelcomeMessage.setText(loggedInUser.getRole() + " Dashboard");

        // Update jLabelEmpName with the user's first and last name
        jLabelEmpName.setText("Welcome, " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
    }

    private void updateDateTime() {
       Timer timer = new Timer();
       timer.scheduleAtFixedRate(new TimerTask() {
           @Override
           public void run() {
               SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
               String formattedDate = sdf.format(new Date());
               dateTimejLabel.setText(formattedDate);
           }
       }, 0, 1000); // Update every second
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        emppagemainPNL = new javax.swing.JPanel();
        emppagemenuPNL = new javax.swing.JPanel();
        specialaccessPNL = new javax.swing.JPanel();
        dateTimejLabel = new javax.swing.JLabel();
        employeepagenameLBL = new javax.swing.JLabel();
        logoutemppgPB = new javax.swing.JButton();
        aboutPB = new javax.swing.JButton();
        personaldetPB = new javax.swing.JButton();
        attendanceemppgPB = new javax.swing.JButton();
        payrollemppgPB = new javax.swing.JButton();
        requestleaveemppgPB = new javax.swing.JButton();
        redOrangeDesign = new javax.swing.JLabel();
        MotorPHLogo = new javax.swing.JLabel();
        leaveRequestManagement = new javax.swing.JButton();
        attendanceeTracking = new javax.swing.JButton();
        empManagement = new javax.swing.JButton();
        emppagemenuPNL1 = new javax.swing.JPanel();
        lblWelcomeMessage = new javax.swing.JLabel();
        jLabelEmpName = new javax.swing.JLabel();

        jPopupMenu1.setBackground(new java.awt.Color(0, 0, 0));
        jPopupMenu1.setToolTipText("MENU BAR");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 0));

        emppagemainPNL.setBackground(new java.awt.Color(255, 255, 255));
        emppagemainPNL.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        emppagemainPNL.setForeground(new java.awt.Color(51, 51, 51));
        emppagemainPNL.setToolTipText("");
        emppagemainPNL.setLayout(null);

        emppagemenuPNL.setBackground(new java.awt.Color(208, 12, 12));
        emppagemenuPNL.setForeground(new java.awt.Color(51, 51, 55));

        javax.swing.GroupLayout emppagemenuPNLLayout = new javax.swing.GroupLayout(emppagemenuPNL);
        emppagemenuPNL.setLayout(emppagemenuPNLLayout);
        emppagemenuPNLLayout.setHorizontalGroup(
            emppagemenuPNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        emppagemenuPNLLayout.setVerticalGroup(
            emppagemenuPNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        emppagemainPNL.add(emppagemenuPNL);
        emppagemenuPNL.setBounds(0, 0, 70, 520);

        specialaccessPNL.setBackground(new java.awt.Color(158, 12, 12));
        specialaccessPNL.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        specialaccessPNL.setForeground(new java.awt.Color(51, 51, 51));

        dateTimejLabel.setBackground(new java.awt.Color(0, 0, 0));
        dateTimejLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dateTimejLabel.setForeground(new java.awt.Color(255, 204, 204));
        dateTimejLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateTimejLabel.setText("MM/DD/YYYY HH:MM:00");

        employeepagenameLBL.setBackground(new java.awt.Color(153, 0, 51));
        employeepagenameLBL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        employeepagenameLBL.setForeground(new java.awt.Color(255, 204, 204));
        employeepagenameLBL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        employeepagenameLBL.setText("Admin User");
        employeepagenameLBL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        logoutemppgPB.setBackground(new java.awt.Color(128, 12, 12));
        logoutemppgPB.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        logoutemppgPB.setForeground(new java.awt.Color(255, 255, 255));
        logoutemppgPB.setText("Log out");
        logoutemppgPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutemppgPBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout specialaccessPNLLayout = new javax.swing.GroupLayout(specialaccessPNL);
        specialaccessPNL.setLayout(specialaccessPNLLayout);
        specialaccessPNLLayout.setHorizontalGroup(
            specialaccessPNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, specialaccessPNLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(employeepagenameLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(dateTimejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(logoutemppgPB)
                .addGap(23, 23, 23))
        );
        specialaccessPNLLayout.setVerticalGroup(
            specialaccessPNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(specialaccessPNLLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(specialaccessPNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateTimejLabel)
                    .addComponent(employeepagenameLBL)
                    .addComponent(logoutemppgPB))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        employeepagenameLBL.getAccessibleContext().setAccessibleName("");

        emppagemainPNL.add(specialaccessPNL);
        specialaccessPNL.setBounds(390, 430, 540, 40);

        aboutPB.setBackground(new java.awt.Color(153, 0, 0));
        aboutPB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        aboutPB.setForeground(new java.awt.Color(255, 255, 255));
        aboutPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/COMPANY 100 X 100.png"))); // NOI18N
        aboutPB.setText("About");
        aboutPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutPBActionPerformed(evt);
            }
        });
        emppagemainPNL.add(aboutPB);
        aboutPB.setBounds(190, 140, 200, 80);

        personaldetPB.setBackground(new java.awt.Color(153, 0, 0));
        personaldetPB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        personaldetPB.setForeground(new java.awt.Color(255, 255, 255));
        personaldetPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/DETAILS 64 X 64.png"))); // NOI18N
        personaldetPB.setText("View Details");
        personaldetPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personaldetPBActionPerformed(evt);
            }
        });
        emppagemainPNL.add(personaldetPB);
        personaldetPB.setBounds(410, 140, 200, 80);

        attendanceemppgPB.setBackground(new java.awt.Color(153, 0, 0));
        attendanceemppgPB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        attendanceemppgPB.setForeground(new java.awt.Color(255, 255, 255));
        attendanceemppgPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/availability.png"))); // NOI18N
        attendanceemppgPB.setText("View Attendance");
        attendanceemppgPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendanceemppgPBActionPerformed(evt);
            }
        });
        emppagemainPNL.add(attendanceemppgPB);
        attendanceemppgPB.setBounds(190, 230, 200, 80);

        payrollemppgPB.setBackground(new java.awt.Color(153, 0, 0));
        payrollemppgPB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        payrollemppgPB.setForeground(new java.awt.Color(255, 255, 255));
        payrollemppgPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/PAYROLL 64 X 64.png"))); // NOI18N
        payrollemppgPB.setText("View Payroll");
        payrollemppgPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payrollemppgPBActionPerformed(evt);
            }
        });
        emppagemainPNL.add(payrollemppgPB);
        payrollemppgPB.setBounds(410, 230, 200, 80);

        requestleaveemppgPB.setBackground(new java.awt.Color(153, 0, 0));
        requestleaveemppgPB.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        requestleaveemppgPB.setForeground(new java.awt.Color(255, 255, 255));
        requestleaveemppgPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/REQUEST LEAVE 64 X 64.png"))); // NOI18N
        requestleaveemppgPB.setText("Request Leave");
        requestleaveemppgPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestleaveemppgPBActionPerformed(evt);
            }
        });
        emppagemainPNL.add(requestleaveemppgPB);
        requestleaveemppgPB.setBounds(410, 320, 200, 80);

        redOrangeDesign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/Red-Orange Design.png"))); // NOI18N
        redOrangeDesign.setText("RedOrangeDesign");
        redOrangeDesign.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        redOrangeDesign.setPreferredSize(new java.awt.Dimension(725, 500));
        emppagemainPNL.add(redOrangeDesign);
        redOrangeDesign.setBounds(-10, -20, 510, 500);

        MotorPHLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/MotorLogo 81x84.png"))); // NOI18N
        emppagemainPNL.add(MotorPHLogo);
        MotorPHLogo.setBounds(790, 30, 100, 90);

        leaveRequestManagement.setBackground(new java.awt.Color(153, 0, 0));
        leaveRequestManagement.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        leaveRequestManagement.setForeground(new java.awt.Color(255, 255, 255));
        leaveRequestManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/leave.png"))); // NOI18N
        leaveRequestManagement.setText("Leave Request Management");
        leaveRequestManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveRequestManagementActionPerformed(evt);
            }
        });
        emppagemainPNL.add(leaveRequestManagement);
        leaveRequestManagement.setBounds(630, 320, 270, 80);

        attendanceeTracking.setBackground(new java.awt.Color(153, 0, 0));
        attendanceeTracking.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        attendanceeTracking.setForeground(new java.awt.Color(255, 255, 255));
        attendanceeTracking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/ATTENDANCE 64 X 64.png"))); // NOI18N
        attendanceeTracking.setText("Attendance Tracking");
        attendanceeTracking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendanceeTrackingActionPerformed(evt);
            }
        });
        emppagemainPNL.add(attendanceeTracking);
        attendanceeTracking.setBounds(630, 230, 270, 80);

        empManagement.setBackground(new java.awt.Color(153, 0, 0));
        empManagement.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        empManagement.setForeground(new java.awt.Color(255, 255, 255));
        empManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/management.png"))); // NOI18N
        empManagement.setText("Employee Management");
        empManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empManagementActionPerformed(evt);
            }
        });
        emppagemainPNL.add(empManagement);
        empManagement.setBounds(630, 140, 270, 80);

        emppagemenuPNL1.setBackground(new java.awt.Color(188, 12, 12));
        emppagemenuPNL1.setForeground(new java.awt.Color(51, 51, 55));

        javax.swing.GroupLayout emppagemenuPNL1Layout = new javax.swing.GroupLayout(emppagemenuPNL1);
        emppagemenuPNL1.setLayout(emppagemenuPNL1Layout);
        emppagemenuPNL1Layout.setHorizontalGroup(
            emppagemenuPNL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        emppagemenuPNL1Layout.setVerticalGroup(
            emppagemenuPNL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        emppagemainPNL.add(emppagemenuPNL1);
        emppagemenuPNL1.setBounds(110, 0, 70, 480);

        lblWelcomeMessage.setBackground(new java.awt.Color(0, 0, 51));
        lblWelcomeMessage.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblWelcomeMessage.setForeground(new java.awt.Color(51, 0, 0));
        lblWelcomeMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWelcomeMessage.setText("Admin Dashboard");
        lblWelcomeMessage.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblWelcomeMessage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblWelcomeMessage.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        emppagemainPNL.add(lblWelcomeMessage);
        lblWelcomeMessage.setBounds(210, 40, 600, 60);
        lblWelcomeMessage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Top, Left, Bottom, Right padding

        jLabelEmpName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelEmpName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEmpName.setText("Employee Name");
        jLabelEmpName.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabelEmpName.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        emppagemainPNL.add(jLabelEmpName);
        jLabelEmpName.setBounds(220, 100, 570, 25);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(emppagemainPNL, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(emppagemainPNL, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutemppgPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutemppgPBActionPerformed
    int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
    if (response == JOptionPane.YES_OPTION) {
        new Login().setVisible(true);
        this.dispose();
    }
  
    }//GEN-LAST:event_logoutemppgPBActionPerformed

    private void aboutPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutPBActionPerformed
    new AboutPage(loggedInUser).setVisible(true);
    this.dispose(); // Close the current page
    }//GEN-LAST:event_aboutPBActionPerformed

    private void personaldetPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personaldetPBActionPerformed
    // Pass the logged-in user to PersonalDetailsPage
    PersonalDetailsPage detailsPage = new PersonalDetailsPage(loggedInUser);
    detailsPage.setVisible(true);
    this.dispose(); // Close the current page
    }//GEN-LAST:event_personaldetPBActionPerformed

    private void attendanceemppgPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendanceemppgPBActionPerformed
    // Pass the logged-in user to AttendanceDetailsGUI
    AttendanceDetailsGUI attendanceDetails = new AttendanceDetailsGUI(loggedInUser);
    attendanceDetails.setVisible(true);
    this.dispose(); // Close the current window
    }//GEN-LAST:event_attendanceemppgPBActionPerformed

    private void payrollemppgPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payrollemppgPBActionPerformed
    // Create a new ViewPayslip instance and pass the logged in user
    ViewPayslip payslipView = new ViewPayslip(loggedInUser);
    payslipView.setVisible(true);
    this.dispose(); // Close the current window
    }//GEN-LAST:event_payrollemppgPBActionPerformed

    private void requestleaveemppgPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestleaveemppgPBActionPerformed
    new LeaveRequest().setVisible(true);
    this.dispose(); // Close the current AdminHR page 
    }//GEN-LAST:event_requestleaveemppgPBActionPerformed

    private void leaveRequestManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveRequestManagementActionPerformed
    new LeaveRequest(loggedInUser).setVisible(true);
    this.dispose(); // Close the current AdminHR page 
    }//GEN-LAST:event_leaveRequestManagementActionPerformed

    private void attendanceeTrackingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendanceeTrackingActionPerformed
    // Pass parameters of logeedInUser and allows for polymorphic behavior (HR & Imemdiate Supervisor)
    new AttendanceTrackingGUI(loggedInUser).setVisible(true);
        this.dispose(); // Close the current AdminHR page 
    }//GEN-LAST:event_attendanceeTrackingActionPerformed

    private void empManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empManagementActionPerformed
    // Pass the user's full name, employee ID, and role
    new EmployeeManagement(loggedInUser.getFirstName() + " " + loggedInUser.getLastName(), 
                          loggedInUser.getEmployeeID(), 
                          loggedInUser.getRole()).setVisible(true);
            this.dispose();// Close the current AdminHR page 
    }//GEN-LAST:event_empManagementActionPerformed

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
        /* Set the Metal look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    
  
    
    
    }

     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MotorPHLogo;
    private javax.swing.JButton aboutPB;
    private javax.swing.JButton attendanceeTracking;
    private javax.swing.JButton attendanceemppgPB;
    private javax.swing.JLabel dateTimejLabel;
    private javax.swing.JButton empManagement;
    private javax.swing.JLabel employeepagenameLBL;
    private javax.swing.JPanel emppagemainPNL;
    private javax.swing.JPanel emppagemenuPNL;
    private javax.swing.JPanel emppagemenuPNL1;
    private javax.swing.JLabel jLabelEmpName;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel lblWelcomeMessage;
    private javax.swing.JButton leaveRequestManagement;
    private javax.swing.JButton logoutemppgPB;
    private javax.swing.JButton payrollemppgPB;
    private javax.swing.JButton personaldetPB;
    private javax.swing.JLabel redOrangeDesign;
    private javax.swing.JButton requestleaveemppgPB;
    private javax.swing.JPanel specialaccessPNL;
    // End of variables declaration//GEN-END:variables
}
