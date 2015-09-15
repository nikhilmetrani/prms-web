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
public class WeeklySchedule {
    private String startDate;
    private String assignedBy;
    private List<ProgramSlot> programSlots = null;

    public WeeklySchedule(String startDate, String assignedBy){
        this.startDate = startDate;
        this.assignedBy = assignedBy;
        programSlots = new ArrayList<ProgramSlot>();
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @return the assignedBy
     */
    public String getAssignedBy() {
        return assignedBy;
    }
    
    public void addProgramSlot(ProgramSlot slot){
        programSlots.add(slot);
    }
    
    public List<ProgramSlot> getProgramSlots(){
        List<ProgramSlot> data = new ArrayList<ProgramSlot>();
        for(ProgramSlot ps : programSlots){
            data.add(ps);
        }
        return data;
    }
    
    public void removeAllProgramSlots(){
        programSlots = new ArrayList<ProgramSlot>();
    }
    
    @Override 
    public int hashCode(){
        return this.startDate.hashCode();
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof WeeklySchedule){
            if(((WeeklySchedule)obj).startDate == this.startDate)
                return true;
            else if(((WeeklySchedule)obj).startDate != null && ((WeeklySchedule)obj).startDate.equals(this.startDate)){
                return true;
            }
        }
        return false;
    }
}
