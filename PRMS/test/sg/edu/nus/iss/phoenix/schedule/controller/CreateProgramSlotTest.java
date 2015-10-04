/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import static org.mockito.Mockito.*;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualScheduleList;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.exception.ProgramSlotException;
import sg.edu.nus.iss.phoenix.schedule.service.ProgramSlotService;

/**
 *
 * @author Rushabh Shah
 *
 */
public class CreateProgramSlotTest {

    private HttpServletRequest req;
    private HttpServletResponse res;
    private HttpSession session;
    private AnnualScheduleList aschList;
    private String year;
    private String weekStDate;
    private String name;
    private String programDate;
    private String startTime;
    private String duration;
    private ProgramSlotService programSlotService;
    private List<String> availableDates;
    private ProgramSlot programSlot;

    @Before
    public void setUp() {
        req = mock(HttpServletRequest.class);
        res = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        aschList = new ScheduleDelegate().reviewSelectAnnualSchedule();
        year = "2015";
        weekStDate = "05-01-2015";
        name = "news";
        programDate = "10-01-2015";
        startTime = "02:30:00";
        duration = "00:30:00";
        programSlotService = new ProgramSlotService();
        availableDates = new ArrayList<>();

        availableDates.add("05-01-2015");
        availableDates.add("06-01-2015");
        availableDates.add("07-01-2015");
        availableDates.add("08-01-2015");
        availableDates.add("09-01-2015");
        availableDates.add("10-01-2015");
        availableDates.add("11-01-2015");
        
        programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
        if (programSlot != null) {
            programSlotService.processDelete(programSlot);
        }

        programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
        Assert.assertNull(programSlot);
    }

    @After
    public void tearDown() {
        programSlotService.processDelete(programSlot);
        programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
        Assert.assertNull(programSlot);   
    }

