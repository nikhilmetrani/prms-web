/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 *
 * @author Nikhil Metrani
 */
@Action("enteras")
public class EnterAnnualScheduleDetalisCmd implements Perform{

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ScheduleDelegate scheduleDelegate = new ScheduleDelegate();
        int year = Integer.parseInt(req.getParameter("year"));
        String assignedBy = req.getParameter("assignedBy");
        AnnualSchedule annualSchedule = new AnnualSchedule(year, assignedBy);
        //System.out.println(annualSchedule.toString());
        scheduleDelegate.processCreate(annualSchedule);
        
        List<WeeklySchedule> weeklySchedules = new ArrayList();
        
        SimpleDateFormat format1 = new SimpleDateFormat("YYYY-MM-DD");
        Calendar cal = Calendar.getInstance();
        cal.set(year, 0, 1, 0, 0);
        for (int i = 0; i <= 366; i++) 
        {
            try 
            {
                cal.add(Calendar.WEEK_OF_YEAR, +1);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                String formatted = format1.format(cal.getTime());
                formatted = formatted + " 00:00:00";
                WeeklySchedule weeklySchedule = new WeeklySchedule(formatted, assignedBy);
                weeklySchedules.add(weeklySchedule);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }        
        scheduleDelegate.processCreate(weeklySchedules);
        return "/nocturne/viewSchedule";
    }
    
}
