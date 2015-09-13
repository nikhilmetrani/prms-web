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
 *
 * @author jayavignesh
 */
@Action("copyWeeklySchedule")
public class CopyWeeklyScheduleCmd implements Perform{
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        AnnualSchedule asch = (AnnualSchedule)req.getSession().getAttribute("annualSchedule");
        String startDate = req.getParameter("weeklySch");
        if(startDate!=null && !"".equals(startDate)){
            req.getSession().setAttribute("weeklySchedule", asch.findWeeklySchedule(startDate));
        }
        return "/pages/maintainSchedule/setupSchedule.jsp";
    }
}
