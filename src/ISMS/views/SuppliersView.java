package ISMS.views;

import ISMS.models.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SuppliersView extends javax.swing.JPanel {

    /**
     * Creates new form SuppliersView
     */
    public SuppliersView() {
        initComponents();
        loadProducts();
        loadSupplier("");
        loadProductSupplier("");
    }

    private void loadProducts() {
        String query = "SELECT product_id, name FROM product";
        try (Connection con = Dbconnect.getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            cmbProduct.removeAllItems();
            cmbProduct.addItem(new ComboItem(0, "Select a Product"));
            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");

                ComboItem item = new ComboItem(id, name);
                cmbProduct.addItem(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading Products: " + ex.getMessage());
        }
    }

    public void addSupplier(String name, String contact_info) {
        String query = "INSERT INTO Supplier (name, contact_info) VALUES (?, ?)";
        try (Connection conn = Dbconnect.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, name);
            pst.setString(2, contact_info);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Supplier added successfully!");
            loadSupplier("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding Supplier: " + e.getMessage());
        }

    }

    public void loadSupplier(String keyword) {
        Connection conn = Dbconnect.getConnection();
        DefaultTableModel model = (DefaultTableModel) tblSuppliers.getModel();
        model.setRowCount(0); // Clear table

        String query = "SELECT * FROM Supplier WHERE name LIKE ? OR contact_info LIKE ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            String searchPattern = "%" + keyword + "%";
            pst.setString(1, searchPattern);
            pst.setString(2, searchPattern);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("supplier_id"),
                    rs.getString("name"),
                    rs.getString("contact_info")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error searching Supplier: " + e.getMessage());
        }
    }

    public void updateSupplier(int id, String name, String contact) {
        String query = "UPDATE Supplier SET name=?, contact_info=? WHERE supplier_id=?";
        try (Connection conn = Dbconnect.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, name);
            pst.setString(2, contact);
            pst.setInt(3, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Supplier updated successfully!");
            loadSupplier("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating Supplier: " + e.getMessage());
        }
    }

    public void deleteSupplier(int id) {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM Supplier WHERE supplier_id=?";
            try (Connection conn = Dbconnect.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, id);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Supplier deleted.");
                loadSupplier("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error deleting Supplier: " + e.getMessage());
            }
        }
    }

    public void addProductSupplier(int Proudctid, int Supplierid) {
        String query = "INSERT INTO productsupplier (product_id, supplier_id) VALUES (?, ?)";
        try (Connection conn = Dbconnect.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, Proudctid);
            pst.setInt(2, Supplierid);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Proudct Connected to Supplier successfully!");
            loadProductSupplier("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Connecting Proudct to Supplier: " + e.getMessage());
        }
    }

    public void loadProductSupplier(String keyword) {
        Connection conn = Dbconnect.getConnection();
        DefaultTableModel model = (DefaultTableModel) tblProductSupplier.getModel();
        model.setRowCount(0); // Clear existing data

        String query = "SELECT ps.product_supplier_id, "
                + "p.product_id, p.name AS product_name, "
                + "s.supplier_id, s.name AS supplier_name "
                + "FROM ProductSupplier ps "
                + "JOIN Product p ON ps.product_id = p.product_id "
                + "JOIN Supplier s ON ps.supplier_id = s.supplier_id "
                + "WHERE p.name LIKE ? OR s.name LIKE ?";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            String searchPattern = "%" + keyword + "%";
            pst.setString(1, searchPattern);
            pst.setString(2, searchPattern);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("product_supplier_id"),
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("supplier_id"),
                    rs.getString("supplier_name")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading product-supplier data: " + e.getMessage());
        }
    }

    public void deleteProductSupplier(int id) {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this product-supplier mapping?", "Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM ProductSupplier WHERE product_supplier_id = ?";
            try (Connection conn = Dbconnect.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, id);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Product-Supplier mapping deleted.");
                loadProductSupplier(""); // refresh table
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error deleting Product-Supplier: " + e.getMessage());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnLinkProduct = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductSupplier = new javax.swing.JTable();
        cmbProduct = new javax.swing.JComboBox<>();
        txtSupplier_name = new javax.swing.JTextField();
        btnClear1 = new javax.swing.JButton();
        txtSearchProductSupplier = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnrefresh2 = new javax.swing.JButton();
        btnDeletePro_Sup = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSuppliers = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtSearchSupplier = new javax.swing.JTextField();
        btnrefresh1 = new javax.swing.JButton();
        btnDeleteSuppliers = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtSupplierName = new javax.swing.JTextField();
        txtSupplierContact = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnAddSupplier = new javax.swing.JButton();
        UpdateSupplierbtn = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1050, 570));
        setMinimumSize(new java.awt.Dimension(1050, 570));
        setPreferredSize(new java.awt.Dimension(1050, 570));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel1.setText("Suppliers:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 123, 36));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Product Association", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Serif", 1, 18))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(320, 463));
        jPanel1.setMinimumSize(new java.awt.Dimension(320, 463));
        jPanel1.setPreferredSize(new java.awt.Dimension(320, 463));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel2.setText("Supplier");
        jLabel2.setMaximumSize(new java.awt.Dimension(80, 30));
        jLabel2.setMinimumSize(new java.awt.Dimension(80, 30));
        jLabel2.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 110, -1));

        jLabel3.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel3.setText("Product:");
        jLabel3.setMaximumSize(new java.awt.Dimension(80, 30));
        jLabel3.setMinimumSize(new java.awt.Dimension(80, 30));
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        btnLinkProduct.setBackground(new java.awt.Color(0, 153, 51));
        btnLinkProduct.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnLinkProduct.setText("Link Product to Supplier");
        btnLinkProduct.setMaximumSize(new java.awt.Dimension(70, 30));
        btnLinkProduct.setMinimumSize(new java.awt.Dimension(70, 30));
        btnLinkProduct.setPreferredSize(new java.awt.Dimension(70, 30));
        btnLinkProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLinkProductActionPerformed(evt);
            }
        });
        jPanel1.add(btnLinkProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 190, -1));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Suppliers products", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblProductSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Supplier ID", "Product ID", "Product Name", "Supplier ID", "Supplier Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblProductSupplier);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 1010, 150));

        cmbProduct.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel1.add(cmbProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 200, 30));

        txtSupplier_name.setEditable(false);
        txtSupplier_name.setMaximumSize(new java.awt.Dimension(200, 30));
        txtSupplier_name.setMinimumSize(new java.awt.Dimension(200, 30));
        txtSupplier_name.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel1.add(txtSupplier_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 200, 30));

        btnClear1.setBackground(new java.awt.Color(255, 255, 204));
        btnClear1.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnClear1.setText("Clear");
        btnClear1.setMaximumSize(new java.awt.Dimension(70, 30));
        btnClear1.setMinimumSize(new java.awt.Dimension(70, 30));
        btnClear1.setPreferredSize(new java.awt.Dimension(70, 30));
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnClear1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, -1, 30));

        txtSearchProductSupplier.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        txtSearchProductSupplier.setMinimumSize(new java.awt.Dimension(250, 30));
        txtSearchProductSupplier.setPreferredSize(new java.awt.Dimension(250, 30));
        txtSearchProductSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchProductSupplierKeyPressed(evt);
            }
        });
        jPanel1.add(txtSearchProductSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 190, -1));

        jLabel8.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel8.setText("Search:");
        jLabel8.setMinimumSize(new java.awt.Dimension(90, 30));
        jLabel8.setPreferredSize(new java.awt.Dimension(90, 30));
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 54, -1));

        btnrefresh2.setText("Refresh");
        jPanel1.add(btnrefresh2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 13, -1, -1));

        btnDeletePro_Sup.setBackground(new java.awt.Color(204, 153, 0));
        btnDeletePro_Sup.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnDeletePro_Sup.setText("Delete Selected");
        btnDeletePro_Sup.setMaximumSize(new java.awt.Dimension(88, 30));
        btnDeletePro_Sup.setMinimumSize(new java.awt.Dimension(88, 30));
        btnDeletePro_Sup.setPreferredSize(new java.awt.Dimension(88, 30));
        btnDeletePro_Sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePro_SupActionPerformed(evt);
            }
        });
        jPanel1.add(btnDeletePro_Sup, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 130, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 1030, 300));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setMaximumSize(new java.awt.Dimension(701, 392));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(701, 392));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(701, 392));

        tblSuppliers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Contact Info"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSuppliers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSuppliersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSuppliers);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 45, -1, 200));

        jLabel7.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel7.setText("Search:");
        jLabel7.setMinimumSize(new java.awt.Dimension(90, 30));
        jLabel7.setPreferredSize(new java.awt.Dimension(90, 30));
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 54, -1));

        txtSearchSupplier.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        txtSearchSupplier.setMinimumSize(new java.awt.Dimension(250, 30));
        txtSearchSupplier.setPreferredSize(new java.awt.Dimension(250, 30));
        txtSearchSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchSupplierActionPerformed(evt);
            }
        });
        add(txtSearchSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, -1, -1));

        btnrefresh1.setText("Refresh");
        add(btnrefresh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 13, -1, -1));

        btnDeleteSuppliers.setBackground(new java.awt.Color(204, 153, 0));
        btnDeleteSuppliers.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnDeleteSuppliers.setText("Delete Selected");
        btnDeleteSuppliers.setMaximumSize(new java.awt.Dimension(88, 30));
        btnDeleteSuppliers.setMinimumSize(new java.awt.Dimension(88, 30));
        btnDeleteSuppliers.setPreferredSize(new java.awt.Dimension(88, 30));
        btnDeleteSuppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSuppliersActionPerformed(evt);
            }
        });
        add(btnDeleteSuppliers, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, 130, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add a New Suppliers", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Serif", 1, 18))); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(320, 463));
        jPanel2.setMinimumSize(new java.awt.Dimension(320, 463));
        jPanel2.setPreferredSize(new java.awt.Dimension(320, 463));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel4.setText("Name:");
        jLabel4.setMaximumSize(new java.awt.Dimension(80, 30));
        jLabel4.setMinimumSize(new java.awt.Dimension(80, 30));
        jLabel4.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 35, -1, -1));

        txtSupplierName.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        txtSupplierName.setMaximumSize(new java.awt.Dimension(180, 30));
        txtSupplierName.setMinimumSize(new java.awt.Dimension(180, 30));
        txtSupplierName.setPreferredSize(new java.awt.Dimension(180, 30));
        jPanel2.add(txtSupplierName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 35, -1, -1));

        txtSupplierContact.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        txtSupplierContact.setMaximumSize(new java.awt.Dimension(180, 30));
        txtSupplierContact.setMinimumSize(new java.awt.Dimension(180, 30));
        txtSupplierContact.setPreferredSize(new java.awt.Dimension(180, 30));
        jPanel2.add(txtSupplierContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 75, -1, -1));

        jLabel5.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel5.setText("ContactNo:");
        jLabel5.setMaximumSize(new java.awt.Dimension(80, 30));
        jLabel5.setMinimumSize(new java.awt.Dimension(80, 30));
        jLabel5.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 75, -1, -1));

        btnAddSupplier.setBackground(new java.awt.Color(0, 204, 204));
        btnAddSupplier.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnAddSupplier.setText("Add");
        btnAddSupplier.setMaximumSize(new java.awt.Dimension(70, 30));
        btnAddSupplier.setMinimumSize(new java.awt.Dimension(70, 30));
        btnAddSupplier.setPreferredSize(new java.awt.Dimension(70, 30));
        btnAddSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSupplierActionPerformed(evt);
            }
        });
        jPanel2.add(btnAddSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        UpdateSupplierbtn.setBackground(new java.awt.Color(0, 153, 51));
        UpdateSupplierbtn.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        UpdateSupplierbtn.setText("Update");
        UpdateSupplierbtn.setMaximumSize(new java.awt.Dimension(70, 30));
        UpdateSupplierbtn.setMinimumSize(new java.awt.Dimension(70, 30));
        UpdateSupplierbtn.setPreferredSize(new java.awt.Dimension(70, 30));
        UpdateSupplierbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateSupplierbtnActionPerformed(evt);
            }
        });
        jPanel2.add(UpdateSupplierbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 80, -1));

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
        jPanel2.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, -1, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 320, 200));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLinkProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLinkProductActionPerformed
        int selectedRow = tblSuppliers.getSelectedRow();
        if (selectedRow >= 0) {
            int Supplierid = (int) tblSuppliers.getValueAt(selectedRow, 0);
            ComboItem item = (ComboItem) cmbProduct.getSelectedItem();
            int Productid = item.getId();
            addProductSupplier(Productid, Supplierid);
        }
    }//GEN-LAST:event_btnLinkProductActionPerformed

    private void btnAddSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSupplierActionPerformed
        String Name = txtSupplierName.getText();
        String contact = txtSupplierContact.getText();

        addSupplier(Name, contact);
    }//GEN-LAST:event_btnAddSupplierActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        txtSupplier_name.setText("");
        cmbProduct.setSelectedIndex(0);
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void UpdateSupplierbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateSupplierbtnActionPerformed
        int selectedRow = tblSuppliers.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tblSuppliers.getValueAt(selectedRow, 0);
            String name = txtSupplierName.getText();
            String contact = txtSupplierContact.getText();

            if (name.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in both name and contactNo.");
                return;
            }

            updateSupplier(id, name, contact);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a branch to update.");
        }
    }//GEN-LAST:event_UpdateSupplierbtnActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtSupplierName.setText("");
        txtSupplierContact.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void tblSuppliersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSuppliersMouseClicked
        int selectedRow = tblSuppliers.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tblSuppliers.getValueAt(selectedRow, 0);
            txtSupplierName.setText(tblSuppliers.getValueAt(selectedRow, 1).toString());
            txtSupplierContact.setText(tblSuppliers.getValueAt(selectedRow, 2).toString());
            txtSupplier_name.setText(tblSuppliers.getValueAt(selectedRow, 1).toString());
        }
    }//GEN-LAST:event_tblSuppliersMouseClicked

    private void btnDeleteSuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSuppliersActionPerformed
        int selectedRow = tblSuppliers.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tblSuppliers.getValueAt(selectedRow, 0);
            deleteSupplier(id);
        }else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }//GEN-LAST:event_btnDeleteSuppliersActionPerformed

    private void txtSearchSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchSupplierActionPerformed
        String Keyword = txtSearchSupplier.getText();
        loadSupplier(Keyword);
    }//GEN-LAST:event_txtSearchSupplierActionPerformed

    private void txtSearchProductSupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchProductSupplierKeyPressed
        String KeyWord = txtSearchProductSupplier.getText();
        loadProductSupplier(KeyWord);
    }//GEN-LAST:event_txtSearchProductSupplierKeyPressed

    private void btnDeletePro_SupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePro_SupActionPerformed
        int selectedRow = tblProductSupplier.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tblProductSupplier.getValueAt(selectedRow, 0);
            deleteProductSupplier(id);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }

    }//GEN-LAST:event_btnDeletePro_SupActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton UpdateSupplierbtn;
    private javax.swing.JButton btnAddSupplier;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnDeletePro_Sup;
    private javax.swing.JButton btnDeleteSuppliers;
    private javax.swing.JButton btnLinkProduct;
    private javax.swing.JButton btnrefresh1;
    private javax.swing.JButton btnrefresh2;
    private javax.swing.JComboBox<ComboItem> cmbProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblProductSupplier;
    private javax.swing.JTable tblSuppliers;
    private javax.swing.JTextField txtSearchProductSupplier;
    private javax.swing.JTextField txtSearchSupplier;
    private javax.swing.JTextField txtSupplierContact;
    private javax.swing.JTextField txtSupplierName;
    private javax.swing.JTextField txtSupplier_name;
    // End of variables declaration//GEN-END:variables
}
