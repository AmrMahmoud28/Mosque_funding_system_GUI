
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
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
public class UserProfile extends javax.swing.JPanel {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private int national_id;

    public UserProfile(int national_id) {
        this.national_id = national_id;

        initComponents();
        showTableCases();
        getMosques();
        getCategoris();
        System.out.println("User");
    }

    // ***************************************************************
    public String getTxtCategory() {
        return txtCategory.getSelectedItem().toString();
    }

    public String getTxtDesc() {
        return txtDesc.getText().trim();
    }

    public void setTxtDesc(String txtDesc) {
        this.txtDesc.setText(txtDesc);
    }

    public String getTxtId() {
        return txtId.getText().trim();
    }

    public void setTxtId(String txtId) {
        this.txtId.setText(txtId);
    }

    public String getTxtMosque() {
        return txtMosque.getSelectedItem().toString();
    }

    // ***************************************************************
    // ***************************************************************
    public String getLableEmail() {
        return lableEmail.getText();
    }

    public void setLableEmail(String lableEmail) {
        this.lableEmail.setText(lableEmail);
    }

    public String getLableName() {
        return lableName.getText();
    }

    public void setLableName(String lableName) {
        this.lableName.setText(lableName);
    }

    public String getLableUsername() {
        return lableUsername.getText();
    }

    public void setLableUsername(String lableUsername) {
        this.lableUsername.setText(lableUsername);
    }

    // ***************************************************************
    // ***************************************************************
    public int getNational_id() {
        return national_id;
    }

    public void setNational_id(int national_id) {
        this.national_id = national_id;
    }

    public void addEventLogout(ActionListener event) {
        logoutButton.addActionListener(event);
    }

    // ***************************************************************
    private void showTableCases() {
        try {
            String sql = "SELECT * FROM "
                    + "(SELECT case_id, case_status, category_name, mosque_name, goal_amount, case_desc, case_date, user_id "
                    + "FROM case, category, mosque "
                    + "WHERE user_id = ? AND case.category_id = category.category_id AND case.mosque_id = mosque.mosque_id "
                    + "UNION "
                    + "SELECT case_id, case_status, category_name, mosque_name, goal_amount, case_desc, case_date, user_id "
                    + "FROM case, category, mosque "
                    + "WHERE case_status = ? AND case.category_id = category.category_id AND case.mosque_id = mosque.mosque_id) "
                    + "ORDER BY 1 DESC";

            con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
            pst = con.prepareStatement(sql);

            pst.setInt(1, getNational_id());
            pst.setString(2, "active");

            rs = pst.executeQuery();

            tableCase.setModel(DbUtils.resultSetToTableModel(rs));
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void getMosques() {
        try {
            String sql = "SELECT * FROM mosque";
            con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                String mosqueName = rs.getString(2);
                txtMosque.addItem(mosqueName);
            }
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void getCategoris() {
        try {
            String sql = "SELECT * FROM category";
            con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                String categoryName = rs.getString(2);
                txtCategory.addItem(categoryName);
            }
            con.close();
        } catch (Exception e) {
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
        txtMosque = new javax.swing.JComboBox<>();
        txtCategory = new javax.swing.JComboBox<>();
        txtDesc = new swing.MyTextField();
        lableName = new javax.swing.JLabel();
        lableUsername = new javax.swing.JLabel();
        lableEmail = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtId = new swing.MyTextField();
        createCaseButton = new swing.MyButton();
        deleteCaseButton = new swing.MyButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCase = new javax.swing.JTable();
        logoutButton = new swing.MyButton();

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
        jLabel4.setText("Mosque");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(68, 68, 68));
        jLabel6.setText("Category");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(68, 68, 68));
        jLabel7.setText("Description");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(68, 68, 68));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Welcome to MFS");

        lableName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableName.setForeground(new java.awt.Color(68, 68, 68));
        lableName.setText("Name: Amr Mahmoud");

        lableUsername.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableUsername.setForeground(new java.awt.Color(68, 68, 68));
        lableUsername.setText("Username: AMRak28");

        lableEmail.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lableEmail.setForeground(new java.awt.Color(68, 68, 68));
        lableEmail.setText("Email: dramr2852001@gmail.com");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(68, 68, 68));
        jLabel9.setText("Case ID");

