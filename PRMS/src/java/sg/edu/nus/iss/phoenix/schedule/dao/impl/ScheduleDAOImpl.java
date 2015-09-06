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
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualScheduleList;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 *
 * @author jayavignesh
 */
public class ScheduleDAOImpl implements ScheduleDAO{

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
    public List<WeeklySchedule> getAllWeeklySchedules(int year) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet result = null;
        openConnection();
        String sql = "SELECT DATE_FORMAT(startDate, '%d-%m-%Y') as startDate, assignedBy FROM `weekly-schedule` WHERE year(startDate) = ? ORDER BY `startDate` ASC; ";
        List<WeeklySchedule> searchResults = new ArrayList<WeeklySchedule>();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, year);
            result = stmt.executeQuery();

            while (result.next()) {
                searchResults.add(new WeeklySchedule(result.getString("startDate"), result.getString("assignedBy")));
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
}
