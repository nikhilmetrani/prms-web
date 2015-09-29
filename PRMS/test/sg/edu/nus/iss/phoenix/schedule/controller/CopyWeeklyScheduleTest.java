/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import static org.mockito.Mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualScheduleList;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 * Tests the Copy Weekly Schedule Functionality
 * 
 * @author jayavignesh
 */
public class CopyWeeklyScheduleTest {
    
    @Test
    public void copyWeeklyScheduleTest(){
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        AnnualScheduleList aschList = new ScheduleDelegate().reviewSelectAnnualSchedule();
        String srcYear = "2015";
        String tgtYear = "2015";
        String srcWeekStDate = "04-01-2015";
        String tgtWeekStDate = "11-01-2015";
        
        when(req.getSession()).thenReturn(session);
        try{
            //View Schedule
            when(req.getParameter("actionType")).thenReturn("");
            new ViewScheduleCmd().perform(null, req, res);
            
            //Select Annual Schedule
            when(session.getAttribute("annualScheduleList")).thenReturn(aschList);
            when(req.getParameter("annualSch")).thenReturn(srcYear);
            new ReviewSelectAnnualScheduleCmd().perform(null, req, res);
            
            //Select Weekly Schedule
            when(session.getAttribute("annualSchedule")).thenReturn(aschList.findAnnualSchedule(2015));
            when(req.getParameter("weeklySch")).thenReturn(srcWeekStDate);
            new ReviewSelectWeeklyScheduleCmd().perform(null, req, res);
            
            //Copy Weekly Schedule
            when(req.getParameter("actionType")).thenReturn("copy");
            when(session.getAttribute("weeklySchedule")).thenReturn(aschList.findAnnualSchedule(2015).findWeeklySchedule(srcWeekStDate));
            new CopyWeeklyScheduleCmd().perform(null, req, res);
            
            //Select Target
            new ViewScheduleCmd().perform(null, req, res);
            
            //Select Target Annual Schedule
            when(req.getParameter("annualSch")).thenReturn(srcYear);
            new ReviewSelectAnnualScheduleCmd().perform(null, req, res);
            
            //Select Target Weekly Schedule
            when(req.getParameter("weeklySch")).thenReturn(tgtWeekStDate);
            new ReviewSelectWeeklyScheduleCmd().perform(null, req, res);
            
            //Confirm Copy
            when(session.getAttribute("srcWeeklySchedule")).thenReturn(aschList.findAnnualSchedule(Integer.parseInt(srcYear)).findWeeklySchedule(srcWeekStDate));
            when(session.getAttribute("weeklySchedule")).thenReturn(aschList.findAnnualSchedule(Integer.parseInt(tgtYear)).findWeeklySchedule(tgtWeekStDate));
            new ConfirmCopyWeeklyScheduleCmd().perform(null, req, res);
            
            aschList = new ScheduleDelegate().reviewSelectAnnualSchedule();
            WeeklySchedule srcWsch = aschList.findAnnualSchedule(Integer.parseInt(srcYear)).findWeeklySchedule(srcWeekStDate);
            WeeklySchedule tgtWsch = aschList.findAnnualSchedule(Integer.parseInt(tgtYear)).findWeeklySchedule(tgtWeekStDate);
            if(srcWsch.getProgramSlots().size()!=tgtWsch.getProgramSlots().size()){
                throw new Exception("Weekly Schedule not copied");
            }
            
            assert(true);
            
        }catch(Exception e){
            assert(false);
            e.printStackTrace();
        }
    }

}
