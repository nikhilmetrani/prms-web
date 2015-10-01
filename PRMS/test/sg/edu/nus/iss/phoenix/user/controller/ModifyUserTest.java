/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author User
 */
public class ModifyUserTest {
    
    @Test
    public void modifyUserCmdTest() {
        
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        
        String newName = "test_three_modified";
        String password = "password1234";
        String[] newRoles = new String[]{"manager", "producer", "presenter"};

        when(req.getSession()).thenReturn(session);
        try {
            UserDaoImpl dao = new UserDaoImpl();
            User toModify = dao.getObject("test_three");
            when(req.getAttribute("user")).thenReturn(toModify);
            
            when(req.getAttribute("name")).thenReturn(newName);
            when(req.getAttribute("id")).thenReturn("test_three");
            when(req.getAttribute("role")).thenReturn(newRoles);
            new ModifyUserCmd().perform(null, req, res);
            
            User modified = dao.getObject("test_three");
            Assert.assertEquals(newName, modified.getName());
            Assert.assertTrue(modified.hasRole("presenter"));
        } catch (Exception e) {
            
        }
    
    }

}
