/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.radioprogram.service.ReviewSelectProgramService;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualScheduleList;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 * Service class for Maintain Schedule
 * 
 * @author jayavignesh
 */
public class ScheduleService {
    DAOFactoryImpl factory;
    ScheduleDAO scheduleDao;

    /**
     * Construtor
    */
    public ScheduleService() {
            super();
            // TODO Auto-generated constructor stub
            factory = new DAOFactoryImpl();
            scheduleDao = factory.getScheduleDAO();
    }

    /**
     * Returns the AnnualScheduleList
     * @return AnnualScheduleList
     * @see AnnualScheduleList
     */
    public AnnualScheduleList getAnnualScheduleList() {
        AnnualScheduleList data = new AnnualScheduleList();
        List<AnnualSchedule> aschList;
        try {
            aschList = scheduleDao.getAllAnnualSchedules();
            for(AnnualSchedule asch : aschList){
                data.addAnnualSchedule(asch);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectProgramService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data; 
    }
    
    /**
     * Creates an Annual Schedule
     * @param annualSchedule AnnualSchedule object that has to be persisted
     * @see AnnualSchedule
     */
    public void processCreate(AnnualSchedule annualSchedule) {
        try {
            scheduleDao.create(annualSchedule);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * Creates all the Weekly Schedules contained in the List
     * @param weeklySchedules List of WeeklySchedule objects that has to be persisted
     */
    public void processCreate(List<WeeklySchedule> weeklySchedules) {
        try {
            scheduleDao.create(weeklySchedules);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Copies the ProgramSlot from Source WeeklySchedule to Target WeeklySchedule
     * @param srcWsch Source WeeklySchedule
     * @param tgtWsch Target WeeklySchedule
     */
    public void copyWeeklySchedule(WeeklySchedule srcWsch, WeeklySchedule tgtWsch){
        List<ProgramSlot> srcSlots = srcWsch.getProgramSlots();
        List<ProgramSlot> tgtSlots = null;
        ProgramSlotService psservice = new ProgramSlotService();
        ProgramSlot temp = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        Date srcDt = null;
        Date srcPsDt = null;
        Date tgtDt = null;
        long diff = 0l;
        try{
            srcDt = format.parse(srcWsch.getStartDate());
            tgtDt = format.parse(tgtWsch.getStartDate());
        }catch(Exception e){
            e.printStackTrace();
            return;
        }

        psservice.deleteByWeek(tgtWsch.getStartDate());
        tgtWsch.removeAllProgramSlots();

        for(ProgramSlot slot : srcSlots){
            String newDtStr = null;
            temp = slot.copy();
            try{
                srcPsDt = format.parse(slot.getDateOfProgram());
                diff = srcPsDt.getTime() - srcDt.getTime();
                cal.setTime(tgtDt);
                cal.add(Calendar.DATE, (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                newDtStr = format.format(cal.getTime());
                if(!newDtStr.substring(6).equals(tgtWsch.getStartDate().substring(6))) 
                    continue;
            }catch(Exception e){
                e.printStackTrace();
                return;
            }
            temp.setDateOfProgram(newDtStr);
            psservice.create(temp);
        }

        tgtSlots = psservice.getProgramSlotsForWeek(tgtWsch.getStartDate());

        for(ProgramSlot slot : tgtSlots){
            tgtWsch.addProgramSlot(slot);
        }

    }
}
