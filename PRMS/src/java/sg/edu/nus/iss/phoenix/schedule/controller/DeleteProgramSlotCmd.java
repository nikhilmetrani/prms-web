/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 *
 * @author Niu Yiming
 */
@Action("deletePgmSlot")
public class DeleteProgramSlotCmd implements Perform {
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        
        String programDate = req.getParameter("programDate");
        String startTime = req.getParameter("startTime");
        
        ProgramSlot programSlot = new ProgramSlot();
        programSlot.setDateOfProgram(programDate);
        programSlot.setStartTime(startTime);
        
        ScheduleDelegate scheduleDelegate = new ScheduleDelegate();
        scheduleDelegate.processDelete(programSlot);
        
        AnnualSchedule asch = (AnnualSchedule)req.getSession().getAttribute("annualSchedule");
        String startDate = req.getParameter("weeklySchedule");
        if(startDate!=null && !"".equals(startDate)) {
            WeeklySchedule weekly = asch.findWeeklySchedule(startDate);
            weekly.deleteProgramSlot(programDate, startTime);
            req.getSession().setAttribute("weeklySchedule", asch.findWeeklySchedule(startDate));
        }
            
        return "/pages/maintainSchedule/setupSchedule.jsp";
        
    } 
}