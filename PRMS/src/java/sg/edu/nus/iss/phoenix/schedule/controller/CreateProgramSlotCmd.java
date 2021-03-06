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
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;

/**
 *
 * Command object that initiates creation of new Program Slot. 
 * @author Rushabh Shah
 */
@Action("createPgmSlot")
public class CreateProgramSlotCmd implements Perform {

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        
        ScheduleDelegate delegate = new ScheduleDelegate();
        req.getSession().setAttribute("annualScheduleList", delegate.reviewSelectAnnualSchedule());
        ReviewSelectProgramDelegate del = new ReviewSelectProgramDelegate();
        List<RadioProgram> radioPrograms = del.reviewSelectRadioProgram();     
        req.getSession().setAttribute("radioPgms", radioPrograms);
        req.setAttribute("actionType", "createPgmSlot");

        String name = req.getParameter("radioProgram");
        String programDate = req.getParameter("programDate");
        if (name != null) {
            req.getSession().setAttribute("radioPgmName", name);
        }
         String startDate = req.getParameter("weeklySch");
        if (programDate != null  && !programDate.isEmpty() 
                && req.getSession().getAttribute("availableDates" )!=null) {
            req.getSession().setAttribute("selectPgmDate", programDate);
        } else {

            List<String> availableDates;

           
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            if (startDate != null) {
                try {

                    Date date = sdf.parse(startDate);
                    Calendar c = Calendar.getInstance();
                    String strDate;
                    availableDates = new ArrayList<>();
                    for (int i = 0; i < 7; i++) {
                        strDate = sdf.format(date);
                        availableDates.add(strDate);
                        c.setTime(date);
                        if (strDate.startsWith("31-12")) {
                            c.roll(Calendar.YEAR, 1);
                        }
                        c.roll(Calendar.DAY_OF_YEAR, 1);
                        date = c.getTime();
                    }

                    req.getSession().setAttribute("availableDates", availableDates);

                } catch (ParseException ex) {
                    Logger.getLogger(CreateProgramSlotCmd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return "/pages/maintainSchedule/createps.jsp";
    }

}
