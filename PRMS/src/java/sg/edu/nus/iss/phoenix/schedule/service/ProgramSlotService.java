/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import sg.edu.nus.iss.phoenix.schedule.exception.SlotOverlapException;
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
 * @author jayavignesh, Rushabh Shah
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
        
        public void validateProgramSlot(ProgramSlot slot) throws SlotOverlapException{
            checkProgramSlotOverlap(slot);
        }

       private  void checkProgramSlotOverlap(ProgramSlot slot) throws SlotOverlapException {
        Date duration, startTime, endTime, inputStartTime;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");       
        Calendar cEnd = Calendar.getInstance();
        Calendar cDur = Calendar.getInstance();
        try {
            inputStartTime = sdf.parse(slot.getStartTime());

            List<ProgramSlot> programSlots = getProgramSlotsForWeek(slot.getDateOfProgram());
            for (ProgramSlot programSlot : programSlots) {
                startTime = sdf.parse(programSlot.getStartTime());
                duration = sdf.parse(programSlot.getDuration());
                
                cDur.setTime(duration);             
                cEnd.setTime(startTime);
               
                
                cEnd.add(Calendar.HOUR_OF_DAY, cDur.get(Calendar.HOUR_OF_DAY));
                cEnd.add(Calendar.MINUTE, cDur.get(Calendar.MINUTE));
                cEnd.add(Calendar.SECOND, cDur.get(Calendar.SECOND));
                
                endTime = cEnd.getTime();
                
                if (inputStartTime.after(startTime) && inputStartTime.before(endTime)) {
                    throw new SlotOverlapException("Program Slots are overlapping");
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(ProgramSlotService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        public List<ProgramSlot> getProgramSlotsForWeek(String startDate){
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
