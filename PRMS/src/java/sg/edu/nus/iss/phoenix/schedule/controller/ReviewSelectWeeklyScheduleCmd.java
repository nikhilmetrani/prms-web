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

/**
 * Command Object that handles the Review/Select Weekly Schedule Command
 * 
 * @author Ganapathy Rajan Jaya Vignesh
 */
@Action("selectWeeklySchedule")
public class ReviewSelectWeeklyScheduleCmd implements Perform{
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("actionType", req.getParameter("actionType"));
        AnnualSchedule asch = (AnnualSchedule)req.getSession().getAttribute("annualSchedule");
        String startDate = req.getParameter("weeklySch");
        if(startDate!=null && !"".equals(startDate)){
            req.getSession().setAttribute("weeklySchedule", asch.findWeeklySchedule(startDate));
        }
        return "/pages/maintainSchedule/setupSchedule.jsp";
    }
}
