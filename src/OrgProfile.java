
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
    private boolean showPending = false;
    
    public OrgProfile(int org_id) {
        this.org_id = org_id;
        
        initComponents();
        showTableUsers();
        txtGoal.setEchoChar((char) 0);
        System.out.println("Org");
    }
    
    // ***************************************************************

    public String getTxtDesc() {
        return txtDesc.getText().trim();
    }

    public void setTxtDesc(String txtDesc) {
        this.txtDesc.setText(txtDesc);
    }

    public String getTxtCate() {
        return txtCate.getText().trim();
    }

    public void setTxtCate(String txtCate) {
        this.txtCate.setText(txtCate);
    }

    public String getTxtId() {
        return txtId.getText().trim();
    }

    public void setTxtId(String txtId) {
        this.txtId.setText(txtId);
    }

    public String getTxtMosque() {
        return txtMosque.getText().trim();
    }

    public void setTxtMosque(String txtMosque) {
        this.txtMosque.setText(txtMosque);
    }

    public String getTxtGoal() {
        return String.valueOf(txtGoal.getPassword()).trim();
    }

    public void setTxtGoal(String txtGoal) {
        this.txtGoal.setText(txtGoal);
    }

    public String getTxtAdmin() {
        return txtAdmin.getText().trim();
    }

    public void setTxtAdmin(String txtAdmin) {
        this.txtAdmin.setText(txtAdmin);
    }

    public String getTxtStatus() {
        return txtStatus.getText().trim();
    }

    public void setTxtStatus(String txtStatus) {
        this.txtStatus.setText(txtStatus);
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
        return getTxtAdmin().equals("")
                || getTxtCate().equals("")
                || getTxtId().equals("")
                || getTxtDesc().equals("")
                || getTxtGoal().equals("")
                || getTxtMosque().equals("")
                || getTxtStatus().equals("");
    }
    
    private void clearAll(){
        setTxtAdmin("");
        setTxtCate("");
        setTxtId("");
        setTxtDesc("");
        setTxtGoal("");
        setTxtMosque("");
        setTxtStatus("");
    }
    
    // ***************************************************************
    
    private void showTableUsers(){
        try {
            if(showPending){
                String sql = "SELECT case_id, case_status, category_name, mosque_name, goal_amount, case_desc, case_date "
                    + "FROM case, category, mosque "
                    + "WHERE case_status = ? AND case.category_id = category.category_id AND case.mosque_id = mosque.mosque_id "
                    + "ORDER BY CASE "
                        + "WHEN case_status = 'active' THEN 1 "
                        + "WHEN case_status = 'pending' THEN 2 "
                        + "WHEN case_status = 'completed' THEN 3 "
                        + "ELSE 4 "
                    + "END, case_id DESC";

                con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
                pst = con.prepareStatement(sql);

                pst.setString(1, "pending");
            }
            else{
                String sql = "SELECT case.case_id, case_status, category_name, mosque_name, goal_amount, "
                    + "NVL(SUM(donation_amount), 0) AS Raise, "
                    + "case_desc, case_date, adminno "
                    + "FROM case, donation, category, mosque, works_on "
                    + "WHERE orgno = ? AND case.case_id = caseno AND case.category_id = category.category_id AND case.case_id = donation.case_id(+) AND case.mosque_id = mosque.mosque_id "
                    + "GROUP BY case.case_id, case_status, category_name, mosque_name, goal_amount, case_desc, case_date, adminno "
                    + "ORDER BY CASE "
                        + "WHEN case_status = 'active' THEN 1 "
                        + "WHEN case_status = 'pending' THEN 2 "
                        + "WHEN case_status = 'completed' THEN 3 "
                        + "ELSE 4 "
                    + "END, case_id DESC";
                con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
                pst = con.prepareStatement(sql);
                
                pst.setInt(1, getOrg_id());
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
        lableStatus = new javax.swing.JLabel();
        lableCate = new javax.swing.JLabel();
        lableMosque = new javax.swing.JLabel();
        labelTitle = new javax.swing.JLabel();
        txtMosque = new swing.MyTextField();
        labelName = new javax.swing.JLabel();
        labelId = new javax.swing.JLabel();
        lableEmail = new javax.swing.JLabel();
        lableCid = new javax.swing.JLabel();
        txtId = new swing.MyTextField();
        acceptCaseButton = new swing.MyButton();
        rejectCaseButton = new swing.MyButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        logoutButton = new swing.MyButton();
        txtStatus = new swing.MyTextField();
        txtCate = new swing.MyTextField();
        lableGoal = new javax.swing.JLabel();
        lableDesc = new javax.swing.JLabel();
        txtDesc = new swing.MyTextField();
        lableAdmin = new javax.swing.JLabel();
        txtAdmin = new swing.MyTextField();
        txtGoal = new swing.MyPassword();
        clearButton = new swing.MyButton();
        toggleButton = new swing.MyButton();
        updateGoalButton = new swing.MyButton();

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

        lableStatus.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableStatus.setForeground(new java.awt.Color(68, 68, 68));
        lableStatus.setText("Case Status");

        lableCate.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableCate.setForeground(new java.awt.Color(68, 68, 68));
        lableCate.setText("Category");

        lableMosque.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableMosque.setForeground(new java.awt.Color(68, 68, 68));
        lableMosque.setText("Mosque");

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

        lableCid.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableCid.setForeground(new java.awt.Color(68, 68, 68));
        lableCid.setText("Case ID");

        acceptCaseButton.setBackground(new java.awt.Color(124, 228, 249));
        acceptCaseButton.setText("Accept Case");
        acceptCaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptCaseButtonActionPerformed(evt);
            }
        });

        rejectCaseButton.setBackground(new java.awt.Color(124, 228, 249));
        rejectCaseButton.setText("Reject Case");
        rejectCaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectCaseButtonActionPerformed(evt);
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

        txtCate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCateActionPerformed(evt);
            }
        });

        lableGoal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableGoal.setForeground(new java.awt.Color(68, 68, 68));
        lableGoal.setText("Goal//Raise//Rem");

        lableDesc.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableDesc.setForeground(new java.awt.Color(68, 68, 68));
        lableDesc.setText("Description");

        lableAdmin.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableAdmin.setForeground(new java.awt.Color(68, 68, 68));
        lableAdmin.setText("Admin ID");

        clearButton.setBackground(new java.awt.Color(124, 228, 249));
        clearButton.setText("Clear All");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        toggleButton.setBackground(new java.awt.Color(124, 228, 249));
        toggleButton.setText("Pending Cases");
        toggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleButtonActionPerformed(evt);
            }
        });

        updateGoalButton.setBackground(new java.awt.Color(124, 228, 249));
        updateGoalButton.setText("Update Goal");
        updateGoalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateGoalButtonActionPerformed(evt);
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
                            .addComponent(lableCid)
                            .addComponent(lableStatus)
                            .addComponent(lableCate)
                            .addComponent(lableMosque)
                            .addComponent(lableGoal)
                            .addComponent(lableDesc)
                            .addComponent(lableAdmin))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtMosque, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDesc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAdmin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGoal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(36, 36, 36))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(acceptCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(rejectCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(updateGoalButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(lableCid)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableCate, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lableMosque)
                            .addComponent(txtMosque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lableGoal)
                            .addComponent(txtGoal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lableDesc)
                            .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lableAdmin)
                            .addComponent(txtAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acceptCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rejectCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateGoalButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void acceptCaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptCaseButtonActionPerformed
        if(!getTxtId().equals("") && getTxtId().matches("[0-9]+"))
                acceptCase(Integer.parseInt(getTxtId()));
            else
                JOptionPane.showMessageDialog(this, "Please Enter your Case ID!!");
    }//GEN-LAST:event_acceptCaseButtonActionPerformed

    private void rejectCaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectCaseButtonActionPerformed
        if(!getTxtId().equals("") && getTxtId().matches("[0-9]+"))
                rejectCase(Integer.parseInt(getTxtId()));
            else
                JOptionPane.showMessageDialog(this, "Please Enter your Case ID!!");
    }//GEN-LAST:event_rejectCaseButtonActionPerformed

    private void loginbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbutton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton2ActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void txtCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCateActionPerformed

    private void tableUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUserMouseClicked
        int row = tableUser.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
        
        setTxtId(model.getValueAt(row, 0).toString());
        setTxtStatus(model.getValueAt(row, 1).toString());
        setTxtCate(model.getValueAt(row, 2).toString());
        setTxtMosque(model.getValueAt(row, 3).toString());
        setTxtGoal("$" + (showPending? model.getValueAt(row, 4).toString() 
                : (model.getValueAt(row, 4).toString() 
                        + " // $" + model.getValueAt(row, 5).toString() 
                        + " // $" + (Double.parseDouble(model.getValueAt(row, 4).toString()) - Double.parseDouble(model.getValueAt(row, 5).toString())))));
        setTxtDesc(model.getValueAt(row, showPending? 5 : 6) == null ? "" : model.getValueAt(row, showPending? 5 : 6).toString());
        setTxtAdmin(model.getValueAt(row, showPending? 6 : 8).toString());
    }//GEN-LAST:event_tableUserMouseClicked

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clearAll();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void toggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleButtonActionPerformed
        clearAll();
        showPending = !showPending;
        
        toggleButton.setText(showPending? "Our Cases" : "Pending Cases");
        lableGoal.setText(showPending? "Goal Amount" : "Goal//Raise//Rem");
        lableAdmin.setText(showPending? "Date" : "Admin ID");
        
        showTableUsers();
    }//GEN-LAST:event_toggleButtonActionPerformed

    private void updateGoalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateGoalButtonActionPerformed
        if(!getTxtId().equals("") && getTxtId().matches("[0-9]+"))
            updateCase(Integer.parseInt(getTxtId()));
        else
            JOptionPane.showMessageDialog(this, "Please Enter your Case ID!!");
    }//GEN-LAST:event_updateGoalButtonActionPerformed
    
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
                    if(input.matches("^([1-9][0-9]*(?:[\\.][0-9]*)?|0*\\.0*[1-9][0-9]*)(?:[eE][+-][0-9]+)?$")){
                        double goalAmount = Double.parseDouble(input);
                        
                        sql = "UPDATE case SET "
                                + "case_status = ?, goal_amount = ? WHERE case_id = ?";
                        
                        pst = con.prepareStatement(sql);
                        
                        pst.setString(1, "active");
                        pst.setDouble(2, goalAmount);
                        pst.setInt(3, caseId);
                        
                        int isDone = pst.executeUpdate();
                        if (isDone == 1){
                            sql = "INSERT INTO works_on "
                                    + "(caseno, orgno, adminno) "
                                    + "VALUES(?, ?, (SELECT admin_id FROM (SELECT admin_id FROM admin ORDER BY dbms_random.value) WHERE rownum = 1))";
                            
                            pst = con.prepareStatement(sql);
                            
                            pst.setInt(1, caseId);
                            pst.setInt(2, getOrg_id());
                            
                            isDone = pst.executeUpdate();
                            if(isDone == 1)
                                JOptionPane.showMessageDialog(this, "Case with ID " + caseId + " is Accepted\nThe Goal amount is $" + goalAmount);
                            else
                                JOptionPane.showMessageDialog(this, "Something went wrong!!");
                        }
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
    
    private void updateCase(int caseId){
        try {
            String sql = "SELECT case_id, "
                    + "(SELECT SUM(donation_amount) FROM donation WHERE case_id = ?) AS Raise "
                    + "FROM case WHERE case_id = ? AND case_status = ?";
            
            con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
            pst = con.prepareStatement(sql);
            
            pst.setInt(1, caseId);
            pst.setInt(2, caseId);
            pst.setString(3, "active");

            rs = pst.executeQuery();
            if(rs.next()){
                String input = JOptionPane.showInputDialog(this, "Enter the new Goal Amount", "Updating Goal Amount of Case with ID " + caseId, JOptionPane.INFORMATION_MESSAGE);
        
                if(input != null){
                    if(input.matches("^([1-9][0-9]*(?:[\\.][0-9]*)?|0*\\.0*[1-9][0-9]*)(?:[eE][+-][0-9]+)?$")){
                        double goalAmount = Double.parseDouble(input);
                        
                        sql = "UPDATE case SET "
                                + "goal_amount = ? WHERE case_id = ?";
                        
                        pst = con.prepareStatement(sql);
                        
                        pst.setDouble(1, goalAmount);
                        pst.setInt(2, caseId);
                        
                        int isDone = pst.executeUpdate();
                        if (isDone == 1){
                            JOptionPane.showMessageDialog(this, "Goal Amount of Case with ID " + caseId + " is Updated\nThe new Goal amount is $" + goalAmount);
                            
                            int raise = rs.getInt(2);
                            
                            if(raise >= goalAmount){
                                sql = "UPDATE case SET "
                                        + "case_status = ? WHERE case_id = ?";
                                
                                pst = con.prepareStatement(sql);
                        
                                pst.setString(1, "completed");
                                pst.setInt(2, caseId);
                                
                                pst.executeUpdate();
                            }
                        }
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
                JOptionPane.showMessageDialog(this, "You can ONLY update Active cases!!");
                clearAll();
            }
            con.close();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.MyButton acceptCaseButton;
    private swing.MyButton clearButton;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel lableAdmin;
    private javax.swing.JLabel lableCate;
    private javax.swing.JLabel lableCid;
    private javax.swing.JLabel lableDesc;
    private javax.swing.JLabel lableEmail;
    private javax.swing.JLabel lableGoal;
    private javax.swing.JLabel lableMosque;
    private javax.swing.JLabel lableStatus;
    private swing.MyButton loginbutton2;
    private swing.MyButton logoutButton;
    private swing.MyButton rejectCaseButton;
    private javax.swing.JTable tableUser;
    private swing.MyButton toggleButton;
    private swing.MyTextField txtAdmin;
    private swing.MyTextField txtCate;
    private swing.MyTextField txtDesc;
    private swing.MyPassword txtGoal;
    private swing.MyTextField txtId;
    private swing.MyTextField txtMosque;
    private swing.MyTextField txtStatus;
    private swing.MyButton updateGoalButton;
    // End of variables declaration//GEN-END:variables
}
