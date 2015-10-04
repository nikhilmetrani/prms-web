/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualScheduleList;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.schedule.service.ProgramSlotService;

/**
 *
 * @author Niu Yiming
 * @author Rushabh Shah
 * 
 */
public class ModifyProgramSlotTest {
    
    private HttpServletRequest req;
    private HttpServletResponse res;
    private HttpSession session;
    private String programName;
    private String programDate;
    private String startTime;
    private String duration;
    private String producerName;
    private String presenterName;
    private String year;
    private ProgramSlotService programSlotService;   
    private ProgramSlot programSlot;    
    private WeeklySchedule weeklySchedule;
    private AnnualSchedule annualSchedule;
    private AnnualScheduleList aschList;
    

    @Before
    public void setUp() {
        req = mock(HttpServletRequest.class);
        res = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        year = "2015";
        programName = "news";
        programDate = "06-01-2015";
        startTime = "02:30:00";
        duration = "00:30:00";
        weeklySchedule = new WeeklySchedule("05-01-2015","pointyhead");
        producerName = "dilbert";
        presenterName = "wally";
        programSlotService = new ProgramSlotService();        
        annualSchedule = new AnnualSchedule(new Integer(year), "pointyhead");  
        aschList = new ScheduleDelegate().reviewSelectAnnualSchedule();

        programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
        if (programSlot == null) {
            ProgramSlot slot = new ProgramSlot();
            slot.setDateOfProgram(programDate);
            slot.setDuration(duration);
            slot.setPresenter(presenterName);
            slot.setProducer(producerName);
            slot.setProgramName(programName);
            slot.setStartTime(startTime);
            programSlotService.processCreate(slot);
            weeklySchedule.addProgramSlot(slot);
        }
        programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
        Assert.assertNotNull(programSlot);
    }

