/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import junit.framework.Assert;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author User
 */
public class ResetPasswordTest {
    
    @Test
    public void resetPasswordCmdTest() {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        String newName = "test_three";
        String password = "password1234";

        when(req.getSession()).thenReturn(session);
        try {
            when(req.getParameter("name")).thenReturn(newName);
            when(req.getParameter("id")).thenReturn("test_three");
            when(req.getParameter("old_password")).thenReturn(password);
            when(req.getParameter("new_password")).thenReturn("newpassword1234");
            new ResetPasswordCmd().perform(null, req, res);
            
            UserDaoImpl dao = new UserDaoImpl();
            User toCheck = dao.getObject("test_three");
            Assert.assertEquals("newpassword1234", toCheck.getPassword());
        } catch (Exception e) {
            
        }
        
    }
}