        createCaseButton.setBackground(new java.awt.Color(124, 228, 249));
        createCaseButton.setText("Create Case");
        createCaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createCaseButtonActionPerformed(evt);
            }
        });

        deleteCaseButton.setBackground(new java.awt.Color(124, 228, 249));
        deleteCaseButton.setText("Delete Case");
        deleteCaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCaseButtonActionPerformed(evt);
            }
        });

        tableCase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Case ID", "Case Description", "Case Date", "Case Status", "Goal Amount", "Category", "Mosque", "User ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableCase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCaseMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCase);

        logoutButton.setBackground(new java.awt.Color(124, 228, 249));
        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
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
                .addComponent(lableUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lableEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(createCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(deleteCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7))
                            .addComponent(jLabel9))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMosque, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCategory, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE)
                            .addComponent(txtDesc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1)
                        .addGap(36, 36, 36))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableName)
                    .addComponent(lableUsername)
                    .addComponent(lableEmail))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMosque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteCaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void createCaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createCaseButtonActionPerformed
        if (getTxtId().equals("")) {
            String[] statuses = {"pending", "active", "cancelled", "completed"};
            int randomNumber = (int) (Math.random() * statuses.length);
            String status = statuses[randomNumber];
            double amount = (int) (Math.random() * (1000001 - 200) + 200);

            if (status.equals("pending") || status.equals("cancelled")) {
                amount = 0;
            }

            try {
                String getCategoryId = "SELECT category_id FROM category WHERE category_name = ?";
                String getMosqueId = "SELECT mosque_id FROM mosque WHERE mosque_name = ?";

                String sql = "INSERT INTO case"
                        + "(case_id, case_desc, case_status, goal_amount, category_id, mosque_id, user_id) "
                        + "VALUES(case_seq.nextval, ?, ?, ?, (" + getCategoryId + "), (" + getMosqueId + "), ?)";

                con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
                pst = con.prepareStatement(sql);

                pst.setString(1, getTxtDesc());
                pst.setString(2, status);
                pst.setDouble(3, amount);
                pst.setString(4, getTxtCategory());
                pst.setString(5, getTxtMosque());
                pst.setInt(6, getNational_id());

                pst.executeUpdate();
                con.close();
                JOptionPane.showMessageDialog(this, "Case is Created successfully");
                showTableCases();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Case ID Should be Empty!!");
        }
        this.setTxtId("");
        this.setTxtDesc("");
    }//GEN-LAST:event_createCaseButtonActionPerformed

    private void deleteCaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCaseButtonActionPerformed
        if(!getTxtId().equals("")){
            try {
                String sql = "DELETE FROM case WHERE case_id = ? AND case_status IN ('pending', 'cancelled') AND user_id = ?";
                con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
                pst = con.prepareStatement(sql);
                
                pst.setInt(1, Integer.parseInt(getTxtId()));
                pst.setInt(2, getNational_id());
                
                int isDone = pst.executeUpdate();
                con.close();
                if (isDone == 1)
                    JOptionPane.showMessageDialog(this, "Case was Deleted successfully");
                else
                    JOptionPane.showMessageDialog(this, "You can ONLY Delete your Cases that is Pending or Cancelled Status");
                showTableCases();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
        else
            JOptionPane.showMessageDialog(this, "Please Enter your Case ID!!");
        
        this.setTxtId("");
        this.setTxtDesc("");
    }//GEN-LAST:event_deleteCaseButtonActionPerformed

    private void loginbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbutton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton2ActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void tableCaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCaseMouseClicked
        int row = tableCase.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tableCase.getModel();
        
        setTxtId(model.getValueAt(row, 0).toString());
    }//GEN-LAST:event_tableCaseMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.MyButton createCaseButton;
    private swing.MyButton deleteCaseButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lableEmail;
    private javax.swing.JLabel lableName;
    private javax.swing.JLabel lableUsername;
    private swing.MyButton loginbutton2;
    private swing.MyButton logoutButton;
    private javax.swing.JTable tableCase;
    private javax.swing.JComboBox<String> txtCategory;
    private swing.MyTextField txtDesc;
    private swing.MyTextField txtId;
    private javax.swing.JComboBox<String> txtMosque;
    // End of variables declaration//GEN-END:variables
}
