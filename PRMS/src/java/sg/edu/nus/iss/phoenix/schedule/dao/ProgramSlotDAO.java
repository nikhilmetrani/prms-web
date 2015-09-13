/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.dao;

import java.sql.SQLException;
import java.util.List;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author jayavignesh
 */
public interface ProgramSlotDAO {
    public List<ProgramSlot> getProgramSlotsForWeek(String startDate) throws SQLException;
    public void create(ProgramSlot valueObject) throws SQLException;
}
