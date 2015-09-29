/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.dao.GeneralDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author jayavignesh, Rushabh Shah(Added producer/presenter fields)
 */
public class ProgramSlotDAOImpl extends GeneralDAO implements ProgramSlotDAO{

    /*
     * (non-Javadoc)
     * 
     * @see
     * sg.edu.nus.iss.phoenix.authenticate.dao.impl.ProgramSlotDao#create(java.sql.
     * Connection, sg.edu.nus.iss.phoenix.authenticate.entity.ProgramSlot)
     */
    @Override
    public synchronized void create(ProgramSlot programSlot) throws SQLException {

            String sql = "";
            PreparedStatement stmt = null;
            openConnection();
            try {
                    sql = "INSERT INTO `program-slot` ( duration, dateOfProgram, startTime, `program-name`, producer, presenter) " 
                            +   "VALUES (str_to_date(?, '%H:%i:%s'), str_to_date(?, '%d-%m-%Y'), str_to_date(?, '%H:%i:%s'), ?, ?, ? ) ";
                    stmt = this.connection.prepareStatement(sql);

                    stmt.setString(1, programSlot.getDuration());
                    stmt.setString(2, programSlot.getDateOfProgram());
                    stmt.setString(3, programSlot.getStartTime());
                    stmt.setString(4, programSlot.getProgramName());
                    stmt.setString(5, programSlot.getProducer());
                    stmt.setString(6, programSlot.getPresenter());

                    stmt.executeUpdate();
            } finally {
                    if (stmt != null)
                            stmt.close();
                    closeConnection();
            }
    }
    
    @Override
    public synchronized void delete(ProgramSlot programSlot) throws SQLException {

            String sql = "";
            PreparedStatement stmt = null;
            openConnection();
            try {
                    sql = "DELETE FROM `program-slot`" 
                            +   " WHERE date = str_to_date(?, '%d-%m-%Y') and startTime = str_to_date(?, '%H:%i:%s')";
                    stmt = this.connection.prepareStatement(sql);
                    stmt.setString(1, programSlot.getDateOfProgram());
                    stmt.setString(2, programSlot.getStartTime());
                    
                    stmt.executeUpdate();
            } finally {
                    if (stmt != null)
                            stmt.close();
                    closeConnection();
            }
    }
    
    @Override
    public List<ProgramSlot> getProgramSlotsForWeek(String startDate) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet result = null;
        ProgramSlot programSlot = null;
        openConnection();
        String sql = "SELECT duration, DATE_FORMAT(dateOfProgram, '%d-%m-%Y') AS dtOfProgram, " 
                + "DATE_FORMAT(startTime, '%H:%i:%s') as stTime, "
                + "`program-name` as programName, `producer` as producer,`presenter` as presenter "
                + "FROM `program-slot` WHERE dateOfProgram >= str_to_date(?, '%d-%m-%Y') AND dateOfProgram < DATE_ADD(str_to_date(?, '%d-%m-%Y'), INTERVAL 7 DAY) " 
                + "ORDER BY dateOfProgram ASC, startTime ASC ";
        List<ProgramSlot> searchResults = new ArrayList<ProgramSlot>();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, startDate);
            stmt.setString(2, startDate);
            result = stmt.executeQuery();

            while (result.next()) {
                programSlot = new ProgramSlot();
                programSlot.setDuration(result.getString("duration"));
                programSlot.setDateOfProgram(result.getString("dtOfProgram"));
                programSlot.setStartTime(result.getString("stTime"));
                programSlot.setProgramName(result.getString("programName"));
                programSlot.setProducer(result.getString("producer"));
                programSlot.setPresenter(result.getString("presenter"));
                searchResults.add(programSlot);
            }
        } finally {
                if (result != null)
                        result.close();
                if (stmt != null)
                        stmt.close();
                closeConnection();
        }
        System.out.println("record size"+searchResults.size());
        return searchResults;
    }

    @Override
    public synchronized void deleteByWeek(String startDateOfWeek) throws SQLException {

            String sql = "";
            PreparedStatement stmt = null;
            openConnection();
            try {
                    sql = "delete from `program-slot` WHERE dateOfProgram >= str_to_date(?, '%d-%m-%Y') AND dateOfProgram < DATE_ADD(str_to_date(?, '%d-%m-%Y'), INTERVAL 7 DAY) "; 
                    stmt = this.connection.prepareStatement(sql);

                    stmt.setString(1, startDateOfWeek);
                    stmt.setString(2, startDateOfWeek);

                    stmt.executeUpdate();
            } finally {
                    if (stmt != null)
                            stmt.close();
                    closeConnection();
            }
    }

}
