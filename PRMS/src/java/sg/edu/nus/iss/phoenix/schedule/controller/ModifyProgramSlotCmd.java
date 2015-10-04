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
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 *
 * @author Niu Yiming
 */
@Action("modifyps")
public class ModifyProgramSlotCmd implements Perform {
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        
        String programDate = req.getParameter("programDate");
        String startTime = req.getParameter("startTime");
        String actionType = req.getParameter("mpsAction");
        ProgramSlot programSlot = null;
        
        
        if (null != actionType) {
            
            req.setAttribute("radioPgmName", (String)req.getParameter("radioPgmName"));
            req.setAttribute("selectPgmDate", (String)req.getParameter("selectPgmDate"));
            req.setAttribute("startTime", (String)req.getParameter("startTime"));
            req.setAttribute("duration", (String)req.getParameter("duration"));
            req.setAttribute("presenterName", (String)req.getParameter("presenterName"));
            req.setAttribute("producerName", (String)req.getParameter("producerName"));
            
            return "/pages/maintainSchedule/modifyps.jsp";
        } else {
            AnnualSchedule asch = (AnnualSchedule)req.getSession().getAttribute("annualSchedule");
            WeeklySchedule weekly = (WeeklySchedule)req.getSession().getAttribute("weeklySchedule");
            if(weekly!=null ) {
                programSlot = weekly.findProgramSlot(programDate, startTime);

                if (null != programSlot) {
                    String name = programSlot.getProgramName();
                    String duration = programSlot.getDuration();
                    String presenter = programSlot.getPresenter();
                    String producer = programSlot.getProducer();

                    req.setAttribute("radioPgmName", name);
                    req.setAttribute("selectPgmDate", programDate);
                    req.setAttribute("startTime", startTime);
                    req.setAttribute("duration", duration);
                    req.setAttribute("presenterName", presenter);
                    req.setAttribute("producerName", producer);

                    req.setAttribute("actionType", "modifyps");
                    req.getSession().setAttribute("previousProgramDate", programDate);
                    req.getSession().setAttribute("previousProgramStratTime", startTime);

                    req.getSession().setAttribute("previousAnnualSchedule", asch);
                    req.getSession().setAttribute("previousWeeklySchedule", weekly);
                    return "/pages/maintainSchedule/modifyps.jsp";
                }
            }
        }
        return "/pages/maintainSchedule/setupSchedule.jsp";
    }
}
