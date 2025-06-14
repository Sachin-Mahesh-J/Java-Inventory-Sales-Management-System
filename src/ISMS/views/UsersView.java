package ISMS.views;

import ISMS.models.Hashpassword;
import ISMS.models.Dbconnect;
import ISMS.models.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UsersView extends javax.swing.JPanel {

    private int selectedUserId = -1;

    public UsersView() {
        initComponents();
        loadBranches();
        loadUserData("");
        Userstable.getColumnModel().getColumn(7).setMinWidth(0);
        Userstable.getColumnModel().getColumn(7).setMaxWidth(0);
        Userstable.getColumnModel().getColumn(7).setWidth(0);

//        Userstable.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//            }
//        });
    }

    private void loadUserData(String keyword) {
        DefaultTableModel model = (DefaultTableModel) Userstable.getModel();
        model.setRowCount(0);

        String sql = "SELECT u.id, u.username, u.name, u.phone, u.usertype, b.name AS branch_name, u.branch_id, u.created_at "
                + "FROM users u LEFT JOIN branch b ON u.branch_id = b.branch_id";

        boolean hasSearch = keyword != null && !keyword.isEmpty();

        if (hasSearch) {
            sql += " WHERE u.name LIKE ? OR u.username LIKE ? OR u.phone LIKE ? OR b.name LIKE ?";
        }

        try (Connection con = Dbconnect.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

            if (hasSearch) {
                String likeKeyword = "%" + keyword + "%";
                pst.setString(1, likeKeyword);
                pst.setString(2, likeKeyword);
                pst.setString(3, likeKeyword);
                pst.setString(3, likeKeyword);
            }

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("username"),
                    rs.getString("phone"),
                    rs.getString("branch_name"),
                    rs.getString("usertype"),
                    rs.getTimestamp("created_at"),
                    rs.getInt("branch_id")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading users: " + e.getMessage());
        }
    }

    private void loadBranches() {
        try (Connection con = Dbconnect.getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT branch_id, name FROM branch")) {

            cmbBranch.removeAllItems();
            cmbBranch.addItem(new ComboItem(0, "Select a branch"));
            while (rs.next()) {
                int id = rs.getInt("branch_id");
                String name = rs.getString("name");

                ComboItem item = new ComboItem(id, name);
                cmbBranch.addItem(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading branches: " + ex.getMessage());
        }
    }

    private void addUser() {
        String name = txtName.getText().trim();
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        String phone = txtContact.getText().trim();
        String userType = cmbUsertype.getSelectedItem().toString();

        // Validate required fields
        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields.");
            return;
        }

        // Validate password length
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long.");
            return;
        }

        // Validate phone number
        if (!phone.matches("\\d{10,15}")) {
            JOptionPane.showMessageDialog(this, "Phone number must be 10–15 digits.");
            return;
        }

        // Validate branch
        ComboItem selectedBranch = (ComboItem) cmbBranch.getSelectedItem();
        if (selectedBranch == null || selectedBranch.getId() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a valid branch.");
            return;
        }
        String usertype = cmbUsertype.getSelectedItem().toString();

        if (usertype.equals("Select Usertype")) {
            JOptionPane.showMessageDialog(this, "Please select a valid user type.");
            return;
        }
        String hashpassword = Hashpassword.hashPassword(password);

        try (Connection conn = Dbconnect.getConnection()) {
            // Check if username already exists
            PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ?");
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "Username already exists. Please choose another.");
                return;
            }

            // Insert new user
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO users (name, username, password, phone, usertype, branch_id) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, username);
            stmt.setString(3, hashpassword);
            stmt.setString(4, phone);
            stmt.setString(5, userType);
            stmt.setInt(6, selectedBranch.getId());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "User added successfully.");
                clearFormFields();
                loadUserData("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add user.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

    private void updateUser() {
        if (selectedUserId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to update.");
            return;
        }

        String name = txtName.getText();
        String username = txtUsername.getText();
        String password = Hashpassword.hashPassword(new String(txtPassword.getPassword()));
        String phone = txtContact.getText();
        String userType = cmbUsertype.getSelectedItem().toString();
        ComboItem selectedBranch = (ComboItem) cmbBranch.getSelectedItem();

        // Validate input
        if (name.isEmpty() || username.isEmpty() || phone.isEmpty() || userType.equals("Select Usertype")) {
            JOptionPane.showMessageDialog(this, "Please fill all fields correctly.");
            return;
        }

        try (Connection conn = Dbconnect.getConnection()) {
            String sql;
            PreparedStatement stmt;

            if (password.isEmpty()) {
                // Do not update password
                sql = "UPDATE users SET name = ?, username = ?, phone = ?, usertype = ?, branch_id = ? WHERE id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, username);
                stmt.setString(3, phone);
                stmt.setString(4, userType);
                stmt.setInt(5, selectedBranch.getId());
                stmt.setInt(6, selectedUserId);
            } else {
                // Update with new password
                sql = "UPDATE users SET name = ?, username = ?, password = ?, phone = ?, usertype = ?, branch_id = ? WHERE id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, username);
                stmt.setString(3, password);
                stmt.setString(4, phone);
                stmt.setString(5, userType);
                stmt.setInt(6, selectedBranch.getId());
                stmt.setInt(7, selectedUserId);
            }

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "User updated successfully.");
                clearFormFields();
                loadUserData("");
                selectedUserId = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Update failed.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

    private void clearFormFields() {
        txtUsername.setText("");
        txtName.setText("");
        txtPassword.setText("");
        txtContact.setText("");
        cmbBranch.setSelectedIndex(0);
        cmbUsertype.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbUsertype = new javax.swing.JComboBox<>();
        btnAdduser = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        cmbBranch = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Userstable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.MatteBorder(null));
        setMaximumSize(new java.awt.Dimension(1050, 570));
        setMinimumSize(new java.awt.Dimension(1050, 570));
        setPreferredSize(new java.awt.Dimension(1050, 570));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel1.setText("Users:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 123, 36));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add a New User", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Serif", 1, 18))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(320, 463));
        jPanel1.setMinimumSize(new java.awt.Dimension(320, 463));
        jPanel1.setPreferredSize(new java.awt.Dimension(320, 463));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel2.setText("Name:");
        jLabel2.setMaximumSize(new java.awt.Dimension(80, 30));
        jLabel2.setMinimumSize(new java.awt.Dimension(80, 30));
        jLabel2.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 35, -1, -1));

        txtName.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        txtName.setMaximumSize(new java.awt.Dimension(180, 30));
        txtName.setMinimumSize(new java.awt.Dimension(180, 30));
        txtName.setPreferredSize(new java.awt.Dimension(180, 30));
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 35, -1, -1));

        txtUsername.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        txtUsername.setMaximumSize(new java.awt.Dimension(180, 30));
        txtUsername.setMinimumSize(new java.awt.Dimension(180, 30));
        txtUsername.setPreferredSize(new java.awt.Dimension(180, 30));
        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 75, -1, -1));

        jLabel3.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel3.setText("Username:");
        jLabel3.setMaximumSize(new java.awt.Dimension(80, 30));
        jLabel3.setMinimumSize(new java.awt.Dimension(80, 30));
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 75, -1, -1));

        jLabel4.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel4.setText("Password:");
        jLabel4.setMaximumSize(new java.awt.Dimension(80, 30));
        jLabel4.setMinimumSize(new java.awt.Dimension(80, 30));
        jLabel4.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 115, -1, -1));

        txtContact.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        txtContact.setMaximumSize(new java.awt.Dimension(180, 30));
        txtContact.setMinimumSize(new java.awt.Dimension(180, 30));
        txtContact.setPreferredSize(new java.awt.Dimension(180, 30));
        jPanel1.add(txtContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 155, -1, -1));

        jLabel5.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel5.setText("ContactNo:");
        jLabel5.setMaximumSize(new java.awt.Dimension(80, 30));
        jLabel5.setMinimumSize(new java.awt.Dimension(80, 30));
        jLabel5.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 155, -1, -1));

        jLabel6.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel6.setText("Usertype:");
        jLabel6.setMaximumSize(new java.awt.Dimension(80, 30));
        jLabel6.setMinimumSize(new java.awt.Dimension(80, 30));
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 235, -1, -1));

        cmbUsertype.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        cmbUsertype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Usertype", "ADMIN", "MANAGER", "STAFF" }));
        cmbUsertype.setMaximumSize(new java.awt.Dimension(180, 30));
        cmbUsertype.setMinimumSize(new java.awt.Dimension(180, 30));
        cmbUsertype.setPreferredSize(new java.awt.Dimension(180, 30));
        jPanel1.add(cmbUsertype, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 235, -1, -1));

        btnAdduser.setBackground(new java.awt.Color(0, 204, 204));
        btnAdduser.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnAdduser.setText("Add");
        btnAdduser.setMaximumSize(new java.awt.Dimension(70, 30));
        btnAdduser.setMinimumSize(new java.awt.Dimension(70, 30));
        btnAdduser.setPreferredSize(new java.awt.Dimension(70, 30));
        btnAdduser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdduserActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdduser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        btnUpdate.setBackground(new java.awt.Color(0, 153, 51));
        btnUpdate.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setMaximumSize(new java.awt.Dimension(70, 30));
        btnUpdate.setMinimumSize(new java.awt.Dimension(70, 30));
        btnUpdate.setPreferredSize(new java.awt.Dimension(70, 30));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 80, -1));

        btnClear.setBackground(new java.awt.Color(255, 255, 204));
        btnClear.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnClear.setText("Clear");
        btnClear.setMaximumSize(new java.awt.Dimension(70, 30));
        btnClear.setMinimumSize(new java.awt.Dimension(70, 30));
        btnClear.setPreferredSize(new java.awt.Dimension(70, 30));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, -1, -1));

        txtPassword.setMaximumSize(new java.awt.Dimension(180, 30));
        txtPassword.setMinimumSize(new java.awt.Dimension(180, 30));
        txtPassword.setPreferredSize(new java.awt.Dimension(180, 30));
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 115, -1, -1));

        cmbBranch.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        cmbBranch.setMaximumSize(new java.awt.Dimension(180, 30));
        cmbBranch.setMinimumSize(new java.awt.Dimension(180, 30));
        cmbBranch.setPreferredSize(new java.awt.Dimension(180, 30));
        jPanel1.add(cmbBranch, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 195, -1, -1));

        jLabel8.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel8.setText("Branch:");
        jLabel8.setMaximumSize(new java.awt.Dimension(80, 30));
        jLabel8.setMinimumSize(new java.awt.Dimension(80, 30));
        jLabel8.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 195, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 320, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setMaximumSize(new java.awt.Dimension(701, 392));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(701, 392));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(701, 392));

        Userstable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Username", "Contact Number", "Branch", "User Type", "Created date", "BranchID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Userstable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserstableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Userstable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 45, -1, -1));

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
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 13, -1, -1));

        btnDelete.setBackground(new java.awt.Color(204, 153, 0));
        btnDelete.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnDelete.setText("Detete Selected");
        btnDelete.setMaximumSize(new java.awt.Dimension(88, 30));
        btnDelete.setMinimumSize(new java.awt.Dimension(88, 30));
        btnDelete.setPreferredSize(new java.awt.Dimension(88, 30));
        add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 130, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdduserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdduserActionPerformed
        addUser();
    }//GEN-LAST:event_btnAdduserActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearFormFields();
    }//GEN-LAST:event_btnClearActionPerformed

    private void UserstableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserstableMouseClicked
        int selectedRow = Userstable.getSelectedRow();
        if (selectedRow != -1) {
            selectedUserId = Integer.parseInt(Userstable.getValueAt(selectedRow, 0).toString());

            // Load other fields as before
            txtName.setText(Userstable.getValueAt(selectedRow, 1).toString());
            txtUsername.setText(Userstable.getValueAt(selectedRow, 2).toString());
            txtContact.setText(Userstable.getValueAt(selectedRow, 3).toString());
            cmbUsertype.setSelectedItem(Userstable.getValueAt(selectedRow, 5).toString());
            int branchId = Integer.parseInt(Userstable.getValueAt(selectedRow, 7).toString());

            for (int i = 0; i < cmbBranch.getItemCount(); i++) {
                ComboItem item = cmbBranch.getItemAt(i);
                if (item.getId() == branchId) {
                    cmbBranch.setSelectedIndex(i);
                    break;
                }
            }

        }

    }//GEN-LAST:event_UserstableMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateUser();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        String Keyword = txtSearch.getText();
        loadUserData(Keyword);
    }//GEN-LAST:event_txtSearchKeyPressed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        txtSearch.setText("");
        loadUserData("");
        loadBranches();
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Userstable;
    private javax.swing.JButton btnAdduser;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<ComboItem> cmbBranch;
    private javax.swing.JComboBox<String> cmbUsertype;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
