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
 * Command object that modify ProgramSlotDetails.
 * @author Rushabh Shah
 */
@Action("finishmodifyps")
public class ModifyProgramSlotDetailsCmd implements Perform{

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        
        //Get current program slot details
        String programDate = (String)req.getSession().getAttribute("previousProgramDate");
        String startTime = (String)req.getSession().getAttribute("previousProgramStratTime");
        
        ProgramSlot currentProgramSlot;
        
        //We need the previous pregram details in case the schedule or start date was changed
        AnnualSchedule previousAnnualSchedule;
        previousAnnualSchedule = (AnnualSchedule)req.getSession().getAttribute("previousAnnualSchedule");
        WeeklySchedule previousWeeklySchedule;
        previousWeeklySchedule = (WeeklySchedule)req.getSession().getAttribute("previousWeeklySchedule");
        
        AnnualSchedule selectedAnnualSchedule;
        selectedAnnualSchedule = (AnnualSchedule)req.getSession().getAttribute("annualSchedule");
        WeeklySchedule selectedWeeklySchedule;
        selectedWeeklySchedule = (WeeklySchedule)req.getSession().getAttribute("weeklySchedule");
        
        if((null != previousAnnualSchedule) &&
                (null != previousWeeklySchedule) &&
                (null != selectedAnnualSchedule) &&
                (null != selectedWeeklySchedule)) {
            
            currentProgramSlot = previousWeeklySchedule.findProgramSlot(programDate, startTime);
            
            if (null != currentProgramSlot) {
                
                ProgramSlot updatedProgramSlot = new ProgramSlot();
                
                //For some reason, only duration is getting passed!
                //Please look into modifyps.jsp to check why the parameters are not passed.
                updatedProgramSlot.setDateOfProgram((String)req.getParameter("selectPgmDate"));
                updatedProgramSlot.setStartTime((String)req.getParameter("startTime"));
                updatedProgramSlot.setDuration((String)req.getParameter("duration"));
                updatedProgramSlot.setProgramName((String)req.getParameter("radioPgmName"));
                updatedProgramSlot.setPresenter((String)req.getParameter("presenter"));
                updatedProgramSlot.setProducer((String)req.getParameter("producer"));
                
                //Delete the current program slot
                ScheduleDelegate scheduleDelegate = new ScheduleDelegate();
                scheduleDelegate.processDelete(currentProgramSlot);
                //Save the program slot with new values
                scheduleDelegate.processCreate(updatedProgramSlot);
                
                if (previousWeeklySchedule.equals(selectedWeeklySchedule))
                    previousWeeklySchedule.updateProgramSlot(programDate, startTime, updatedProgramSlot);
                else {
                    previousWeeklySchedule.deleteProgramSlot(programDate, startTime);
                    selectedWeeklySchedule.addProgramSlot(updatedProgramSlot);
                }
                req.getSession().setAttribute("annualSchedule", previousAnnualSchedule);
                req.getSession().setAttribute("weeklySchedule", previousWeeklySchedule);
            } else {
                return "/pages/maintainSchedule/modifyps.jsp";
            }
        }
        return "/pages/maintainSchedule/setupSchedule.jsp";
        
    }
}
