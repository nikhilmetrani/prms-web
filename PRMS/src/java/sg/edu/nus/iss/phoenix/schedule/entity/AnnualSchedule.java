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
    private int year;
    private String assignedBy;
    private List<WeeklySchedule> weeklySchedules;

    public AnnualSchedule(int year, String assignedBy){
        this.year = year;
        this.assignedBy = assignedBy;
        weeklySchedules = new ArrayList<WeeklySchedule>();
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
    
    public void addWeeklySchedule(WeeklySchedule wsch){
        weeklySchedules.add(wsch);
    }

    public void addAllWeeklySchedules(List<WeeklySchedule> wschs){
        weeklySchedules.addAll(wschs);
    }
        
    public List<WeeklySchedule> getWeeklySchedules(){
        List<WeeklySchedule> data = new ArrayList<WeeklySchedule>();
        for(WeeklySchedule wsch : weeklySchedules){
            data.add(wsch);
        }
        return data;
    }

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
