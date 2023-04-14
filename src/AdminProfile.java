
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import swing.MyPassword;
import swing.MyTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dramr
 */
public class AdminProfile extends javax.swing.JPanel {
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private int admin_id;
    private boolean showCases = false;
    
    public AdminProfile(int admin_id) {
        this.admin_id = admin_id;
        
        initComponents();
        showTableUsers();
        System.out.println("Admin");
    }
    
    // ***************************************************************

    public String getTxtEmail() {
        return txtEmail.getText().trim();
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail.setText(txtEmail);
    }

    public String getTxtFname() {
        return txtFname.getText().trim();
    }

    public void setTxtFname(String txtFname) {
        this.txtFname.setText(txtFname);
    }

    public String getTxtId() {
        return txtId.getText().trim();
    }

    public void setTxtId(String txtId) {
        this.txtId.setText(txtId);
    }

    public String getTxtLname() {
        return txtLname.getText().trim();
    }

    public void setTxtLname(String txtLname) {
        this.txtLname.setText(txtLname);
    }

    public String getTxtPass() {
        return String.valueOf(txtPass.getPassword()).trim();
    }

    public void setTxtPass(String txtPass) {
        this.txtPass.setText(txtPass);
    }

    public String getTxtPhone() {
        return txtPhone.getText().trim();
    }

    public void setTxtPhone(String txtPhone) {
        this.txtPhone.setText(txtPhone);
    }

    public String getTxtUsername() {
        return txtUsername.getText().trim();
    }

    public void setTxtUsername(String txtUsername) {
        this.txtUsername.setText(txtUsername);
    }
    
    // ***************************************************************
    
    // ***************************************************************

    public String getLableEmail() {
        return lableEmail.getText();
    }

    public void setLableEmail(String lableEmail) {
        this.lableEmail.setText(lableEmail);
    }

    public String getLableId() {
        return lableId.getText();
    }

    public void setLableId(String lableId) {
        this.lableId.setText(lableId);
    }

    public String getLableName() {
        return lableName.getText();
    }

    public void setLableName(String lableName) {
        this.lableName.setText(lableName);
    }
    
    // ***************************************************************
    
    // ***************************************************************

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
    
    public void addEventLogout(ActionListener event){
        logoutButton.addActionListener(event);
    }
    
    private boolean isTextEmpty(){
        return getTxtEmail().equals("")
                || getTxtFname().equals("")
                || getTxtId().equals("")
                || getTxtLname().equals("")
                || getTxtPass().equals("")
                || getTxtPhone().equals("")
                || getTxtUsername().equals("");
    }
    
    private void clearAll(){
        setTxtEmail("");
        setTxtFname("");
        setTxtId("");
        setTxtLname("");
        setTxtPass("");
        setTxtPhone("");
        setTxtUsername("");
    }
    
    // ***************************************************************
    
