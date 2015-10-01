/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ganapathy Rajan Jaya Vignesh
 */
public class AnnualSchedule {
    private final int year;
    private final String assignedBy;
    private List<WeeklySchedule> weeklySchedules;
    
    public AnnualSchedule(int year, String assignedBy){
        this.year = year;
        this.assignedBy = assignedBy;
        weeklySchedules = new ArrayList<>();
    }
    
    public AnnualSchedule(int year, String assignedBy, List<WeeklySchedule> weeklySchedules){
        this.year = year;
        this.assignedBy = assignedBy;
        this.weeklySchedules = weeklySchedules;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the assignedBy
     */
    public String getAssignedBy() {
        return assignedBy;
    }
    
    /**
     * Adds a WeeklySchedule to the AnnualSchedule
     * @param weeklySchedule WeeklySchedule object that has to be added to the AnnualSchedule
     * @see WeeklySchedule
     */
    public void addWeeklySchedule(WeeklySchedule weeklySchedule){
        weeklySchedules.add(weeklySchedule);
    }

    /**
     * Adds all the WeeklySchedule objects from the List to the AnnualSchedule
     * @param weeklySchedules 
     */
    public void addAllWeeklySchedules(List<WeeklySchedule> weeklySchedules){
        this.weeklySchedules.addAll(weeklySchedules);
    }

    /**
     * Returns the list of WeeklySchedule assigned to the AnnualSchedule
     * @return      List of WeeklySchedule
     */
    public List<WeeklySchedule> getWeeklySchedules(){
        List<WeeklySchedule> data = new ArrayList<>();
        for(WeeklySchedule weeklySchedule : weeklySchedules){
            data.add(weeklySchedule);
        }
        return data;
    }

    /**
     * Finds and returns the WeeklySchedule for the matching startDate
     * @param startDate Start date of the week
     * @return WeeklySchedule
     */
    public WeeklySchedule findWeeklySchedule(String startDate){
        int idx = weeklySchedules.indexOf(new WeeklySchedule(startDate, null));
        if(idx>=0)
            return weeklySchedules.get(idx);
        return null;
    }

    @Override 
    public int hashCode(){
        return this.year;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof AnnualSchedule){
            if(((AnnualSchedule)obj).year == this.year)
                return true;
        }
        return false;
    }
}
