/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ISMS.views;

import ISMS.models.Dbconnect;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sachi
 */
public class UserLogsVIew extends javax.swing.JPanel {

    /**
     * Creates new form UserLogs
     */
    public UserLogsVIew() {
        initComponents();
        loadUserlogs("");
    }

    private void loadUserlogs(String keyword) {
        Connection conn = Dbconnect.getConnection();
        DefaultTableModel model = (DefaultTableModel) tbluserLogs.getModel();
        model.setRowCount(0);

        String query = "SELECT * FROM userlogs WHERE id LIKE ? OR username LIKE ? OR in_time LIKE ? OR out_time LIKE ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            String searchPattern = "%" + keyword + "%";
            pst.setString(1, searchPattern);
            pst.setString(2, searchPattern);
            pst.setString(3, searchPattern);
            pst.setString(4, searchPattern);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("in_time"),
                    rs.getString("out_time")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error searching UserLogs: " + e.getMessage());
        }
    }

    public void deleteuserlogs(int id) {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this userlog?", "Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM userlogs WHERE id = ?";
            try (Connection conn = Dbconnect.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, id);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "userlog deleted.");
                loadUserlogs("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error deleting userlog: " + e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbluserLogs = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.MatteBorder(null));
        setMaximumSize(new java.awt.Dimension(1050, 570));
        setMinimumSize(new java.awt.Dimension(1050, 570));
        setPreferredSize(new java.awt.Dimension(1050, 570));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel1.setText("Users Logs:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 123, 36));

        jLabel7.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel7.setText("Search:");
        jLabel7.setMinimumSize(new java.awt.Dimension(90, 30));
        jLabel7.setPreferredSize(new java.awt.Dimension(90, 30));
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 54, -1));

        txtSearch.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        txtSearch.setMinimumSize(new java.awt.Dimension(250, 30));
        txtSearch.setPreferredSize(new java.awt.Dimension(250, 30));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, -1, -1));

        btnRefresh.setText("Refresh");
        add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 13, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setMaximumSize(new java.awt.Dimension(1030, 400));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1030, 400));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1030, 400));

        tbluserLogs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "In Time", "Out Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbluserLogs);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, -1, -1));

        btnDelete.setBackground(new java.awt.Color(204, 153, 0));
        btnDelete.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnDelete.setText("Delete Selected");
        btnDelete.setMaximumSize(new java.awt.Dimension(88, 30));
        btnDelete.setMinimumSize(new java.awt.Dimension(88, 30));
        btnDelete.setPreferredSize(new java.awt.Dimension(88, 30));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 450, 130, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        loadUserlogs(txtSearch.getText());
    }//GEN-LAST:event_txtSearchKeyPressed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tbluserLogs.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tbluserLogs.getValueAt(selectedRow, 0);
            deleteuserlogs(id);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbluserLogs;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
