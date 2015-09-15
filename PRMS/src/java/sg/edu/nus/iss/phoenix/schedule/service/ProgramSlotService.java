/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.radioprogram.service.ReviewSelectProgramService;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualScheduleList;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author jayavignesh
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

	public void create(ProgramSlot slot) {
            try {
                psdao.create(slot);
            } catch (SQLException ex) {
                Logger.getLogger(ReviewSelectProgramService.class.getName()).log(Level.SEVERE, null, ex);
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