    @After
    public void tearDown() {
        programSlotService.processDelete(programSlot);
        programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programSlot.getDateOfProgram(), programSlot.getStartTime());
        Assert.assertNull(programSlot);   
    }

    @Test
    public void modify_StartTime_ProgramSlotCmdTest() {    

        when(req.getSession()).thenReturn(session);
        try {      
            
             //Select program date
            when(session.getAttribute("previousProgramDate")).thenReturn(programDate);
             //Select program date
            when(session.getAttribute("previousProgramStratTime")).thenReturn(startTime);
            //Select previousAnnualSchedule Schedule
            when(session.getAttribute("previousAnnualSchedule")).thenReturn(annualSchedule);
            //Select previousWeeklySchedule Schedule
            when(session.getAttribute("previousWeeklySchedule")).thenReturn(weeklySchedule);
            //Select Annual Schedule
            when(session.getAttribute("annualSchedule")).thenReturn(annualSchedule);
            //Select weeklySchedule Schedule
            when(session.getAttribute("weeklySchedule")).thenReturn(weeklySchedule);
           
            String startTime1 = "15:30:00";
            
            //Select program date
            when(req.getParameter("selectPgmDate")).thenReturn(programDate);
            //Select program date
            when(req.getParameter("startTime")).thenReturn(startTime1);
            //Select program date
            when(req.getParameter("duration")).thenReturn(duration);
            //Select program name
            when(req.getParameter("radioPgmName")).thenReturn(programName);              
            //Select program name
            when(req.getParameter("presenterName")).thenReturn(presenterName);
              //Select program date
            when(req.getParameter("producerName")).thenReturn(producerName);

            new ModifyProgramSlotDetailsCmd().perform(null, req, res);
            
            programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime1);
            Assert.assertNotNull(programSlot);
            Assert.assertEquals(programDate, programSlot.getDateOfProgram());
            Assert.assertEquals(startTime1, programSlot.getStartTime());            
            Assert.assertEquals(duration, programSlot.getDuration()); 
            Assert.assertEquals(programName, programSlot.getProgramName());
            Assert.assertEquals(presenterName, programSlot.getPresenter());  
            Assert.assertEquals(producerName, programSlot.getProducer());   
            
            programSlot.setStartTime(startTime1);
            

        } catch (Exception e) {
            assert (false);
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void modify_PgmDate_ProgramSlotCmdTest() {        

        when(req.getSession()).thenReturn(session);
        try {      
            
             //Select program date
            when(session.getAttribute("previousProgramDate")).thenReturn(programDate);
             //Select program date
            when(session.getAttribute("previousProgramStratTime")).thenReturn(startTime);
            //Select previousAnnualSchedule Schedule
            when(session.getAttribute("previousAnnualSchedule")).thenReturn(annualSchedule);
            //Select previousWeeklySchedule Schedule
            when(session.getAttribute("previousWeeklySchedule")).thenReturn(weeklySchedule);
            //Select Annual Schedule
            when(session.getAttribute("annualSchedule")).thenReturn(annualSchedule);
            //Select weeklySchedule Schedule
            when(session.getAttribute("weeklySchedule")).thenReturn(weeklySchedule);
           
            String pgmDate = "07-01-2015";
            
            //Select program date
            when(req.getParameter("selectPgmDate")).thenReturn(pgmDate);
            //Select program date
            when(req.getParameter("startTime")).thenReturn(startTime);
            //Select program date
            when(req.getParameter("duration")).thenReturn(duration);
            //Select program name
            when(req.getParameter("radioPgmName")).thenReturn(programName);              
            //Select program name
            when(req.getParameter("presenterName")).thenReturn(presenterName);
              //Select program date
            when(req.getParameter("producerName")).thenReturn(producerName);

            new ModifyProgramSlotDetailsCmd().perform(null, req, res);
            
            programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(pgmDate, startTime);
            Assert.assertNotNull(programSlot);
            Assert.assertEquals(pgmDate, programSlot.getDateOfProgram());
            Assert.assertEquals(startTime, programSlot.getStartTime());            
            Assert.assertEquals(duration, programSlot.getDuration()); 
            Assert.assertEquals(programName, programSlot.getProgramName());
            Assert.assertEquals(presenterName, programSlot.getPresenter());  
            Assert.assertEquals(producerName, programSlot.getProducer());   
            
            programSlot.setDateOfProgram(pgmDate);            

        } catch (Exception e) {
            assert (false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void modify_Duration_ProgramSlotCmdTest() {        

        when(req.getSession()).thenReturn(session);
        try {      
            
             //Select program date
            when(session.getAttribute("previousProgramDate")).thenReturn(programDate);
             //Select program date
            when(session.getAttribute("previousProgramStratTime")).thenReturn(startTime);
            //Select previousAnnualSchedule Schedule
            when(session.getAttribute("previousAnnualSchedule")).thenReturn(annualSchedule);
            //Select previousWeeklySchedule Schedule
            when(session.getAttribute("previousWeeklySchedule")).thenReturn(weeklySchedule);
            //Select Annual Schedule
            when(session.getAttribute("annualSchedule")).thenReturn(annualSchedule);
            //Select weeklySchedule Schedule
            when(session.getAttribute("weeklySchedule")).thenReturn(weeklySchedule);
           
            String dur = "01:30:00";
            
            //Select program date
            when(req.getParameter("selectPgmDate")).thenReturn(programDate);
            //Select program date
            when(req.getParameter("startTime")).thenReturn(startTime);
            //Select program date
            when(req.getParameter("duration")).thenReturn(dur);
            //Select program name
            when(req.getParameter("radioPgmName")).thenReturn(programName);              
            //Select program name
            when(req.getParameter("presenterName")).thenReturn(presenterName);
              //Select program date
            when(req.getParameter("producerName")).thenReturn(producerName);

            new ModifyProgramSlotDetailsCmd().perform(null, req, res);
            
            programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
            Assert.assertNotNull(programSlot);
            Assert.assertEquals(programDate, programSlot.getDateOfProgram());
            Assert.assertEquals(startTime, programSlot.getStartTime());            
            Assert.assertEquals(dur, programSlot.getDuration()); 
            Assert.assertEquals(programName, programSlot.getProgramName());
            Assert.assertEquals(presenterName, programSlot.getPresenter());  
            Assert.assertEquals(producerName, programSlot.getProducer());                         

        } catch (Exception e) {
            assert (false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void modify_PgmName_ProgramSlotCmdTest() {        

        when(req.getSession()).thenReturn(session);
        try {      
            
             //Select program date
            when(session.getAttribute("previousProgramDate")).thenReturn(programDate);
             //Select program date
            when(session.getAttribute("previousProgramStratTime")).thenReturn(startTime);
            //Select previousAnnualSchedule Schedule
            when(session.getAttribute("previousAnnualSchedule")).thenReturn(annualSchedule);
            //Select previousWeeklySchedule Schedule
            when(session.getAttribute("previousWeeklySchedule")).thenReturn(weeklySchedule);
            //Select Annual Schedule
            when(session.getAttribute("annualSchedule")).thenReturn(annualSchedule);
            //Select weeklySchedule Schedule
            when(session.getAttribute("weeklySchedule")).thenReturn(weeklySchedule);
           
            String pgmName = "ppk";
            
            //Select program date
            when(req.getParameter("selectPgmDate")).thenReturn(programDate);
            //Select program date
            when(req.getParameter("startTime")).thenReturn(startTime);
            //Select program date
            when(req.getParameter("duration")).thenReturn(duration);
            //Select program name
            when(req.getParameter("radioPgmName")).thenReturn(pgmName);              
            //Select program name
            when(req.getParameter("presenterName")).thenReturn(presenterName);
              //Select program date
            when(req.getParameter("producerName")).thenReturn(producerName);

            new ModifyProgramSlotDetailsCmd().perform(null, req, res);
            
            programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
            Assert.assertNotNull(programSlot);
            Assert.assertEquals(programDate, programSlot.getDateOfProgram());
            Assert.assertEquals(startTime, programSlot.getStartTime());            
            Assert.assertEquals(duration, programSlot.getDuration()); 
            Assert.assertEquals(pgmName, programSlot.getProgramName());
            Assert.assertEquals(presenterName, programSlot.getPresenter());  
            Assert.assertEquals(producerName, programSlot.getProducer());                         

        } catch (Exception e) {
            assert (false);
            e.printStackTrace();
        }
    }
    
    @Test
    public void modify_Presenter_ProgramSlotCmdTest() {        

        when(req.getSession()).thenReturn(session);
        try {      
            
             //Select program date
            when(session.getAttribute("previousProgramDate")).thenReturn(programDate);
             //Select program date
            when(session.getAttribute("previousProgramStratTime")).thenReturn(startTime);
            //Select previousAnnualSchedule Schedule
            when(session.getAttribute("previousAnnualSchedule")).thenReturn(annualSchedule);
            //Select previousWeeklySchedule Schedule
            when(session.getAttribute("previousWeeklySchedule")).thenReturn(weeklySchedule);
            //Select Annual Schedule
            when(session.getAttribute("annualSchedule")).thenReturn(annualSchedule);
            //Select weeklySchedule Schedule
            when(session.getAttribute("weeklySchedule")).thenReturn(weeklySchedule);
           
            String present = "dilbert";
            
            //Select program date
            when(req.getParameter("selectPgmDate")).thenReturn(programDate);
            //Select program date
            when(req.getParameter("startTime")).thenReturn(startTime);
            //Select program date
            when(req.getParameter("duration")).thenReturn(duration);
            //Select program name
            when(req.getParameter("radioPgmName")).thenReturn(programName);              
            //Select program name
            when(req.getParameter("presenterName")).thenReturn(present);
              //Select program date
            when(req.getParameter("producerName")).thenReturn(producerName);

            new ModifyProgramSlotDetailsCmd().perform(null, req, res);
            
            programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
            Assert.assertNotNull(programSlot);
            Assert.assertEquals(programDate, programSlot.getDateOfProgram());
            Assert.assertEquals(startTime, programSlot.getStartTime());            
            Assert.assertEquals(duration, programSlot.getDuration()); 
            Assert.assertEquals(programName, programSlot.getProgramName());
            Assert.assertEquals(present, programSlot.getPresenter());  
            Assert.assertEquals(producerName, programSlot.getProducer());                         

        } catch (Exception e) {
            assert (false);
            e.printStackTrace();
        }
    }
    
     @Test
    public void modify_Producer_ProgramSlotCmdTest() {        

        when(req.getSession()).thenReturn(session);
        try {      
            
             //Select program date
            when(session.getAttribute("previousProgramDate")).thenReturn(programDate);
             //Select program date
            when(session.getAttribute("previousProgramStratTime")).thenReturn(startTime);
            //Select previousAnnualSchedule Schedule
            when(session.getAttribute("previousAnnualSchedule")).thenReturn(annualSchedule);
            //Select previousWeeklySchedule Schedule
            when(session.getAttribute("previousWeeklySchedule")).thenReturn(weeklySchedule);
            //Select Annual Schedule
            when(session.getAttribute("annualSchedule")).thenReturn(annualSchedule);
            //Select weeklySchedule Schedule
            when(session.getAttribute("weeklySchedule")).thenReturn(weeklySchedule);
           
            String prod = "dogbert";
            
            //Select program date
            when(req.getParameter("selectPgmDate")).thenReturn(programDate);
            //Select program date
            when(req.getParameter("startTime")).thenReturn(startTime);
            //Select program date
            when(req.getParameter("duration")).thenReturn(duration);
            //Select program name
            when(req.getParameter("radioPgmName")).thenReturn(programName);              
            //Select program name
            when(req.getParameter("presenterName")).thenReturn(presenterName);
              //Select program date
            when(req.getParameter("producerName")).thenReturn(prod);

            new ModifyProgramSlotDetailsCmd().perform(null, req, res);
            
            programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
            Assert.assertNotNull(programSlot);
            Assert.assertEquals(programDate, programSlot.getDateOfProgram());
            Assert.assertEquals(startTime, programSlot.getStartTime());            
            Assert.assertEquals(duration, programSlot.getDuration()); 
            Assert.assertEquals(programName, programSlot.getProgramName());
            Assert.assertEquals(presenterName, programSlot.getPresenter());  
            Assert.assertEquals(prod, programSlot.getProducer());                         

        } catch (Exception e) {
            assert (false);
            e.printStackTrace();
        }
    }
    
    

}
