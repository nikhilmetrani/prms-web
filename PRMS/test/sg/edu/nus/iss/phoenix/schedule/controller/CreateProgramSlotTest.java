/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import static org.mockito.Mockito.*;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectProgramDelegate;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualScheduleList;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createProgramSlotCmdTest() {

        when(req.getSession()).thenReturn(session);
        try {
            //View Schedule
            when(req.getParameter("actionType")).thenReturn("");
            new ViewScheduleCmd().perform(null, req, res);
            assert (true);

            //Select Annual Schedule
            when(session.getAttribute("annualScheduleList")).thenReturn(aschList);
            when(req.getParameter("annualSch")).thenReturn(year);
            new ReviewSelectAnnualScheduleCmd().perform(null, req, res);

            //Select Weekly Schedule
            when(session.getAttribute("annualSchedule")).thenReturn(aschList.findAnnualSchedule(2015));
            when(req.getParameter("weeklySch")).thenReturn(weekStDate);
            new ReviewSelectWeeklyScheduleCmd().perform(null, req, res);

            //Select program name
            when(session.getAttribute("radioPgms")).thenReturn(new ReviewSelectProgramDelegate().reviewSelectRadioProgram());
            //Select radioProgram
            when(req.getAttribute("radioProgram")).thenReturn(name);
            //Select program name
            when(session.getAttribute("radioPgmName")).thenReturn(name);
            //Select program date
            when(session.getAttribute("programDate")).thenReturn(programDate);
            //Select program date
            when(session.getAttribute("selectPgmDate")).thenReturn(programDate);

            //Select program date
            when(session.getAttribute("availableDates")).thenReturn(availableDates);
            new CreateProgramSlotCmd().perform(null, req, res);

        } catch (Exception e) {
            assert (false);
            e.printStackTrace();
        }
    }

    @Test
    public void enterProgramSlotDetailsCmdTest() {

        programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
        if (programSlot != null) {
            programSlotService.processDelete(programSlot);
        }

        programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
        Assert.assertNull(programSlot);

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
            new EnterProgramSlotDetailsCmd().perform(null, req, res);

            programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);

            Assert.assertNotNull(programSlot);
            Assert.assertEquals(name, programSlot.getProgramName());
            Assert.assertEquals(duration, programSlot.getDuration());

            programSlotService.processDelete(programSlot);
            programSlot = programSlotService.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
            Assert.assertNull(programSlot);

        } catch (Exception e) {
            assert (false);

        }
    }

}
