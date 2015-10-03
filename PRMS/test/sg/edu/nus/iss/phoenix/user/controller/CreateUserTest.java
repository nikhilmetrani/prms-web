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
public class CreateUserTest {
    
    @Test
    public void createUserCmdTest() {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        String id = "test_three";
        String name = "test_three";
        String password = "password1234";
        String[] roles = new String[]{"manager", "producer"};

        when(req.getSession()).thenReturn(session);
        try {
            // Create the user
            when(req.getParameter("name")).thenReturn(name);
            when(req.getParameter("id")).thenReturn(id);
            when(req.getParameter("password")).thenReturn(password);
            when(req.getParameterValues("role")).thenReturn(roles);
            new CreateUserCmd().perform(null, req, res);
            
            // Retrieve the created user from db
            UserDaoImpl dao = new UserDaoImpl();
            User toCheck = dao.getObject("test_three");
            
            // Check if the user created properly
            Assert.assertEquals(toCheck.getId(), id);
            Assert.assertEquals(toCheck.getName(), name);
            Assert.assertEquals(toCheck.getPassword(), password);
            Assert.assertTrue(toCheck.hasRole("manager"));
            
        } catch (Exception e) {
            assert(false);
        }
        
    }
    
}
