/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Niu Yiming
 */
@Action("deletePgmSlot")
public class DeleteProgramSlotCmd implements Perform {
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        
        String name = req.getParameter("radioProgram");
        String programDate = req.getParameter("programDate");
        if (name != null) {
            req.getSession().setAttribute("radioPgmName", name);
        }
        if (programDate != null && !programDate.isEmpty()) {
            req.getSession().setAttribute("selectPgmDate", programDate);
        } else {
            
            String startDate = req.getParameter("weeklySch");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            
            if (startDate != null) {
                try {
                    Date date = sdf.parse(startDate);
                    String strDate  = sdf.format(date);
                    req.getSession().setAttribute("", strDate);
                    //req.getSession().setAttribute("availableDates", availableDates);
                } catch (ParseException ex) {
                    Logger.getLogger(CreateProgramSlotCmd.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
        }
        return "/pages/maintainSchedule/deleteps.jsp";
    } 
}


