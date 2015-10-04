/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import static org.mockito.Mockito.*;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.schedule.service.ProgramSlotService;

/**
 *
 * @author Niu Yiming
 * @author Rushabh Shah
 *
 *
 */
public class DeleteProgramSlotTest {

    private HttpServletRequest req;
    private HttpServletResponse res;
    private HttpSession session;
    private String programName;
    private String programDate;
    private String startTime;
    private String duration;
    private String producer;
    private String presenter;
    private String year;
    private ProgramSlotService programSlotService;   
    private ProgramSlot programSlot;    
    private WeeklySchedule weeklySchedule;
    private AnnualSchedule annualSchedule;
    

    @Before
    public void setUp() {
        req = mock(HttpServletRequest.class);
        res = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        year = "2015";
        programName = "news";
        programDate = "10-01-2015";
        startTime = "02:30:00";
        duration = "00:30:00";
        weeklySchedule = new WeeklySchedule("05-01-2015","pointyhead");
        producer = "dilbert";
        presenter = "wally";
        programSlotService = new ProgramSlotService();        
        annualSchedule = new AnnualSchedule(new Integer(year), "pointyhead");       

        programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
        if (programSlot == null) {
            ProgramSlot slot = new ProgramSlot();
            slot.setDateOfProgram(programDate);
            slot.setDuration(duration);
            slot.setPresenter(presenter);
            slot.setProducer(producer);
            slot.setProgramName(programName);
            slot.setStartTime(startTime);
            programSlotService.processCreate(slot);
        }
        programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
        Assert.assertNotNull(programSlot);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void deleteProgramSlotTest() {

        when(req.getSession()).thenReturn(session);

        //Select Annual Schedule
        when(session.getAttribute("annualSchedule")).thenReturn(annualSchedule);
        //Select Weekly Schedule
        when(session.getAttribute("weeklySchedule")).thenReturn(weeklySchedule);
        //Select program date
        when(req.getParameter("programDate")).thenReturn(programDate);
        //Select startTime
        when(req.getParameter("startTime")).thenReturn(startTime);

        try {
            new DeleteProgramSlotCmd().perform(null, req, res);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(DeleteProgramSlotTest.class.getName()).log(Level.SEVERE, null, ex);
            assert (false);
        }
        programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
        Assert.assertNull(programSlot);

    }

}
