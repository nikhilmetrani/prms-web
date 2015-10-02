/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import java.util.Calendar;
import static org.mockito.Mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nkhil Metrani
 */
public class CreateAnnualScheduleTest {
    
    HttpServletRequest request;
    HttpServletResponse response;
    HttpSession session;
    int scheduleYearPast;
    int scheduleYearCurrent;
    int scheduleYearFuture;
    String userName;
    
    public CreateAnnualScheduleTest() {
    }
    
    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        scheduleYearPast = 2013;
        scheduleYearCurrent = Calendar.YEAR;
        scheduleYearFuture = 2019;
        userName = "Mockito";
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
    public void createAnnualScheduleForPastYearTest(){
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("assignedBy")).thenReturn(userName);
        when(request.getParameter("year")).thenReturn((String.valueOf(scheduleYearPast)));
        new EnterAnnualScheduleDetalisCmd().perform(null, request, response);
        String errorMessage = (String)request.getAttribute("errorMessage");
        if (null == errorMessage) {
            assert(true);
        } else {
            if ("".equals(errorMessage))
                fail("EnterAnnualScheduleDetalisCmd should have failed for past year");
            else
                assert(true);
        }
    }
    
    @Test
    public void createAnnualScheduleForCurrentYearTest(){
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("assignedBy")).thenReturn(userName);
        when(request.getParameter("year")).thenReturn((String.valueOf(scheduleYearCurrent)));
        new EnterAnnualScheduleDetalisCmd().perform(null, request, response);
        String errorMessage = (String)request.getAttribute("errorMessage");
        if (null == errorMessage) {
            assert(true);
        } else {
            if (!"".equals(errorMessage))
                fail("Unexpected error: " + errorMessage);
            else
                assert(true);
        }
            
    }
    
    @Test
    public void createAnnualScheduleForFutureYearTest(){
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("assignedBy")).thenReturn(userName);
        when(request.getParameter("year")).thenReturn((String.valueOf(scheduleYearFuture)));
        new EnterAnnualScheduleDetalisCmd().perform(null, request, response);
        assert(true);
        String errorMessage = (String)request.getAttribute("errorMessage");
        if (null == errorMessage) {
            assert(true);
        } else {
            if (!"".equals(errorMessage))
                fail("Unexpected error: " + errorMessage);
            else
                assert(true);
        }
    }
    
    @Test
    public void createAnnualScheduleForExistingYearTest(){
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("assignedBy")).thenReturn(userName);
        when(request.getParameter("year")).thenReturn((String.valueOf(scheduleYearFuture)));
        new EnterAnnualScheduleDetalisCmd().perform(null, request, response);
        String errorMessage = (String)request.getAttribute("errorMessage");
        if (null == errorMessage) {
            assert(true);
        } else {
            if ("".equals(errorMessage))
                fail("EnterAnnualScheduleDetalisCmd should have failed for existing year");
            else
                assert(true);
        }
    }
}
