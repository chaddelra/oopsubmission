
package gui;

import CSV.CSVDatabaseProcessor;
import oop.classes.management.UserAuthentication;
import javax.swing.*;
import oop.classes.actors.User;

/**
 * Login GUI
 * @author Admin
 */

public class Login extends javax.swing.JFrame {

    private final UserAuthentication userAuth;

    public Login() {
        CSVDatabaseProcessor csvProcessor = new CSVDatabaseProcessor();
        csvProcessor.loadUserCredentialData(); // Load user credentials
        csvProcessor.loadEmployeeCSVData(csvProcessor.getEmployeeDetailsFilePath()); // Load employee details
        userAuth = new UserAuthentication(csvProcessor);
        initComponents();
    }

    private void login() {
        String email = txtEmail.getText().trim();
        String password = new String(jPasswordField1.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both email and password", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate credentials and get the User object
        User user = userAuth.validateCredentials(email, password);

        if (user != null) {
            System.out.println("Login successful for user: " + user.getEmail());
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            redirectUserBasedOnRole(user);
        } else {
            System.out.println("Login failed for email: " + email);
            JOptionPane.showMessageDialog(this, "Invalid Email or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void redirectUserBasedOnRole(User user) {
        if (user == null) {
            JOptionPane.showMessageDialog(this, "Invalid user object.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String role = user.getRole();

        switch (role) {
            case "HR":
            case "EMPLOYEE":
            case "IT":
            case "IMMEDIATE_SUPERVISOR":
            case "ACCOUNTING":
                // Redirect to EmployeeSelfService page
                EmployeeSelfService employeeSelfService = new EmployeeSelfService(user);
                employeeSelfService.setVisible(true);
                this.dispose(); // Close the login window
                break;

            default:
                JOptionPane.showMessageDialog(this, "Unknown role: " + role, "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
    
    //Will change into this if each user role will have a landing page with both regular employee features and admin features based on their role
    /**
     * private void redirectUserBasedOnRole(User user) {
    if (user == null) {
        JOptionPane.showMessageDialog(this, "Invalid user object.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String role = user.getRole();

    switch (role) {
        case "HR":
            HRDashboard hrDashboard = new HRDashboard(user);
            hrDashboard.setVisible(true);
            break;
        
        case "IT":
            ITDashboard itDashboard = new ITDashboard(user);
            itDashboard.setVisible(true);
            break;
        
        case "EMPLOYEE":
            EmployeeSelfService employeeSelfService = new EmployeeSelfService(user);
            employeeSelfService.setVisible(true);
            break;
        
        case "IMMEDIATE_SUPERVISOR":
            SupervisorDashboard supervisorDashboard = new SupervisorDashboard(user);
            supervisorDashboard.setVisible(true);
            break;
        
        case "ACCOUNTING":
            AccountingDashboard accountingDashboard = new AccountingDashboard(user);
            accountingDashboard.setVisible(true);
            break;

        default:
            JOptionPane.showMessageDialog(this, "Unknown role: " + role, "Error", JOptionPane.ERROR_MESSAGE);
            return; // Prevents closing the login window if role is invalid
    }

    this.dispose(); // Close the login window after opening the correct dashboard
}
    */



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        showPassword = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/LOGIN (2).png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setText("LOGIN");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Email");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Password");

        showPassword.setText("Show password");
        showPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordActionPerformed(evt);
            }
        });

        jLabel5.setText("Forget password?");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(220, 95, 0));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jPasswordField1.setActionCommand("<Not Set>");
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(txtEmail)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(showPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                                .addComponent(jLabel5))
                            .addComponent(jPasswordField1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel2)
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showPassword)
                    .addComponent(jLabel5))
                .addGap(41, 41, 41)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
       
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        login();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void showPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordActionPerformed
    if (showPassword.isSelected()) {
        jPasswordField1.setEchoChar((char) 0); // Show password
    } else {
        jPasswordField1.setEchoChar('•'); // Hide password
    }
    }//GEN-LAST:event_showPasswordActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
       login();
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
       JOptionPane.showMessageDialog(this, "Reset password code has been sent to your company email!", 
                                  "Password Reset", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jLabel5MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JCheckBox showPassword;
    private javax.swing.JTextField txtEmail;
    // End of variables declaration//GEN-END:variables
}
