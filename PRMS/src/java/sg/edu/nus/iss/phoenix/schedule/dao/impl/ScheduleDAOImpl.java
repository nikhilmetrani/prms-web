/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 *
 * @author jayavignesh
 */
public class ScheduleDAOImpl implements ScheduleDAO {

    Connection connection;

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

    private void openConnection() {
        try {
            Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            this.connection = DriverManager.getConnection(DBConstants.dbUrl,
                    DBConstants.dbUserName, DBConstants.dbPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

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
                // System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            closeConnection();
        }
    }
    
    @Override
    public void create(WeeklySchedule weeklySchedule) throws SQLException {
        String sql;
        PreparedStatement statement = null;
        openConnection();
        try {
            sql = "INSERT INTO `weekly-schedule` (`startDate`, `assignedBy`) VALUES (?,?); ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, weeklySchedule.getStartDate());
            statement.setString(2, weeklySchedule.getAssignedBy());
            int rowcount = databaseUpdate(statement);
            if (rowcount != 1) {
                // System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }
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
     * @param conn This method requires working database connection.
     * @param statement This parameter contains the SQL statement to be
     * executed.
     * @return
     * @throws java.sql.SQLException
     */
    protected int databaseUpdate(PreparedStatement statement) throws SQLException {
        int result = statement.executeUpdate();
        return result;
    }
}
