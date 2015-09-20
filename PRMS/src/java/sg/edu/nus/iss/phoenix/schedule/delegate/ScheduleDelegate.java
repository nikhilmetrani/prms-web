/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualScheduleList;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

/**
 *
 * @author jayavignesh
 */
public class ScheduleDelegate {
    public void processCreate(AnnualSchedule anualSchedule) {
            ScheduleService service = new ScheduleService();
            service.processCreate(anualSchedule);
    }
    
    public void processCreate(List<WeeklySchedule> weeklySchedules) {
            ScheduleService service = new ScheduleService();
            service.processCreate(weeklySchedules);
    }

    public AnnualScheduleList reviewSelectAnnualSchedule() {
        ScheduleService service = new ScheduleService();
        return service.getAnnualScheduleList(); 
    }

    public void copyWeeklySchedule(WeeklySchedule srcWsch, WeeklySchedule tgtWsch){
        ScheduleService service = new ScheduleService();
        service.copyWeeklySchedule(srcWsch, tgtWsch);
    }
}
