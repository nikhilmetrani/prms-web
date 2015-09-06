/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.radioprogram.service.ReviewSelectProgramService;

/**
 *
 * @author jayavignesh
 */
public class ScheduleService {
	DAOFactoryImpl factory;
	ProgramDAO rpdao;

	public ScheduleService() {
		super();
		// TODO Auto-generated constructor stub
		factory = new DAOFactoryImpl();
		rpdao = factory.getProgramDAO();
	}

	public List<RadioProgram> getAnnualScheduleList() {
            List<RadioProgram> data = null;
            try {
                data = rpdao.loadAll();
            } catch (SQLException ex) {
                Logger.getLogger(ReviewSelectProgramService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return data; 
	}
}
