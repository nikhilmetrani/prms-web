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
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualScheduleList;

/**
 * Command Object that handles the Review/Select Annual Schedule Command
 * 
 * @author Ganapathy Rajan Jaya Vignesh
 */
@Action("selectAnnualSchedule")
public class ReviewSelectAnnualScheduleCmd implements Perform{
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("actionType", req.getParameter("actionType"));
        req.getSession().removeAttribute("weeklySchedule");
        AnnualScheduleList aschList = (AnnualScheduleList)req.getSession().getAttribute("annualScheduleList");
        String year = req.getParameter("annualSch");
        if(year!=null && !"".equals(year)){
            req.getSession().setAttribute("annualSchedule", aschList.findAnnualSchedule(Integer.parseInt(year)));
        }
        if("createPgmSlot".equals(req.getAttribute("actionType"))){
             return "/nocturne/createPgmSlot";
        }else{
        return "/pages/maintainSchedule/setupSchedule.jsp";
        }
    }
}
