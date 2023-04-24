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
public class SignupTest {
    
    @Test
    public void testSignupWithExistedAccount(){
        Main main = new Main();
        
        main.signup.setTxtNational("1122334455");
        main.signup.setTxtUser("amr");
        main.signup.setTxtEmail("test@gmai.com");
        main.signup.setTxtFName("Amr");
        main.signup.setTxtLName("Mahmoud");
        main.signup.setTxtPass("123");
        main.signup.setTxtPhone("0562643483");
        
        assertFalse(main.startSignup());
    }
    
    @Test
    public void testSignupWithInvalidPhoneNumber(){
        Main main = new Main();
        
        main.signup.setTxtNational("1122334455");
        main.signup.setTxtUser("amr");
        main.signup.setTxtEmail("test@gmai.com");
        main.signup.setTxtFName("Amr");
        main.signup.setTxtLName("Mahmoud");
        main.signup.setTxtPass("123");
        main.signup.setTxtPhone("Invalid Phone Number");
        
        assertFalse(main.startSignup());
    }
}
