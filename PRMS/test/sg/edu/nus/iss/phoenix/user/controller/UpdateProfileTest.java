/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.controller;

import java.sql.SQLException;
import static org.mockito.Mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author Nikhil Metrani
 */
public class UpdateProfileTest {
    
    HttpServletRequest request;
    HttpServletResponse response;
    HttpSession session;
    UserDao userDao;
    String errorMessage;
    String managerId, producerId, presenterId;
    User manager, producer, presenter;
    
    public UpdateProfileTest() {
    }
    
    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        userDao = new UserDaoImpl();
        managerId = "pointyhead";
        producerId = "dogbert";
        presenterId = "dilbert";
        errorMessage = "";
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void returnLoginPageWhenUserNotLoggedInTest() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);
        String returnValue = new UpdateProfileCmd().perform(null, request, response);
        if (!"/pages/login.jsp".equals(returnValue))
            fail("Expected re-direct to login page");
        else
            assert(true);
    }
    
    @Test
    public void failWhenUserIsNotProducerTest() {
        when(request.getSession()).thenReturn(session);
        when(request.getAttribute("errorMessage")).thenReturn(errorMessage);
        try {
            manager = userDao.getObject(managerId);
            when(session.getAttribute("user")).thenReturn(manager);
            String returnValue = new UpdateProfileCmd().perform(null, request, response);
            assertEquals("/nocturne/viewempdetails?error=failed", returnValue);
        } catch (NotFoundException | SQLException ex) {
            fail("Unexpected error: " + ex.getMessage());
        }
    }
    
    @Test
    public void failWhenUserIsNotPresenterTest() {
        when(request.getSession()).thenReturn(session);
        try {
            manager = userDao.getObject(managerId);
            when(session.getAttribute("user")).thenReturn(manager);
            String returnValue = new UpdateProfileCmd().perform(null, request, response);
            if (!"/nocturne/viewempdetails?error=failed".equals(returnValue))
                fail("UpdateProfileCmd should have failed user without Producer/Presenter role");
            else
                assert(true);
        } catch (NotFoundException | SQLException ex) {
            fail("Unexpected error: " + ex.getMessage());
        }
    }
    
    @Test
    public void updatePresenterProfileTest() {
        when(request.getSession()).thenReturn(session);
        try {
            presenter = userDao.getObject(presenterId);
            when(session.getAttribute("user")).thenReturn(presenter);

            when(request.getParameter("name")).thenReturn("dilbert, the presenter");
            when(request.getParameter("email")).thenReturn("dilbert@prms.com");
            when(request.getParameter("phoneNo")).thenReturn("+659735467");
            when(request.getParameter("siteLink")).thenReturn("http://dilbert.prms.com");
            
            String returnValue = new UpdateProfileCmd().perform(null, request, response);
            assertEquals("/nocturne/viewempdetails", returnValue);
            
            User updatedUser = userDao.getObject(presenterId);
            
            assertEquals("dilbert, the presenter", updatedUser.getName());
            assertEquals("dilbert@prms.com", updatedUser.getEmail());
            assertEquals("+659735467", updatedUser.getPhoneNumber());
            assertEquals("http://dilbert.prms.com", updatedUser.getProfile().getSiteLink());
            
        } catch (NotFoundException | SQLException ex) {
            fail("Unexpected error: " + ex.getMessage());
        }
    }
    
    @Test
    public void updateProduerProfileTest() {
        when(request.getSession()).thenReturn(session);
        try {
            producer = userDao.getObject(producerId);
            when(session.getAttribute("user")).thenReturn(producer);
            
            when(request.getParameter("name")).thenReturn("dogbert, the producer");
            when(request.getParameter("email")).thenReturn("dogbert.producer@prms.com");
            when(request.getParameter("phoneNo")).thenReturn("+659766467");
            when(request.getParameter("siteLink")).thenReturn("http://dogbert-producer.prms.com");
            
            String returnValue = new UpdateProfileCmd().perform(null, request, response);
            assertEquals("/nocturne/viewempdetails", returnValue);
            
            User updatedUser = userDao.getObject(producerId);
            
            assertEquals("dogbert, the producer", updatedUser.getName());
            assertEquals("dogbert.producer@prms.com", updatedUser.getEmail());
            assertEquals("+659766467", updatedUser.getPhoneNumber());
            assertEquals("http://dogbert-producer.prms.com", updatedUser.getProfile().getSiteLink());
            
        } catch (NotFoundException | SQLException ex) {
            fail("Unexpected error: " + ex.getMessage());
        }
    }
}
