/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.delegate;

import java.sql.SQLException;
import java.util.List;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualScheduleList;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.schedule.service.ProgramSlotService;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;
import sg.edu.nus.iss.phoenix.schedule.exception.ProgramSlotException;

/**
 *
 * @author Ganapathy Rajan Jaya Vignesh 
 * @author Rushabh Shah(validateProgramSlot , getProgramSlotByDateOfProgramAndStartTime method and constructor added)
 */
public class ScheduleDelegate {

    ProgramSlotService programSlotservice;

    public ScheduleDelegate() {
         programSlotservice = new ProgramSlotService();
    }

    /**
     * Delegates the call to ScheduleService
     *
     * @param anualSchedule The annual schedule that is to be saved to the database
     * @throws java.sql.SQLException
     * @see ScheduleService
     */
    public void processCreate(AnnualSchedule anualSchedule) throws SQLException {
            ScheduleService service = new ScheduleService();
            service.processCreate(anualSchedule);
    }

    /**
     * Delegates the call to ScheduleService
     *
     * @param weeklySchedules List of weekly schedules to be saved to the database.
     * @throws java.sql.SQLException
     * @see ScheduleService
     */
    public void processCreate(List<WeeklySchedule> weeklySchedules) throws SQLException {
            ScheduleService service = new ScheduleService();
            service.processCreate(weeklySchedules);
    }

    /**
     * Delegates the call to ScheduleService
     *
     * @see ScheduleService
     */
    public AnnualScheduleList reviewSelectAnnualSchedule() {
        ScheduleService service = new ScheduleService();
        return service.getAnnualScheduleList();
    }

    /**
     * Delegates the call to ScheduleService
     *
     * @see ScheduleService
     */
    public void copyWeeklySchedule(WeeklySchedule srcWsch, WeeklySchedule tgtWsch) {
        ScheduleService service = new ScheduleService();
        service.copyWeeklySchedule(srcWsch, tgtWsch);
    }

    /**
     * Delegates the call to ProgramSlotService
     *
     * @param programSlot
     * @see ProgramSlotService
     */
    public void processCreate(ProgramSlot programSlot) {
        programSlotservice.processCreate(programSlot);
    }
    
    public void processModify(ProgramSlot origPs, ProgramSlot newPs) {
        programSlotservice.processModify(origPs, newPs);
    }
    
    public void processDelete(ProgramSlot programSlot) {
        programSlotservice.processDelete(programSlot);
    }

    /**
     * Delegates the call to ProgramSlotService
     *
     * @param programSlot
     * @throws sg.edu.nus.iss.phoenix.schedule.exception.ProgramSlotException
     * @see ProgramSlotService
     */
    public void validateProgramSlot(ProgramSlot programSlot) throws ProgramSlotException {
        programSlotservice.validateProgramSlot(programSlot);
    }
    
     /**
     * Delegates the call to ProgramSlotService
     * @param programDate
     * @param startTime
     * @return ProgramSlot
     * @see ProgramSlotService
     */
    public ProgramSlot getProgramSlotByDateOfProgramAndStartTime(String programDate,String startTime){
           return programSlotservice.getProgramSlotByDateOfProgramAndStartTime(programDate, startTime);
    }
}