    @Test
    public void createProgramSlotDetailsCmdTest() {       

        when(req.getSession()).thenReturn(session);
        try {
            //Select radioProgram
            when(req.getParameter("radioProgram")).thenReturn(name);
            //Select program date
            when(req.getParameter("programDate")).thenReturn(programDate);
            //Select startTime
            when(req.getParameter("startTime")).thenReturn(startTime);
            //Select pgmSlotDuration
            when(req.getParameter("pgmSlotDuration")).thenReturn(duration);

            //Select program name
            when(session.getAttribute("radioPgmName")).thenReturn(name);
            //Select program date
            when(session.getAttribute("selectPgmDate")).thenReturn(programDate);

            //Select program date
            when(session.getAttribute("availableDates")).thenReturn(availableDates);
            new CreateProgramSlotCmd().perform(null, req, res);
            new EnterProgramSlotDetailsCmd().perform(null, req, res);

            programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);

            Assert.assertNotNull(programSlot);
            Assert.assertEquals(name, programSlot.getProgramName());
            Assert.assertEquals(duration, programSlot.getDuration());           

        } catch (Exception e) {
            assert (false);
        }
    }
    
    
     @Test
    public void validationCheckForProgramSlotOverlappingTest() throws IOException, ServletException {       

        when(req.getSession()).thenReturn(session);
        
            //Select radioProgram
            when(req.getParameter("radioProgram")).thenReturn(name);
            //Select program date
            when(req.getParameter("programDate")).thenReturn(programDate);
            //Select startTime
            when(req.getParameter("startTime")).thenReturn(startTime);
            //Select pgmSlotDuration
            when(req.getParameter("pgmSlotDuration")).thenReturn(duration);

            //Select program name
            when(session.getAttribute("radioPgmName")).thenReturn(name);
            //Select program date
            when(session.getAttribute("selectPgmDate")).thenReturn(programDate);

            //Select program date
            when(session.getAttribute("availableDates")).thenReturn(availableDates);
            new EnterProgramSlotDetailsCmd().perform(null, req, res);

            programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);

            Assert.assertNotNull(programSlot);
            Assert.assertEquals(name, programSlot.getProgramName());
            Assert.assertEquals(duration, programSlot.getDuration());
            
            String pgmName = "ppk";           
            
            programSlot.setProgramName(pgmName);        
            
            try {
                programSlotService.validateProgramSlot(programSlot);
            } catch (ProgramSlotException ex) {
                Assert.assertEquals("Program Slots are overlapping, Please change the start time", ex.getMessage());
            }                
    }
    
    
    @Test
    public void validation_For_ProgramSlot_With_Duration_LessThan_ThirtyMinutes_Test() throws IOException, ServletException {

        when(req.getSession()).thenReturn(session);
        
            //Select radioProgram
            when(req.getParameter("radioProgram")).thenReturn(name);
            //Select program date
            when(req.getParameter("programDate")).thenReturn(programDate);
            //Select startTime
            when(req.getParameter("startTime")).thenReturn(startTime);
            //Select pgmSlotDuration
            when(req.getParameter("pgmSlotDuration")).thenReturn(duration);

            //Select program name
            when(session.getAttribute("radioPgmName")).thenReturn(name);
            //Select program date
            when(session.getAttribute("selectPgmDate")).thenReturn(programDate);

            //Select program date
            when(session.getAttribute("availableDates")).thenReturn(availableDates);
            new EnterProgramSlotDetailsCmd().perform(null, req, res);

            programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);

            Assert.assertNotNull(programSlot);
            Assert.assertEquals(name, programSlot.getProgramName());
            Assert.assertEquals(duration, programSlot.getDuration());            
             
            String dur = "00:20:00";                 
            programSlot.setDuration(dur);
            
            try {
                programSlotService.validateProgramSlot(programSlot);
            } catch (ProgramSlotException ex) {
                Assert.assertEquals("Minimum Duration of time should be 30 minutes", ex.getMessage());
            }   
    }
    
    
    @Test
    public void validation_For_ProgramSlot_With_Duration_Not_Multiple_Of_ThirtyMinutes_Test() throws IOException, ServletException {

        when(req.getSession()).thenReturn(session);
        
            //Select radioProgram
            when(req.getParameter("radioProgram")).thenReturn(name);
            //Select program date
            when(req.getParameter("programDate")).thenReturn(programDate);
            //Select startTime
            when(req.getParameter("startTime")).thenReturn(startTime);
            //Select pgmSlotDuration
            when(req.getParameter("pgmSlotDuration")).thenReturn(duration);

            //Select program name
            when(session.getAttribute("radioPgmName")).thenReturn(name);
            //Select program date
            when(session.getAttribute("selectPgmDate")).thenReturn(programDate);

            //Select program date
            when(session.getAttribute("availableDates")).thenReturn(availableDates);
            new EnterProgramSlotDetailsCmd().perform(null, req, res);

            programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);

            Assert.assertNotNull(programSlot);
            Assert.assertEquals(name, programSlot.getProgramName());
            Assert.assertEquals(duration, programSlot.getDuration());
            
            String dur = "00:35:00";              
            programSlot.setDuration(dur);
            
            try {
                programSlotService.validateProgramSlot(programSlot);
            } catch (ProgramSlotException ex) {
                Assert.assertEquals("Duration must be multiple of 30 minutes", ex.getMessage());
            }          
            
    }
    
    @Test
    public void validation_For_ProgramSlot_With_StartTime_Extending_To_NextWeek_Test() throws IOException, ServletException {

        when(req.getSession()).thenReturn(session);
        
            //Select radioProgram
            when(req.getParameter("radioProgram")).thenReturn(name);
            //Select program date
            when(req.getParameter("programDate")).thenReturn(programDate);
            //Select startTime
            when(req.getParameter("startTime")).thenReturn(startTime);
            //Select pgmSlotDuration
            when(req.getParameter("pgmSlotDuration")).thenReturn(duration);

            //Select program name
            when(session.getAttribute("radioPgmName")).thenReturn(name);
            //Select program date
            when(session.getAttribute("selectPgmDate")).thenReturn(programDate);

            //Select program date
            when(session.getAttribute("availableDates")).thenReturn(availableDates);
            new EnterProgramSlotDetailsCmd().perform(null, req, res);

            programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);

            Assert.assertNotNull(programSlot);
            Assert.assertEquals(name, programSlot.getProgramName());
            Assert.assertEquals(duration, programSlot.getDuration());                   
            
            String stDate = "11-01-2015";
            String stTime = "23:30:00";
            String dur = "00:30:00";   
            programSlot.setStartTime(stTime);
            programSlot.setDateOfProgram(stDate);
            programSlot.setDuration(dur);         
            
            try {
                programSlotService.validateProgramSlot(programSlot);
            } catch (ProgramSlotException ex) {
                Assert.assertEquals("Program slot cannot span to next week", ex.getMessage());
            }   
            
            programSlot.setDateOfProgram(programDate);
            programSlot.setStartTime(startTime);
            
    }

}
