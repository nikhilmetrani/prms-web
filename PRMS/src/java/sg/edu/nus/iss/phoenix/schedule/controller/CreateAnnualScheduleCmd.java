/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command object that initiates creation of new Annual Schedule.
 * @author Nikhil Metrani
 */
@Action("createas")
public class CreateAnnualScheduleCmd implements Perform {
    /**
     * This action will take users to the Annual Schedule creation page where they can enter the year.
     * @param path Path
     * @param req Http Servlet Request
     * @param resp Http Servlet Response
     * @return Returns the path of page where the user can enter details
     */
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp){
        req.getSession().setAttribute("errorMessage", "");
        return "/pages/maintainSchedule/createas.jsp";
    }
}
