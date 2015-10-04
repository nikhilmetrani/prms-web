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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 *
 * @author Niu Yiming
 */
@Action("modifyPgmSlot")
public class ModifyProgramSlotCmd implements Perform {
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        
        String programDate = req.getParameter("programDate");
        String startTime = req.getParameter("startTime");
        
        ProgramSlot programSlot = null;
        
        AnnualSchedule asch = (AnnualSchedule)req.getSession().getAttribute("annualSchedule");
        String startDate = req.getParameter("weeklySchedule");
        if(startDate!=null && !"".equals(startDate)) {
            WeeklySchedule weekly = asch.findWeeklySchedule(startDate);
            programSlot = weekly.findProgramSlot(programDate, startTime);
            
            if (null != programSlot) {
                String name = programSlot.getProgramName();
                String duration = programSlot.getDuration();
                String presenter = programSlot.getPresenter();
                String producer = programSlot.getProducer();
                
                List<String> availableDates = getAvaliableDates(startDate);
                
                req.getSession().setAttribute("radioPgmName", name);
                req.getSession().setAttribute("selectPgmDate", programDate);
                req.getSession().setAttribute("availableDates", availableDates);
                req.getSession().setAttribute("startTime", startTime);
                req.getSession().setAttribute("duration", duration);
                req.getSession().setAttribute("presenter", presenter);
                req.getSession().setAttribute("producer", producer);
            }
            req.getSession().setAttribute("weeklySchedule", asch.findWeeklySchedule(startDate));
        }
        return "/pages/maintainSchedule/modifyps.jsp";
    }
    
    private List<String> getAvaliableDates(String weekStartDate) {
        List<String> availableDates = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if (weekStartDate != null) {
            try {

                Date date = sdf.parse(weekStartDate);
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
            }catch (ParseException ex) {
                Logger.getLogger(ModifyProgramSlotCmd.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }
        return availableDates;
    }
}
