/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dramr
 */
public class LoginTest {
    
    @Test
    public void testLoginAsUser(){
        Main main = new Main();
        
        main.login.setTxtUser("AMRak28");
        main.login.setTxtPass("123");
        
        String[] actual = main.startLogin(2);
        String[] expected = {"Amr Mahmoud", "AMRak28", "dramr2852001@gmail.com", "1122334455"};
        
        assertArrayEquals(expected, actual);
    } 
    
    @Test
    public void testLoginAsAdmin(){
        Main main = new Main();
        
        main.login.setTxtUser("amr@admin.mfs.com");
        main.login.setTxtPass("123");
        
        String[] actual = main.startLogin(0);
        String[] expected = {"Amr Mahmoud", "43578", "amr@admin.mfs.com", "43578"};
        
        assertArrayEquals(expected, actual);
    }
    
    @Test
    public void testLoginAsOrganization(){
        Main main = new Main();
        
        main.login.setTxtUser("csc@awqaf.gov.sa");
        main.login.setTxtPass("11662");
        
        String[] actual = main.startLogin(1);
        String[] expected = {"General Authority for Awqaf", "11662", "csc@awqaf.gov.sa", "11662"};
        
        assertArrayEquals(expected, actual);
    }
}
