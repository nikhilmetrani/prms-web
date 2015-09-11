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
import sg.edu.nus.iss.phoenix.radioprogram.service.ReviewSelectProgramService;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualScheduleList;

/**
 *
 * @author jayavignesh
 */
public class ScheduleService {
	DAOFactoryImpl factory;
	   ScheduleDAO schdao;

	public ScheduleService() {
		super();
		// TODO Auto-generated constructor stub
		factory = new DAOFactoryImpl();
		schdao = factory.getScheduleDAO();
	}

	public AnnualScheduleList getAnnualScheduleList() {
            AnnualScheduleList data = new AnnualScheduleList();
            List<AnnualSchedule> aschList = null;
            try {
                aschList = schdao.getAllAnnualSchedules();
                for(AnnualSchedule asch : aschList){
                    data.addAnnualSchedule(asch);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReviewSelectProgramService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return data; 
	}
}
