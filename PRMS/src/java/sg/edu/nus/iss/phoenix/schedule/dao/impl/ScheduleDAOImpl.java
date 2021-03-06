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
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.dao.GeneralDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 *
 * @author Ganapathy Rajan Jaya Vignesh
 * @author Nikhil Metrani
 */
public class ScheduleDAOImpl extends GeneralDAO implements ScheduleDAO {

    /**
     * Retrieves all the Annual Schedules from database and returns as a List
     * @return   List of AnnualSchedule
     * @throws SQLException 
     */
    @Override
    public List<AnnualSchedule> getAllAnnualSchedules() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet result = null;
        AnnualSchedule temp = null;
        openConnection();
        String sql = "SELECT * FROM `annual-schedule` ORDER BY `year` ASC; ";
        List<AnnualSchedule> searchResults = new ArrayList<AnnualSchedule>();
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery();

            while (result.next()) {
                temp = new AnnualSchedule(result.getInt("year"), result.getString("assignedBy"));
                temp.addAllWeeklySchedules(this.getAllWeeklySchedules(temp.getYear()));
                searchResults.add(temp);
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
        System.out.println("record size" + searchResults.size());
        return searchResults;
    }

    /**
     * Retrieves all the Weekly Schedules from database for the provided year and returns as a List
     * @param year  
     * @return   List of WeeklySchedule
     * @throws SQLException 
     */
    @Override
    public List<WeeklySchedule> getAllWeeklySchedules(int year) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet result = null;
        WeeklySchedule temp = null;
        List<ProgramSlot> slots = null;
        ProgramSlotDAO psdao = new DAOFactoryImpl().getProgramSlotDAO();
        openConnection();
        String sql = "SELECT DATE_FORMAT(startDate, '%d-%m-%Y') as startDt, assignedBy FROM `weekly-schedule` WHERE year(startDate) = ? ORDER BY `startDate` ASC; ";
        List<WeeklySchedule> searchResults = new ArrayList<WeeklySchedule>();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, year);
            result = stmt.executeQuery();

            while (result.next()) {
                temp = new WeeklySchedule(result.getString("startDt"), result.getString("assignedBy"));
                slots = psdao.getProgramSlotsForWeek(temp.getStartDate());
                for(ProgramSlot slot:slots){
                    temp.addProgramSlot(slot);
                }
                searchResults.add(temp);
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
        System.out.println("record size" + searchResults.size());
        return searchResults;
    }

    /**
     * Saves the given Annual Schedule object into database.
     * @param annualSchedule This should be the annual schedule object to be saved.
     * @throws SQLException SQLException will be thrown when the method fails to write info into the database.
     * This could happen if the schedule already exists.
     */
    @Override
    public void create(AnnualSchedule annualSchedule) throws SQLException {
        String sql;
        PreparedStatement statement = null;
        openConnection();
        try {
            sql = "INSERT INTO `annual-schedule` (`year`, `assignedBy`) VALUES (?,?); ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, annualSchedule.getYear());
            statement.setString(2, annualSchedule.getAssignedBy());
            int rowcount = databaseUpdate(statement);
            if (rowcount != 1) {
                throw new SQLException("Annual Schedule for the year " + annualSchedule.getYear() + " already exists");
            }
        } catch (SQLException ex) {
            throw new SQLException("Annual Schedule for the year " + annualSchedule.getYear() + " already exists");
        } finally {
            if (statement != null) {
                statement.close();
            }
            closeConnection();
        }
    }
    
    /**
     * Saves the given list of weekly schedules into the database.
     * @param weeklySchedules A list of weekly schedules that are to be saved.
     * @throws SQLException SQLException will be thrown when the method fails to write info into the database.
     * This could happen if any of the schedules already exists.
     */
    @Override
    public void create(List<WeeklySchedule> weeklySchedules) throws SQLException {
        String sql;
        PreparedStatement statement = null;
        openConnection();
        try {
            for (WeeklySchedule weeklySchedule: weeklySchedules) {
                sql = "INSERT INTO `weekly-schedule` (`startDate`, `assignedBy`) VALUES (?,?); ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, weeklySchedule.getStartDate());
                statement.setString(2, weeklySchedule.getAssignedBy());
                int rowcount = databaseUpdate(statement);
                if (rowcount != 1) {
                    throw new SQLException("Weekly Schedule for " + weeklySchedule.getStartDate() + " already exists");
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("Weekly Schedule already exists");
        } finally {
            if (statement != null) {
                statement.close();
            }
            closeConnection();
        }
    }

    /**
     *
     *
     * databaseUpdate-method. This method is a helper method for internal use.
     * It will execute all database handling that will change the information in
     * tables. SELECT queries will not be executed here however. The return
     * value indicates how many rows were affected. This method will also make
     * sure that if cache is used, it will reset when data changes.
     *
     * @param statement This parameter contains the SQL statement to be
     * executed.
     * @return Returns the number of rows that were committed to the database
     * @throws java.sql.SQLException
     */
    protected int databaseUpdate(PreparedStatement statement) throws SQLException {
        int result = statement.executeUpdate();
        return result;
    }
}
