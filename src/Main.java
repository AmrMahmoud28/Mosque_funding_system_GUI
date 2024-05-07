
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dramr
 */
public class Main extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    Login login = new Login();
    Signup signup = new Signup();
    UserProfile userProfile;
    AdminProfile adminProfile;
    OrgProfile orgProfile;

    public Main() {
        initComponents();
        setSize(Toolkit.getDefaultToolkit().getScreenSize());

        slide.setAnimate(5);
        slide.init(login, signup);
        login.login();

        login.addEventRegister((ActionEvent ae) -> {
            slide.show(1);
            signup.signup();
        });

        signup.addEventBackLogin((ActionEvent ae) -> {
            slide.show(0);
            login.login();
        });

        login.addEventLogin((ActionEvent ae) -> {
            if (!isTextEmpty(login)) {
                if (login.getTxtUser().contains("admin")) {
                    String[] result = startLogin(0);
                    if (result != null) {
                        adminProfile = new AdminProfile(Integer.parseInt(result[3]));
                        
                        adminProfile.setLableName("Name: " + result[0]);
                        adminProfile.setLableId("ID: " + result[1]);
                        adminProfile.setLableEmail("Email: " + result[2]);
                        
                        slide.init(adminProfile);
                        slide.show(slide.getComponentCount() - 1);
                        
                        adminProfile.addEventLogout((ActionEvent ae1) -> {
                            slide.show(0);
                            login.login();
                            clearAll();
                        });
                    }
                }
                else if(login.getTxtUser().contains("gov")){
                    String[] result = startLogin(1);
                    if (result != null) {
                        orgProfile = new OrgProfile(Integer.parseInt(result[1]));
                        
                        orgProfile.setLabelTitle(result[0].split(" ")[result[0].split(" ").length - 1] + " Organization");
                        orgProfile.setLableName("Name: " + result[0]);
                        orgProfile.setLableId("ID: " + result[1]);
                        orgProfile.setLableEmail("Email: " + result[2]);
                        
                        slide.init(orgProfile);
                        slide.show(slide.getComponentCount() - 1);
                        
                        orgProfile.addEventLogout((ActionEvent ae1) -> {
                            slide.show(0);
                            login.login();
                            clearAll();
                        });
                    }
                }
                else {
                    String[] result = startLogin(2);
                    if (result != null) {
                        userProfile = new UserProfile(Integer.parseInt(result[3]));
                        
                        userProfile.setLableName("Name: " + result[0]);
                        userProfile.setLableUsername("Username: " + result[1]);
                        userProfile.setLableEmail("Email: " + result[2]);
                        
                        slide.init(userProfile);
                        slide.show(slide.getComponentCount() - 1);
                        
                        userProfile.addEventLogout((ActionEvent ae1) -> {
                            slide.show(0);
                            login.login();
                            clearAll();
                        });
                    }
                }
                clearAll();
            }
            else
                JOptionPane.showMessageDialog(null, "Please Enter all Information");
        });

        signup.addEventSignup((ActionEvent ae) -> {
            if (!isTextEmpty(signup)) {
                if(startSignup()){
                    slide.show(0);
                    login.login();
                    JOptionPane.showMessageDialog(null, "Your Account is created successfully\nPlease Login!");
                }
                clearAll();
            }
            else
                JOptionPane.showMessageDialog(null, "Please Enter all Required Information");
        });
    }

    public void clearAll() {
        login.setTxtUser("");
        login.setTxtPass("");

        signup.setTxtEmail("");
        signup.setTxtFName("");
        signup.setTxtLName("");
        signup.setTxtNational("");
        signup.setTxtPass("");
        signup.setTxtPhone("");
        signup.setTxtUser("");

        if (userProfile != null) {
            userProfile.setTxtDesc("");
            userProfile.setTxtId("");
        }

        if (adminProfile != null) {
            adminProfile.setTxtEmail("");
            adminProfile.setTxtFname("");
            adminProfile.setTxtId("");
            adminProfile.setTxtLname("");
            adminProfile.setTxtPass("");
            adminProfile.setTxtPhone("");
            adminProfile.setTxtUsername("");
        }
    }

    private boolean isTextEmpty(Login login) {
        return login.getTxtUser().equals("") || login.getTxtPass().equals("");
    }

    private boolean isTextEmpty(Signup signup) {
        return signup.getTxtNational().equals("")
                || signup.getTxtUser().equals("")
                || signup.getTxtPass().equals("")
                || signup.getTxtEmail().equals("")
                || signup.getTxtFName().equals("")
                || signup.getTxtLName().equals("")
                || signup.getTxtPhone().equals("");
    }

    protected String[] startLogin(int userType) {
        try {
            String sql;
            switch (userType) {
                case 0:
                    sql = "SELECT * FROM admin WHERE admin_email = ? AND password = ?";
                    break;
                case 1:
                    sql = "SELECT * FROM organization WHERE org_email = ? AND org_id = ?";
                    break;
                default:
                    sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                    break;
            }

            con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
            pst = con.prepareStatement(sql);

            pst.setString(1, login.getTxtUser());
            pst.setString(2, login.getTxtPass());

            rs = pst.executeQuery();
            if (rs.next()) {
                String[] result = new String[4];
                switch (userType) {
                    case 0:
                        result[0] = (rs.getString(2));
                        result[1] = rs.getString(1);
                        result[2] = rs.getString(3);
                        result[3] = rs.getString(1);
                        break;
                    case 1:
                        result[0] = (rs.getString(2));
                        result[1] = rs.getString(1);
                        result[2] = rs.getString(3);
                        result[3] = rs.getString(1);
                        break;
                    default:
                        result[0] = (rs.getString(3) + " " + rs.getString(4));
                        result[1] = rs.getString(2);
                        result[2] = rs.getString(6);
                        result[3] = rs.getString(1);
                        break;
                }
                return result;
            } else {
                JOptionPane.showMessageDialog(this, "Wrong Username or Password");
            }
            con.close();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
        login.login();
        return null;
    }
    
    protected boolean startSignup(){
        try {
            String sql = "INSERT INTO users"
                + "(national_id, username, first_name, last_name, password, email, phone_number)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
            
            con = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-TQURACRK:1521:XE", "system", "MarMar28");
            pst = con.prepareStatement(sql);
            
            pst.setString(1, signup.getTxtNational());
            pst.setString(2, signup.getTxtUser());
            pst.setString(3, signup.getTxtFName());
            pst.setString(4, signup.getTxtLName());
            pst.setString(5, signup.getTxtPass());
            pst.setString(6, signup.getTxtEmail());
            pst.setString(7, signup.getTxtPhone());
            
            pst.executeUpdate();
            con.close();
            return true;
        }
        catch (SQLException e) {
            if(e.toString().contains("unique"))
                JOptionPane.showMessageDialog(this, "This Account already exists");
            else if(e.toString().contains("number"))
                JOptionPane.showMessageDialog(this, "Please Enter a valid Information");
            else
                JOptionPane.showMessageDialog(this, e);
            signup.signup();
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGradiente1 = new swing.PanelGradiente();
        panelBorder1 = new swing.PanelBorder();
        slide = new swing.PanelSlide();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelGradiente1.setColorPrimario(new java.awt.Color(147, 232, 249));
        panelGradiente1.setColorSecundario(new java.awt.Color(13, 138, 163));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        slide.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout slideLayout = new javax.swing.GroupLayout(slide);
        slide.setLayout(slideLayout);
        slideLayout.setHorizontalGroup(
            slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 992, Short.MAX_VALUE)
        );
        slideLayout.setVerticalGroup(
            slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 657, Short.MAX_VALUE)
        );

        panelBorder1.setLayer(slide, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(slide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(slide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        exitButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        exitButton.setForeground(new java.awt.Color(255, 0, 0));
        exitButton.setText("X");
        exitButton.setContentAreaFilled(false);
        exitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        panelGradiente1.setLayer(panelBorder1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelGradiente1.setLayer(exitButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panelGradiente1Layout = new javax.swing.GroupLayout(panelGradiente1);
        panelGradiente1.setLayout(panelGradiente1Layout);
        panelGradiente1Layout.setHorizontalGroup(
            panelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGradiente1Layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGradiente1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelGradiente1Layout.setVerticalGroup(
            panelGradiente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGradiente1Layout.createSequentialGroup()
                .addComponent(exitButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private swing.PanelBorder panelBorder1;
    private swing.PanelGradiente panelGradiente1;
    private swing.PanelSlide slide;
    // End of variables declaration//GEN-END:variables
}