    private void showTableUsers(){
        try {
            if(showCases){
                String sql = "SELECT case_id, case_status, category_name, mosque_name, goal_amount, case_desc, case_date, user_id "
                        + "FROM case, category, mosque "
                        + "WHERE case.category_id = category.category_id AND case.mosque_id = mosque.mosque_id "
                        + "ORDER BY 1 DESC";
                con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
                pst = con.prepareStatement(sql);
            }
            else{
                String sql = "SELECT * FROM users";
                con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
                pst = con.prepareStatement(sql);
            }
            
            rs = pst.executeQuery();
            
            tableUser.setModel(DbUtils.resultSetToTableModel(rs));
            con.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
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

        jLabel5 = new javax.swing.JLabel();
        loginbutton2 = new swing.MyButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLname = new swing.MyTextField();
        lableName = new javax.swing.JLabel();
        lableId = new javax.swing.JLabel();
        lableEmail = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtId = new swing.MyTextField();
        addUserButton = new swing.MyButton();
        deleteUserButton = new swing.MyButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        logoutButton = new swing.MyButton();
        txtUsername = new swing.MyTextField();
        txtFname = new swing.MyTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtEmail = new swing.MyTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPhone = new swing.MyTextField();
        txtPass = new swing.MyPassword();
        updateUserButton = new swing.MyButton();
        clearButton = new swing.MyButton();
        toggleButton = new swing.MyButton();

        jLabel5.setForeground(new java.awt.Color(68, 68, 68));
        jLabel5.setText("Username");

        loginbutton2.setBackground(new java.awt.Color(124, 228, 249));
        loginbutton2.setText("Delete Case");
        loginbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbutton2ActionPerformed(evt);
            }
        });

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(68, 68, 68));
        jLabel4.setText("Username");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(68, 68, 68));
        jLabel6.setText("First Name");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(68, 68, 68));
        jLabel7.setText("Last Name");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(68, 68, 68));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MFS Administration");

        lableName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableName.setForeground(new java.awt.Color(68, 68, 68));
        lableName.setText("Name: Amr Mahmoud");

        lableId.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableId.setForeground(new java.awt.Color(68, 68, 68));
        lableId.setText("ID: 1122334455");

        lableEmail.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableEmail.setForeground(new java.awt.Color(68, 68, 68));
        lableEmail.setText("Email: amr@admin.mfs.com");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(68, 68, 68));
        jLabel9.setText("National ID");

        addUserButton.setBackground(new java.awt.Color(124, 228, 249));
        addUserButton.setText("Add User");
        addUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserButtonActionPerformed(evt);
            }
        });

        deleteUserButton.setBackground(new java.awt.Color(124, 228, 249));
        deleteUserButton.setText("Delete User");
        deleteUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserButtonActionPerformed(evt);
            }
        });

        tableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "National ID", "Username", "Full name", "Password", "Email", "Phone Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableUser);

        logoutButton.setBackground(new java.awt.Color(124, 228, 249));
        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        txtFname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFnameActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(68, 68, 68));
        jLabel10.setText("Password");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(68, 68, 68));
        jLabel11.setText("Email");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(68, 68, 68));
        jLabel12.setText("Phone Number");

        updateUserButton.setBackground(new java.awt.Color(124, 228, 249));
        updateUserButton.setText("Update User");
        updateUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUserButtonActionPerformed(evt);
            }
        });

        clearButton.setBackground(new java.awt.Color(124, 228, 249));
        clearButton.setText("Clear All");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        toggleButton.setBackground(new java.awt.Color(124, 228, 249));
        toggleButton.setText("Show Cases");
        toggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lableName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lableId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lableEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtLname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(36, 36, 36))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(deleteUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(updateUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(toggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableName)
                    .addComponent(lableId)
                    .addComponent(lableEmail))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserButtonActionPerformed
        if(!isTextEmpty()){
            try {
                String sql = "INSERT INTO users"
                    + "(national_id, username, first_name, last_name, password, email, phone_number)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)";
                
                con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
                pst = con.prepareStatement(sql);
                
                pst.setString(1, getTxtId());
                pst.setString(2, getTxtUsername());
                pst.setString(3, getTxtFname());
                pst.setString(4, getTxtLname());
                pst.setString(5, getTxtPass());
                pst.setString(6, getTxtEmail());
                pst.setString(7, getTxtPhone());

                pst.executeUpdate();
                con.close();
                JOptionPane.showMessageDialog(this, "Account is Created successfully");
                showTableUsers();
            }
            catch (Exception e) {
                if(e.toString().contains("unique"))
                    JOptionPane.showMessageDialog(this, "This Account already exists");
                else if(e.toString().contains("number"))
                    JOptionPane.showMessageDialog(this, "Please Enter a valid Information");
                else
                    JOptionPane.showMessageDialog(this, e);
            }
            clearAll();
        }
        else
            JOptionPane.showMessageDialog(this, "Please Enter all User's Information");
    }//GEN-LAST:event_addUserButtonActionPerformed

    private void deleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserButtonActionPerformed
        if(!getTxtId().equals("")){
            try {
                String sql = "DELETE FROM users WHERE national_id = ?";
                con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
                pst = con.prepareStatement(sql);
                
                pst.setString(1, getTxtId());
                
                int isDone = pst.executeUpdate();
                con.close();
                if (isDone == 1)
                    JOptionPane.showMessageDialog(this, "User was Deleted successfully");
                else
                    JOptionPane.showMessageDialog(this, "No User with that National ID");
                
                showTableUsers();
            }
            catch (Exception e) {
                if(e.toString().contains("child"))
                    JOptionPane.showMessageDialog(this, "This Account is active and cannot be Deleted");
                else
                    JOptionPane.showMessageDialog(this, e);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please Enter User National ID!!");
        }
        clearAll();
    }//GEN-LAST:event_deleteUserButtonActionPerformed

    private void loginbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbutton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton2ActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void txtFnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFnameActionPerformed

    private void updateUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUserButtonActionPerformed
        if(!isTextEmpty()){
            try {
                String sql = "UPDATE users SET "
                    + "username = ?, first_name = ?, last_name = ?, password = ?, email = ?, phone_number = ? "
                    + "WHERE national_id = ?";
                
                con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
                pst = con.prepareStatement(sql);
                
                pst.setString(1, getTxtUsername());
                pst.setString(2, getTxtFname());
                pst.setString(3, getTxtLname());
                pst.setString(4, getTxtPass());
                pst.setString(5, getTxtEmail());
                pst.setString(6, getTxtPhone());
                pst.setString(7, getTxtId());
                
                int isDone = pst.executeUpdate();
                con.close();
                if (isDone == 1)
                    JOptionPane.showMessageDialog(this, "Account is Updated successfully");
                else
                    JOptionPane.showMessageDialog(this, "No User with that National ID");
                showTableUsers();
            }
            catch (Exception e) {
                if(e.toString().contains("unique"))
                    JOptionPane.showMessageDialog(this, "This Information is already taken");
                else if(e.toString().contains("number"))
                    JOptionPane.showMessageDialog(this, "Please Enter a valid Information");
                else
                    JOptionPane.showMessageDialog(this, e);
            }
            clearAll();
        }
        else
            JOptionPane.showMessageDialog(this, "Please Enter all User's Information");
    }//GEN-LAST:event_updateUserButtonActionPerformed

    private void tableUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUserMouseClicked
        int row = tableUser.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
        
        try{
            setTxtId(model.getValueAt(row, 0).toString());
            setTxtUsername(model.getValueAt(row, 1).toString());
            setTxtFname(model.getValueAt(row, 2).toString());
            setTxtLname(model.getValueAt(row, 3).toString());
            setTxtPass(model.getValueAt(row, 4).toString());
            setTxtEmail(model.getValueAt(row, 5).toString());
            setTxtPhone(model.getValueAt(row, 6).toString());
        }
        catch(Exception e){
        }
    }//GEN-LAST:event_tableUserMouseClicked

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clearAll();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void toggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleButtonActionPerformed
        clearAll();
        showCases = !showCases;
        toggleButton.setText(showCases? "Show Users" : "Show Cases");
        txtPass.setEchoChar(showCases? (char) 0 : '*');
        showTableUsers();
    }//GEN-LAST:event_toggleButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.MyButton addUserButton;
    private swing.MyButton clearButton;
    private swing.MyButton deleteUserButton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lableEmail;
    private javax.swing.JLabel lableId;
    private javax.swing.JLabel lableName;
    private swing.MyButton loginbutton2;
    private swing.MyButton logoutButton;
    private javax.swing.JTable tableUser;
    private swing.MyButton toggleButton;
    private swing.MyTextField txtEmail;
    private swing.MyTextField txtFname;
    private swing.MyTextField txtId;
    private swing.MyTextField txtLname;
    private swing.MyPassword txtPass;
    private swing.MyTextField txtPhone;
    private swing.MyTextField txtUsername;
    private swing.MyButton updateUserButton;
    // End of variables declaration//GEN-END:variables
}
