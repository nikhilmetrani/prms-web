/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.exception.ProgramSlotException;

/**
 * Command object that enter ProgramSlotDetails.
 * @author Rushabh Shah
 */
@Action("enterps")
public class EnterProgramSlotDetailsCmd implements Perform{

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
       String name = req.getParameter("radioProgram");
       String programDate = req.getParameter("programDate"); 
       String startTime = req.getParameter("startTime");
       String duration = req.getParameter("pgmSlotDuration");
       ProgramSlot programSlot = new ProgramSlot();
       programSlot.setProgramName(name);
       programSlot.setStartTime(startTime);
       programSlot.setDateOfProgram(programDate);
       programSlot.setDuration(duration);  
       
        ScheduleDelegate scheduleDelegate = new ScheduleDelegate();        
        
        try {
            scheduleDelegate.validateProgramSlot(programSlot);
            scheduleDelegate.processCreate(programSlot);
        } catch (ProgramSlotException ex) {
            Logger.getLogger(EnterProgramSlotDetailsCmd.class.getName()).log(Level.SEVERE, null, ex);
            req.setAttribute("errPgmSlot", ex.getMessage());
            return "/pages/maintainSchedule/createps.jsp";
        }
        final HttpSession session = req.getSession();
        
        session.removeAttribute("annualScheduleList");
        session.removeAttribute("weeklySch");
        session.removeAttribute("availableDates");
        session.removeAttribute("selectPgmDate");
        session.removeAttribute("radioPgms");
        session.removeAttribute("radioPgmName");   
        session.removeAttribute("annualSchedule");
        session.removeAttribute("weeklySchedule");
        
        req.removeAttribute("startTime");
        req.removeAttribute("pgmSlotDuration");
        
        req.setAttribute("successMsg", "Program Slot has been created Successfully !"); 
        
//        return "/pages/home.jsp";
//        return "/nocturne/createPgmSlot";
        return "/pages/maintainSchedule/createps.jsp";
    }
    
   
    
}
