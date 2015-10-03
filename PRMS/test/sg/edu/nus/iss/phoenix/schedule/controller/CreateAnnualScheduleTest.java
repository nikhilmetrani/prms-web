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
        scheduleYearCurrent = Calendar.getInstance().get(Calendar.YEAR);
        scheduleYearFuture = 2019;
        userName = "pointyhead";
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
        String returnValue = new EnterAnnualScheduleDetalisCmd().perform(null, request, response);
        assertEquals("/nocturne/createas", returnValue);
    }
    
    @Test
    public void createAnnualScheduleForCurrentYearTest(){
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("assignedBy")).thenReturn(userName);
        when(request.getParameter("year")).thenReturn((String.valueOf(scheduleYearCurrent)));
        String returnValue = new EnterAnnualScheduleDetalisCmd().perform(null, request, response);
        assertEquals("/nocturne/viewSchedule", returnValue);
    }
    
    @Test
    public void createAnnualScheduleForFutureYearTest(){
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("assignedBy")).thenReturn(userName);
        when(request.getParameter("year")).thenReturn((String.valueOf(scheduleYearFuture)));
        String returnValue = new EnterAnnualScheduleDetalisCmd().perform(null, request, response);
        assertEquals("/nocturne/viewSchedule", returnValue);
    }
    
    @Test
    public void createAnnualScheduleForExistingYearTest(){
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("assignedBy")).thenReturn(userName);
        when(request.getParameter("year")).thenReturn((String.valueOf(scheduleYearFuture)));
        String returnValue = new EnterAnnualScheduleDetalisCmd().perform(null, request, response);
        assertEquals("/nocturne/createas", returnValue);
    }
}
