/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import sg.edu.nus.iss.phoenix.schedule.exception.ProgramSlotException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.radioprogram.service.ReviewSelectProgramService;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author jayavignesh, Rushabh Shah(Program-slot validations added)
 */
public class ProgramSlotService {

    DAOFactoryImpl factory;
    ProgramSlotDAO psdao;

    public ProgramSlotService() {
        super();
        // TODO Auto-generated constructor stub
        factory = new DAOFactoryImpl();
        psdao = factory.getProgramSlotDAO();
    }

    public void processCreate(ProgramSlot slot) {
        try {
            psdao.create(slot);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectProgramService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void validateProgramSlot(ProgramSlot slot) throws ProgramSlotException {
        validateDuration(slot);
        validateTimeSpan(slot);
        checkProgramSlotOverlap(slot);

    }

    private void validateDuration(ProgramSlot slot) throws ProgramSlotException {
        try {
            Date duration, minTime, refTime;
            long dur_ms, min_ms, ref_ms;
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            duration = sdf.parse(slot.getDuration());
            Calendar cDur = Calendar.getInstance();
            Calendar cMin = Calendar.getInstance();
            Calendar cRef = Calendar.getInstance();
            cDur.setTime(duration);
            cMin.setTime(duration);
            cRef.setTime(duration);
            cMin.set(Calendar.HOUR_OF_DAY, 0);
            cMin.set(Calendar.MINUTE, 30);
            cMin.set(Calendar.SECOND, 0);

            cRef.set(Calendar.HOUR_OF_DAY, 0);
            cRef.set(Calendar.MINUTE, 0);
            cRef.set(Calendar.SECOND, 0);
            refTime = cRef.getTime();
            duration = cDur.getTime();
            minTime = cMin.getTime();
            if (duration.before(minTime)) {
                throw new ProgramSlotException("Minimum Duration of time should be 30 minutes");
            }
            ref_ms = refTime.getTime();
            dur_ms = duration.getTime() - ref_ms;
            min_ms = minTime.getTime() - ref_ms;
            if (dur_ms % min_ms != 0) {
                throw new ProgramSlotException("Duration must be multiple of 30 minutes");
            }
        } catch (ParseException ex) {
            Logger.getLogger(ProgramSlotService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void validateTimeSpan(ProgramSlot slot) throws ProgramSlotException {
        try {
            Date startDate, duration;
            SimpleDateFormat sdf_time = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat sdf_date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            startDate = sdf_date.parse(getDate(slot));
            Calendar cStart = Calendar.getInstance();
            cStart.setTime(startDate);
            if (cStart.get(Calendar.DAY_OF_WEEK) == 7) {
                duration = sdf_time.parse(slot.getDuration());
                Calendar cEnd = Calendar.getInstance();
                addDuration(duration, cEnd, startDate);
                if (cEnd.get(Calendar.DAY_OF_WEEK) == 1) {
                    throw new ProgramSlotException("Program slot cannot span to next week");
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(ProgramSlotService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Date addDuration(Date duration, Calendar cEnd, Date startDate) {
        Date endDate;
        Calendar cDur = Calendar.getInstance();
        cDur.setTime(duration);
        cEnd.setTime(startDate);
        cEnd.add(Calendar.HOUR_OF_DAY, cDur.get(Calendar.HOUR_OF_DAY));
        cEnd.add(Calendar.MINUTE, cDur.get(Calendar.MINUTE));
        cEnd.add(Calendar.SECOND, cDur.get(Calendar.SECOND));
        endDate = cEnd.getTime();
        return endDate;
    }

    private void checkProgramSlotOverlap(ProgramSlot slot) throws ProgramSlotException {
        Date duration, startTime, endTime, inputStartTime;
        SimpleDateFormat sdf_date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar cEnd = Calendar.getInstance();
        try {
            inputStartTime = sdf_date.parse(getDate(slot));
            List<ProgramSlot> programSlots = getProgramSlotsForWeek(slot.getDateOfProgram());
            for (ProgramSlot programSlot : programSlots) {
                startTime = sdf_date.parse(getDate(programSlot));
                duration = sdf_date.parse(programSlot.getDateOfProgram() + " " + programSlot.getDuration());
                endTime = addDuration(duration, cEnd, startTime);
                if ((inputStartTime.after(startTime) && inputStartTime.before(endTime))
                        || inputStartTime.equals(startTime)) {
                    throw new ProgramSlotException("Program Slots are overlapping, Please change the start time");
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(ProgramSlotService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String getDate(ProgramSlot slot) {
        return slot.getDateOfProgram() + " " + slot.getStartTime();
    }

    public List<ProgramSlot> getProgramSlotsForWeek(String startDate) {
        try {
            return psdao.getProgramSlotsForWeek(startDate);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectProgramService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<ProgramSlot>();
    }

    public void deleteByWeek(String startDateOfWeek) {
        try {
            psdao.deleteByWeek(startDateOfWeek);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectProgramService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
