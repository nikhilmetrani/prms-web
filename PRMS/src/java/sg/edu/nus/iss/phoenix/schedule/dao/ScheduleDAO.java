/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.dao;

import java.sql.SQLException;
import java.util.List;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 *
 * @author jayavignesh
 */
public interface ScheduleDAO {
    public List<AnnualSchedule> getAllAnnualSchedules() throws SQLException;
    public List<WeeklySchedule> getAllWeeklySchedules(int year) throws SQLException;
}
