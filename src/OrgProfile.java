
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
public class OrgProfile extends javax.swing.JPanel {
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private int org_id;
    private boolean showCases = false;
    private boolean showPending = false;
    
    public OrgProfile(int admin_id) {
        this.org_id = admin_id;
        
        initComponents();
        showTableUsers();
        System.out.println("Org");
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
    
    public String getLabelTitle() {
        return labelTitle.getText();
    }

    public void setLabelTitle(String labelTitle) {
        this.labelTitle.setText(labelTitle);
    }

    public String getLableEmail() {
        return lableEmail.getText();
    }

    public void setLableEmail(String lableEmail) {
        this.lableEmail.setText(lableEmail);
    }

    public String getLableId() {
        return labelId.getText();
    }

    public void setLableId(String labelId) {
        this.labelId.setText(labelId);
    }

    public String getLableName() {
        return labelName.getText();
    }

    public void setLableName(String labelName) {
        this.labelName.setText(labelName);
    }
    
    // ***************************************************************
    
    // ***************************************************************

    public int getOrg_id() {
        return org_id;
    }

    public void setOrg_id(int org_id) {
        this.org_id = org_id;
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
                if(showPending){
                    String sql = "SELECT case_id, case_status, category_name, mosque_name, goal_amount, case_desc, case_date, user_id "
                        + "FROM case, category, mosque "
                        + "WHERE case_status = ? AND case.category_id = category.category_id AND case.mosque_id = mosque.mosque_id "
                        + "ORDER BY 1 DESC";
                
                    con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
                    pst = con.prepareStatement(sql);

                    pst.setString(1, "pending");
                }
                else{
                    String sql = "SELECT case_id, case_status, category_name, mosque_name, goal_amount, case_desc, case_date, user_id "
                        + "FROM case, category, mosque "
                        + "WHERE case.category_id = category.category_id AND case.mosque_id = mosque.mosque_id "
                        + "ORDER BY 1 DESC";
                    con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
                    pst = con.prepareStatement(sql);
                }
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
        lableUser = new javax.swing.JLabel();
        lableFname = new javax.swing.JLabel();
        lableLname = new javax.swing.JLabel();
        labelTitle = new javax.swing.JLabel();
        txtLname = new swing.MyTextField();
        labelName = new javax.swing.JLabel();
        labelId = new javax.swing.JLabel();
        lableEmail = new javax.swing.JLabel();
        lableNid = new javax.swing.JLabel();
        txtId = new swing.MyTextField();
        addUserButton = new swing.MyButton();
        deleteUserButton = new swing.MyButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        logoutButton = new swing.MyButton();
        txtUsername = new swing.MyTextField();
        txtFname = new swing.MyTextField();
        lablePass = new javax.swing.JLabel();
        lableE = new javax.swing.JLabel();
        txtEmail = new swing.MyTextField();
        lableNumber = new javax.swing.JLabel();
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

        lableUser.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableUser.setForeground(new java.awt.Color(68, 68, 68));
        lableUser.setText("Username");

        lableFname.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableFname.setForeground(new java.awt.Color(68, 68, 68));
        lableFname.setText("First Name");

        lableLname.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableLname.setForeground(new java.awt.Color(68, 68, 68));
        lableLname.setText("Last Name");

        labelTitle.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        labelTitle.setForeground(new java.awt.Color(68, 68, 68));
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("Organization");

        labelName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelName.setForeground(new java.awt.Color(68, 68, 68));
        labelName.setText("Name: General Authority for Awqaf");

        labelId.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelId.setForeground(new java.awt.Color(68, 68, 68));
        labelId.setText("ID: 11662");

        lableEmail.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableEmail.setForeground(new java.awt.Color(68, 68, 68));
        lableEmail.setText("Email: csc@awqaf.gov.sa");

        lableNid.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableNid.setForeground(new java.awt.Color(68, 68, 68));
        lableNid.setText("National ID");

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

        lablePass.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lablePass.setForeground(new java.awt.Color(68, 68, 68));
        lablePass.setText("Password");

        lableE.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableE.setForeground(new java.awt.Color(68, 68, 68));
        lableE.setText("Email");

        lableNumber.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableNumber.setForeground(new java.awt.Color(68, 68, 68));
        lableNumber.setText("Phone Number");

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
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(labelId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lableEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableNid)
                            .addComponent(lableUser)
                            .addComponent(lableFname)
                            .addComponent(lableLname)
                            .addComponent(lablePass)
                            .addComponent(lableE)
                            .addComponent(lableNumber))
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
                .addComponent(labelTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(labelId)
                    .addComponent(lableEmail))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lableNid)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableFname, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lableLname)
                            .addComponent(txtLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lablePass)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lableE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lableNumber)
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
        if(showCases){
            if(!getTxtId().equals("") && getTxtId().matches("[0-9]+"))
                acceptCase(Integer.parseInt(getTxtId()));
            else
                JOptionPane.showMessageDialog(this, "Please Enter your Case ID!!");
        }
        else{
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
        }
    }//GEN-LAST:event_addUserButtonActionPerformed

    private void deleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserButtonActionPerformed
        if(showCases){
            if(!getTxtId().equals("") && getTxtId().matches("[0-9]+"))
                rejectCase(Integer.parseInt(getTxtId()));
            else
                JOptionPane.showMessageDialog(this, "Please Enter your Case ID!!");
        }
        else{
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
        }
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
        if(showCases){
            clearAll();
            showPending = !showPending;
            
            updateUserButton.setText(showPending? "All Cases" : "Pending Cases");
            
            showTableUsers();
        }
        else{
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
            }
    }//GEN-LAST:event_updateUserButtonActionPerformed

    private void tableUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUserMouseClicked
        int row = tableUser.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
        
        setTxtId(model.getValueAt(row, 0).toString());
        setTxtUsername(model.getValueAt(row, 1).toString());
        setTxtFname(model.getValueAt(row, 2).toString());
        setTxtLname(model.getValueAt(row, 3).toString());
        setTxtPass((showCases? "$" : "") + model.getValueAt(row, 4).toString());
        setTxtEmail(model.getValueAt(row, 5) == null ? "" : model.getValueAt(row, 5).toString());
        setTxtPhone(model.getValueAt(row, showCases? 7 : 6).toString());
    }//GEN-LAST:event_tableUserMouseClicked

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clearAll();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void toggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleButtonActionPerformed
        clearAll();
        showCases = !showCases;
        
        toggleButton.setText(showCases? "Show Users" : "Show Cases");
        txtPass.setEchoChar(showCases? (char) 0 : '*');
        lableNid.setText(showCases? "Case ID" : "National ID");
        lableUser.setText(showCases? "Case Status" : "Username");
        lableFname.setText(showCases? "Category" : "First Name");
        lableLname.setText(showCases? "Mosque" : "Last Name");
        lablePass.setText(showCases? "Goal Amount" : "Password");
        lableE.setText(showCases? "Description" : "Email");
        lableNumber.setText(showCases? "User ID" : "Phone Number");
        
        addUserButton.setText(showCases? "Accept Case" : "Add User");
        deleteUserButton.setText(showCases? "Reject Case" : "Delete User");
        updateUserButton.setText(showCases? (showPending? "All Cases" : "Pending Cases") : "Update User");
        
        showTableUsers();
    }//GEN-LAST:event_toggleButtonActionPerformed
    
    private void acceptCase(int caseId){
        try {
            String sql = "SELECT * FROM case WHERE case_id = ? AND case_status = ?";
            
            con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
            pst = con.prepareStatement(sql);
            
            pst.setInt(1, caseId);
            pst.setString(2, "pending");

            rs = pst.executeQuery();
            if(rs.next()){
                String input = JOptionPane.showInputDialog(this, "Enter the Goal Amount", "Accepting Case with ID " + caseId, JOptionPane.INFORMATION_MESSAGE);
        
                if(input != null){
                    if(input.matches("[0-9]+")){
                        double goalAmount = Double.parseDouble(input);
                        
                        sql = "UPDATE case SET "
                                + "case_status = ?, goal_amount = ? WHERE case_id = ?";
                        
                        pst = con.prepareStatement(sql);
                        
                        pst.setString(1, "active");
                        pst.setDouble(2, goalAmount);
                        pst.setInt(3, caseId);
                        
                        int isDone = pst.executeUpdate();
                        if (isDone == 1)
                            JOptionPane.showMessageDialog(this, "Case with ID " + caseId + " is Accepted\nThe Goal amount is $" + goalAmount);
                        else
                            JOptionPane.showMessageDialog(this, "Something went wrong!!");
                        clearAll();
                        showTableUsers();
                    }
                    else
                        JOptionPane.showMessageDialog(this, "Please Enter a valid number!!");
                }
                else{
                    clearAll();
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "You can ONLY accept Pending cases!!");
                clearAll();
            }
            con.close();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    private void rejectCase(int caseId){
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to REJECT the case with ID " + caseId + "?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(confirm == JOptionPane.YES_OPTION){
            try {
                String sql = "UPDATE case SET "
                    + "case_status = ? WHERE case_id = ? AND case_status = ?";
                
                con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
                pst = con.prepareStatement(sql);

                pst.setString(1, "cancelled");
                pst.setInt(2, caseId);
                pst.setString(3, "pending");
                
                int isDone = pst.executeUpdate();
                con.close();
                if (isDone == 1)
                    JOptionPane.showMessageDialog(this, "Case with ID " + caseId + " is Rejected");
                else
                    JOptionPane.showMessageDialog(this, "You can ONLY reject Pending cases!!");
                clearAll();
                showTableUsers();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
        else
            clearAll();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.MyButton addUserButton;
    private swing.MyButton clearButton;
    private swing.MyButton deleteUserButton;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel lableE;
    private javax.swing.JLabel lableEmail;
    private javax.swing.JLabel lableFname;
    private javax.swing.JLabel lableLname;
    private javax.swing.JLabel lableNid;
    private javax.swing.JLabel lableNumber;
    private javax.swing.JLabel lablePass;
    private javax.swing.JLabel lableUser;
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
