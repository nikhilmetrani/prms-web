/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Perform;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import at.nocturne.api.Action;


/**
 *
 * @author Rushabh Shah
 */
@Action("createPgmSlot")
public class CreateProgramSlotCmd implements Perform{
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {       

        String name = req.getParameter("radioProgram");
        req.getSession().setAttribute("radioPgmName", name);
        return "/pages/maintainSchedule/setupSchedule.jsp";
    }
    
}
