/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ISMS.views;

import ISMS.models.*;
import ISMS.models.User;
import java.awt.CardLayout;
import java.sql.*;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 *
 * @author sachi
 */
public class Dashboardview extends javax.swing.JFrame {

    CardLayout layout;
    String username;
    User user;
    LocalDateTime outTime;
    
    public Dashboardview(String username,String usertype,User user) {
        initComponents();
        
        NavPanel.setVisible(false);
        layout = new CardLayout();
        this.username = username;
        this.user = user;
        currentUserSession();
        
        
        DisplayPanel.setLayout(layout);
        DisplayPanel.add("Home",new HomeView());
        DisplayPanel.add("Users",new UsersView());
        DisplayPanel.add("Product",new ProductView());
        DisplayPanel.add("Sale",new SalesView());
        DisplayPanel.add("Branch",new BranchView());
        DisplayPanel.add("Inventory",new InventoryView());
        DisplayPanel.add("UserLogs",new UserLogsVIew());
        DisplayPanel.add("Suppliers", new SuppliersView());
        
    }
    public void currentUserSession() {
        user.getName();
        LblName.setText(user.getName() + " ("+user.getUserType()+")");
    }

    public void addUserLogin(User user) {
        try {
            String query = "INSERT INTO userlogs (username, in_time, out_time) values(?,?,?)";
            Connection conn = Dbconnect.getConnection();
            PreparedStatement prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, user.getUsername());
            prepStatement.setString(2, user.getInTime());
            prepStatement.setString(3, user.getOutTime());

            prepStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        LblName = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        DisplayPanel = new javax.swing.JPanel();
        NavPanel = new javax.swing.JPanel();
        HomeButton = new javax.swing.JButton();
        BranchButton = new javax.swing.JButton();
        ProductButton = new javax.swing.JButton();
        SaleButton = new javax.swing.JButton();
        InventoryButton = new javax.swing.JButton();
        SuppliersButton = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        Users = new javax.swing.JButton();
        UserLogs = new javax.swing.JButton();
        MenuPanel1 = new javax.swing.JPanel();
        MenuButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1280, 780));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 780));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel4.setPreferredSize(new java.awt.Dimension(562, 70));

        LblName.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        LblName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ISMS/Resources/profile.gif"))); // NOI18N
        LblName.setText("User:");
        LblName.setToolTipText("");

        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ISMS/Resources/logout.gif"))); // NOI18N
        btnLogout.setContentAreaFilled(false);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Store Inventory System");
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(1280, 40));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(173, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addComponent(LblName, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblName, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 1050, 70));

        DisplayPanel.setBackground(new java.awt.Color(255, 255, 255));
        DisplayPanel.setBorder(new javax.swing.border.MatteBorder(null));
        DisplayPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DisplayPanel.setMaximumSize(new java.awt.Dimension(1050, 600));
        DisplayPanel.setMinimumSize(new java.awt.Dimension(1050, 600));
        DisplayPanel.setPreferredSize(new java.awt.Dimension(1050, 600));

        javax.swing.GroupLayout DisplayPanelLayout = new javax.swing.GroupLayout(DisplayPanel);
        DisplayPanel.setLayout(DisplayPanelLayout);
        DisplayPanelLayout.setHorizontalGroup(
            DisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1048, Short.MAX_VALUE)
        );
        DisplayPanelLayout.setVerticalGroup(
            DisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );

        jPanel1.add(DisplayPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        NavPanel.setBackground(new java.awt.Color(255, 255, 255));
        NavPanel.setBorder(new javax.swing.border.MatteBorder(null));
        NavPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        NavPanel.setMaximumSize(new java.awt.Dimension(189, 600));
        NavPanel.setMinimumSize(new java.awt.Dimension(189, 600));
        NavPanel.setPreferredSize(new java.awt.Dimension(189, 600));
        NavPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HomeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ISMS/Resources/home.gif"))); // NOI18N
        HomeButton.setContentAreaFilled(false);
        HomeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        HomeButton.setPreferredSize(new java.awt.Dimension(140, 30));
        HomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeButtonActionPerformed(evt);
            }
        });
        NavPanel.add(HomeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, 162, 37));

        BranchButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BranchButton.setText("Branches");
        BranchButton.setPreferredSize(new java.awt.Dimension(150, 30));
        BranchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BranchButtonActionPerformed(evt);
            }
        });
        NavPanel.add(BranchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 65, 162, 37));

        ProductButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ProductButton.setText("Products");
        ProductButton.setPreferredSize(new java.awt.Dimension(140, 30));
        ProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductButtonActionPerformed(evt);
            }
        });
        NavPanel.add(ProductButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 118, 162, 37));

        SaleButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SaleButton.setText("Sales");
        SaleButton.setPreferredSize(new java.awt.Dimension(140, 30));
        SaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaleButtonActionPerformed(evt);
            }
        });
        NavPanel.add(SaleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 170, 162, 37));

        InventoryButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        InventoryButton.setText("Inventory");
        InventoryButton.setPreferredSize(new java.awt.Dimension(140, 30));
        InventoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InventoryButtonActionPerformed(evt);
            }
        });
        NavPanel.add(InventoryButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 222, 162, 37));

        SuppliersButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        SuppliersButton.setText("Suppliers");
        SuppliersButton.setPreferredSize(new java.awt.Dimension(140, 30));
        SuppliersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuppliersButtonActionPerformed(evt);
            }
        });
        NavPanel.add(SuppliersButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 274, 162, 37));

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setText("jButton2");
        jButton8.setPreferredSize(new java.awt.Dimension(140, 30));
        NavPanel.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 326, 162, 37));

        Users.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Users.setText("Users");
        Users.setPreferredSize(new java.awt.Dimension(140, 30));
        Users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsersActionPerformed(evt);
            }
        });
        NavPanel.add(Users, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 378, 162, 37));

        UserLogs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UserLogs.setText("User Logs");
        UserLogs.setPreferredSize(new java.awt.Dimension(140, 30));
        UserLogs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserLogsActionPerformed(evt);
            }
        });
        NavPanel.add(UserLogs, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 430, 162, 37));

        jPanel1.add(NavPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 189, 600));

        MenuPanel1.setBackground(new java.awt.Color(255, 255, 255));
        MenuPanel1.setBorder(new javax.swing.border.MatteBorder(null));
        MenuPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MenuPanel1.setOpaque(false);
        MenuPanel1.setPreferredSize(new java.awt.Dimension(216, 72));

        MenuButton1.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        MenuButton1.setForeground(new java.awt.Color(255, 255, 255));
        MenuButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ISMS/Resources/menu.gif"))); // NOI18N
        MenuButton1.setContentAreaFilled(false);
        MenuButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuPanel1Layout = new javax.swing.GroupLayout(MenuPanel1);
        MenuPanel1.setLayout(MenuPanel1Layout);
        MenuPanel1Layout.setHorizontalGroup(
            MenuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        MenuPanel1Layout.setVerticalGroup(
            MenuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.add(MenuPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 189, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void UsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsersActionPerformed
        layout.show(DisplayPanel, "Users");
    }//GEN-LAST:event_UsersActionPerformed

    private void HomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeButtonActionPerformed
        layout.show(DisplayPanel, "Home");
    }//GEN-LAST:event_HomeButtonActionPerformed

    private void MenuButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuButton1ActionPerformed
        NavPanel.setVisible(!NavPanel.isVisible());
    }//GEN-LAST:event_MenuButton1ActionPerformed

    private void BranchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BranchButtonActionPerformed
        layout.show(DisplayPanel, "Branch");
    }//GEN-LAST:event_BranchButtonActionPerformed

    private void ProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductButtonActionPerformed
        layout.show(DisplayPanel, "Product");
    }//GEN-LAST:event_ProductButtonActionPerformed

    private void SaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaleButtonActionPerformed
        layout.show(DisplayPanel, "Sale");
    }//GEN-LAST:event_SaleButtonActionPerformed

    private void InventoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InventoryButtonActionPerformed
        layout.show(DisplayPanel, "Inventory");
    }//GEN-LAST:event_InventoryButtonActionPerformed

    private void UserLogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserLogsActionPerformed
        layout.show(DisplayPanel, "UserLogs");
    }//GEN-LAST:event_UserLogsActionPerformed

    private void SuppliersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuppliersButtonActionPerformed
        layout.show(DisplayPanel, "Suppliers");
    }//GEN-LAST:event_SuppliersButtonActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) 
        {
            outTime = LocalDateTime.now();
            user.setOutTime(String.valueOf(outTime));
            addUserLogin(user);
            this.dispose();
            LoginView logpage = new LoginView();
            logpage.setVisible(true);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Dashboardview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Dashboardview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Dashboardview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Dashboardview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Dashboardview().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BranchButton;
    private javax.swing.JPanel DisplayPanel;
    private javax.swing.JButton HomeButton;
    private javax.swing.JButton InventoryButton;
    private javax.swing.JLabel LblName;
    private javax.swing.JButton MenuButton1;
    private javax.swing.JPanel MenuPanel1;
    private javax.swing.JPanel NavPanel;
    private javax.swing.JButton ProductButton;
    private javax.swing.JButton SaleButton;
    private javax.swing.JButton SuppliersButton;
    private javax.swing.JButton UserLogs;
    private javax.swing.JButton Users;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
