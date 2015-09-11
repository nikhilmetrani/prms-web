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

/**
 *
 * @author jayavignesh
 */
@Action("viewSchedule")
public class ViewScheduleCmd implements Perform{
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getSession().removeAttribute("annualScheduleList");
        req.getSession().removeAttribute("annualSchedule");
        req.getSession().removeAttribute("weeklySchedule");
        ScheduleDelegate delegate = new ScheduleDelegate();
        req.getSession().setAttribute("annualScheduleList", delegate.reviewSelectAnnualSchedule());
        return "/pages/maintainSchedule/setupSchedule.jsp";
    }
}
