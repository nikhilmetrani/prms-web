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

/**
 * Command Object that handles the Copy Weekly Schedule Command
 * 
 * @author Ganapathy Rajan Jaya Vignesh
 */
@Action("copyWeeklySchedule")
public class CopyWeeklyScheduleCmd implements Perform{
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("actionType", req.getParameter("actionType"));
        req.getSession().setAttribute("srcAnnualSchedule", req.getSession().getAttribute("annualSchedule"));
        req.getSession().setAttribute("srcWeeklySchedule", req.getSession().getAttribute("weeklySchedule"));
        req.getSession().removeAttribute("annualSchedule");
        req.getSession().removeAttribute("weeklySchedule");
        return "/nocturne/viewSchedule";
    }
}
