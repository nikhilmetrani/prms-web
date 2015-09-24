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
        scheduleDelegate.processCreate(annualSchedule);
        
        
        List<WeeklySchedule> weeklySchedules = createWeeklySchedulesForYear(year, assignedBy);
        scheduleDelegate.processCreate(weeklySchedules);
        
        return "/nocturne/viewSchedule";
    }
    
    private List<WeeklySchedule> createWeeklySchedulesForYear(int year, String assignedBy) {
        List<WeeklySchedule> weeklySchedules = new ArrayList();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYY-MM-dd");
        for (int month = 0; month <=11; month++) { //Month is zero based - 0 to 11
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, 1);
            int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int day = 1; day <= daysInMonth; day++) {
                calendar.set(year, month, day);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek == Calendar.SUNDAY) {
                    try 
                    {
                        String formatted = dateFormatter.format(calendar.getTime());
                        formatted = formatted + " 00:00:00";
                        WeeklySchedule weeklySchedule = new WeeklySchedule(formatted, assignedBy);
                        weeklySchedules.add(weeklySchedule);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        return weeklySchedules;
    }
}
