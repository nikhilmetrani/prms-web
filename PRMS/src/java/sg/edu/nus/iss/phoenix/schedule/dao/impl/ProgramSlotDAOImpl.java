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
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author jayavignesh
 */
public class ProgramSlotDAOImpl implements ProgramSlotDAO{

    Connection connection;

    /*
     * (non-Javadoc)
     * 
     * @see
     * sg.edu.nus.iss.phoenix.authenticate.dao.impl.ProgramSlotDao#create(java.sql.
     * Connection, sg.edu.nus.iss.phoenix.authenticate.entity.ProgramSlot)
     */
    @Override
    public synchronized void create(ProgramSlot valueObject) throws SQLException {

            String sql = "";
            PreparedStatement stmt = null;
            openConnection();
            try {
                    sql = "INSERT INTO `program-slot` ( duration, dateOfProgram, startTime, `program-name`) " 
                            +   "VALUES (str_to_date(?, '%H:%i:%s'), str_to_date(?, '%d-%m-%Y'), str_to_date(?, '%H:%i:%s'), ?) ";
                    stmt = this.connection.prepareStatement(sql);

                    stmt.setString(1, valueObject.getDuration());
                    stmt.setString(2, valueObject.getDateOfProgram());
                    stmt.setString(3, valueObject.getStartTime());
                    stmt.setString(4, valueObject.getProgramName());

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
        ProgramSlot temp = null;
        openConnection();
        String sql = "SELECT duration, DATE_FORMAT(dateOfProgram, '%d-%m-%Y') AS dtOfProgram, " 
                + "DATE_FORMAT(startTime, '%H:%i:%s') as stTime, `program-name` as programName "
                + "FROM `program-slot` WHERE dateOfProgram >= str_to_date(?, '%d-%m-%Y') AND dateOfProgram < str_to_date(?, '%d-%m-%Y') + 7 " 
                + "AND dateOfProgram <= str_to_date(?, '%d-%m-%Y') " 
                + "ORDER BY dateOfProgram ASC, startTime ASC ";
        List<ProgramSlot> searchResults = new ArrayList<ProgramSlot>();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, startDate);
            stmt.setString(2, startDate);
            stmt.setString(3, "31-12-"+(startDate.substring(6)));
            result = stmt.executeQuery();

            while (result.next()) {
                temp = new ProgramSlot();
                temp.setDuration(result.getString("duration"));
                temp.setDateOfProgram(result.getString("dtOfProgram"));
                temp.setStartTime(result.getString("stTime"));
                temp.setProgramName(result.getString("programName"));
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
    public synchronized void deleteByWeek(String startDateOfWeek) throws SQLException {

            String sql = "";
            PreparedStatement stmt = null;
            openConnection();
            try {
                    sql = "delete from `program-slot` WHERE dateOfProgram >= str_to_date(?, '%d-%m-%Y') AND dateOfProgram < str_to_date(?, '%d-%m-%Y') + 7 " 
                            + "AND dateOfProgram <= str_to_date(?, '%d-%m-%Y') "; 
                    stmt = this.connection.prepareStatement(sql);

                    stmt.setString(1, startDateOfWeek);
                    stmt.setString(2, startDateOfWeek);
                    stmt.setString(3, "31-12-"+(startDateOfWeek.substring(6)));

                    stmt.executeUpdate();
            } finally {
                    if (stmt != null)
                            stmt.close();
                    closeConnection();
            }
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
