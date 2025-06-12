
package ISMS.views;

public class UsersView extends javax.swing.JPanel {

    
    public UsersView() {
        initComponents();
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
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.MatteBorder(null));
        setMaximumSize(new java.awt.Dimension(1050, 521));
        setMinimumSize(new java.awt.Dimension(1050, 521));
        setPreferredSize(new java.awt.Dimension(1050, 521));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel1.setText("Users:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 123, 36));

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
        jPanel1.add(btnAdduser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        btnUpdate.setBackground(new java.awt.Color(0, 153, 51));
        btnUpdate.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setMaximumSize(new java.awt.Dimension(70, 30));
        btnUpdate.setMinimumSize(new java.awt.Dimension(70, 30));
        btnUpdate.setPreferredSize(new java.awt.Dimension(70, 30));
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 80, -1));

        btnClear.setBackground(new java.awt.Color(255, 255, 204));
        btnClear.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnClear.setText("Clear");
        btnClear.setMaximumSize(new java.awt.Dimension(70, 30));
        btnClear.setMinimumSize(new java.awt.Dimension(70, 30));
        btnClear.setPreferredSize(new java.awt.Dimension(70, 30));
        jPanel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, -1, -1));

        txtPassword.setMaximumSize(new java.awt.Dimension(180, 30));
        txtPassword.setMinimumSize(new java.awt.Dimension(180, 30));
        txtPassword.setPreferredSize(new java.awt.Dimension(180, 30));
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 115, -1, -1));

        cmbBranch.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        cmbBranch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Branch" }));
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Username", "Contact Number", "Branch", "User Type", "Created date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 45, -1, -1));

        jLabel7.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel7.setText("Search:");
        jLabel7.setMinimumSize(new java.awt.Dimension(90, 30));
        jLabel7.setPreferredSize(new java.awt.Dimension(90, 30));
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 54, -1));

        jTextField5.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jTextField5.setMinimumSize(new java.awt.Dimension(250, 30));
        jTextField5.setPreferredSize(new java.awt.Dimension(250, 30));
        add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, -1, -1));

        btnRefresh.setText("Refresh");
        add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 13, -1, -1));

        btnDelete.setBackground(new java.awt.Color(204, 153, 0));
        btnDelete.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnDelete.setText("Detete Selected");
        btnDelete.setMaximumSize(new java.awt.Dimension(88, 30));
        btnDelete.setMinimumSize(new java.awt.Dimension(88, 30));
        btnDelete.setPreferredSize(new java.awt.Dimension(88, 30));
        add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 130, 30));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdduser;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbBranch;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
