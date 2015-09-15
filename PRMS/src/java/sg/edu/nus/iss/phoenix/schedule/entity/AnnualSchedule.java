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
 * @author jayavignesh
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
    
    public void addWeeklySchedule(WeeklySchedule weeklySchedule){
        weeklySchedules.add(weeklySchedule);
    }

    public void addAllWeeklySchedules(List<WeeklySchedule> weeklySchedules){
        weeklySchedules.addAll(weeklySchedules);
    }
        
    public List<WeeklySchedule> getWeeklySchedules(){
        List<WeeklySchedule> data = new ArrayList<>();
        for(WeeklySchedule weeklySchedule : weeklySchedules){
            data.add(weeklySchedule);
        }
        return data;
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
