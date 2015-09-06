/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;

/**
 *
 * @author jayavignesh
 */
public class AnnualSchedule {
    private int year;
    private String assignedBy;

    public AnnualSchedule(int year, String assignedBy){
        this.year = year;
        this.assignedBy = assignedBy;
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
