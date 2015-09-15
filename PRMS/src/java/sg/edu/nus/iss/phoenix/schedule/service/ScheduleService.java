/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
        
        public void copyWeeklySchedule(WeeklySchedule srcWsch, WeeklySchedule tgtWsch){
            List<ProgramSlot> srcSlots = srcWsch.getProgramSlots();
            List<ProgramSlot> tgtSlots = null;
            ProgramSlotService psservice = new ProgramSlotService();
            ProgramSlot temp = null;
            SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
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
                temp = slot.copy();
                try{
                    srcPsDt = format.parse(slot.getDateOfProgram());
                    diff = srcPsDt.getTime() - srcDt.getTime();
                }catch(Exception e){
                    e.printStackTrace();
                    return;
                }
                temp.setDateOfProgram(format.format(new Date(tgtDt.getTime() + diff)));
                psservice.create(temp);
            }
            
            tgtSlots = psservice.getProgramSlotsForWeek(tgtWsch.getStartDate());
            
            for(ProgramSlot slot : tgtSlots){
                tgtWsch.addProgramSlot(slot);
            }

        }
}
