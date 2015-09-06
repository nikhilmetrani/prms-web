/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.radioprogram.service.ReviewSelectProgramService;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

/**
 *
 * @author jayavignesh
 */
public class ScheduleDelegate {
    private ScheduleService service;
    
	public ScheduleDelegate() {
		service = new ScheduleService();
	}
	
	public List<RadioProgram> reviewSelectAnnualSchedule() {
		return service.getAnnualScheduleList(); 
	}
}
