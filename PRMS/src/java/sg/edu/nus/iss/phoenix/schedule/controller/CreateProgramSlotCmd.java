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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Rushabh Shah
 */
@Action("createPgmSlot")
public class CreateProgramSlotCmd implements Perform{
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {       

        String name = req.getParameter("radioProgram");
        if (name != null) {
            req.getSession().setAttribute("radioPgmName", name);
        }
        List<Date> availableDates;
        String startDate = req.getParameter("weeklySch");
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        if(startDate !=null){
        try {
           
            Date date = sdf.parse(startDate);
            Calendar c = Calendar.getInstance();
            
            availableDates = new ArrayList<>();
            for(int i=0;i<=7;i++ ){
                c.setTime(date);
                c.add(Calendar.DATE, i);  // number of days to add
                date = c.getTime();
                availableDates.add(date);
            }
            req.setAttribute("availableDates", availableDates);
            String programDate = req.getParameter("programDate");
            if(programDate!=null){
                req.getSession().setAttribute("selectDate", programDate);
            }
        } catch (ParseException ex) {
            Logger.getLogger(CreateProgramSlotCmd.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        return "/pages/maintainSchedule/createps.jsp";
    }
    
}
