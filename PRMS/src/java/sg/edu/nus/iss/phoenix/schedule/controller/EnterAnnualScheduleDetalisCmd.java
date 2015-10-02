/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 * Command object that creates Annual Schedule for given year if it doesn't exist.
 * @author Nikhil Metrani
 */
@Action("enteras")
public class EnterAnnualScheduleDetalisCmd implements Perform{

    /**
     * Creates Annual Schedule for the given year if it does not exist.
     * The year must not be less than the current year.<br>
     * The request must contain following parameters:<br>
     * 1. year: The year for which the annual schedule is to be created<br>
     * 2. assignedBy: User name of the assignee<br>
     * @param path Path
     * @param req Http Servlet Request
     * @param resp Http Servlet Response
     * @return Returns path of viewSchedule command.<br>
     * If there are any errors, errorMessage parameter will be set and path of createas command is returned.
     */
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp){
        req.removeAttribute("errorMessage");
        ScheduleDelegate scheduleDelegate = new ScheduleDelegate();
        int year = Integer.parseInt(req.getParameter("year"));
        if(year < Calendar.getInstance().get(Calendar.YEAR)) {
            req.setAttribute("errorMessage", "Year cannot be less than current year!");
            return "/nocturne/createas";
        }
        String assignedBy = req.getParameter("assignedBy");
        AnnualSchedule annualSchedule = new AnnualSchedule(year, assignedBy);
        try{
            scheduleDelegate.processCreate(annualSchedule);

            List<WeeklySchedule> weeklySchedules = createWeeklySchedulesForYear(year, assignedBy);
            scheduleDelegate.processCreate(weeklySchedules);
        }
        catch (Exception ex) {
            req.setAttribute("errorMessage", ex.getMessage());
            return "/nocturne/createas";
        }
        
        return "/nocturne/viewSchedule";
    }
    
    private List<WeeklySchedule> createWeeklySchedulesForYear(int year, String assignedBy) throws Exception {
        List<WeeklySchedule> weeklySchedules = new ArrayList();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        for (int month = 0; month <=11; month++) { //Month is zero based - 0 to 11
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, 1);
            int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int day = 1; day <= daysInMonth; day++) {
                calendar.set(year, month, day);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek == Calendar.MONDAY) {
                    try 
                    {
                        String formatted = dateFormatter.format(calendar.getTime());
                        formatted = formatted + " 00:00:00";
                        WeeklySchedule weeklySchedule = new WeeklySchedule(formatted, assignedBy);
                        weeklySchedules.add(weeklySchedule);
                    } catch (Exception e) {
                        throw new Exception("Failed to create weekly schedules");
                    }
                }
            }
        }
        return weeklySchedules;
    }
}
