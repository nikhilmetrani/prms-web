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
public class DeleteUserTest {
    
    @Test
    public void deleteUserCmdTest() {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        String id = "test_three";
        String name = "test_three";

        when(req.getSession()).thenReturn(session);
        try {
            // Delete the user
            when(req.getParameter("name")).thenReturn(name);
            when(req.getParameter("id")).thenReturn(id);
            new DeleteUserCmd().perform(null, req, res);
            
            // Check if the deleted user still active
            UserDaoImpl dao = new UserDaoImpl();
            User toDelete = dao.getObject("test_three");
            
            Assert.assertEquals(false, toDelete.isActiveUserFlag());
            
        } catch (Exception e) {
            assert(false);
        }
    }
}
