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
    
    /**
     * Adds a ProgramSlot to the WeeklySchedule
     * @param slot ProgramSlot object that has to be added to the WeeklySchedule
     */
    public void addProgramSlot(ProgramSlot slot){
        programSlots.add(slot);
    }
    
    /**
     * Returns the list of ProgramSlots
     * @return      List of ProgramSlot
     */
    public List<ProgramSlot> getProgramSlots(){
        List<ProgramSlot> data = new ArrayList<>();
        for(ProgramSlot ps : programSlots){
            data.add(ps);
        }
        return data;
    }
    
    /* Nikhil Metrani
    *  Added methods deleteProgramSlot, updateProgramSlot, and findProgramSlot to enable easy
    *  modification of the entity.
    */
    
    /**
     * Deletes a single program slot from the list.
     * @param startDate Start date of the program slot to be deleted.<br>
     * @param startTime Start time of the program slot to be deleted.<br>
     * @return Returns the deleted program slot object on successful delete.<br>
     * On failure returns null.
     */
    public ProgramSlot deleteProgramSlot(String startDate, String startTime) {
        ProgramSlot programSlot = findProgramSlot(startDate, startTime);
        if (null != programSlot) {
            if (!programSlots.remove(programSlot))
                programSlot = null;
        }
        return programSlot;
    }
    
    /**
     * Updates a single program slot from the list if it exists.
     * @param startDate Start date of the program slot to be updated.<br>
     * @param startTime Start time of the program slot to be updated.<br>
     * @param updated Updated program slot object.<br>
     * @return Returns the updated program slot when successful.<br>
     * On failure returns null.
     */
    public ProgramSlot updateProgramSlot(String startDate, String startTime, ProgramSlot updated) {
        ProgramSlot programSlot = findProgramSlot(startDate, startTime);
        if (null != programSlot) {
            int index = programSlots.indexOf(programSlot);
            if (0 <= index)
                programSlot = programSlots.set(index, updated);
        }
        return programSlot;
    }
    
    /**
     * Finds a single program slot from the list.
     * @param startDate Start date of the program slot to be deleted.<br>
     * @param startTime Start time of the program slot to be deleted.<br>
     * @return Returns the first program slot object matching the given startDate and startTime.<br>
     * On failure returns null.
     */
    public ProgramSlot findProgramSlot(String startDate, String startTime) {
        if ((null == startDate) || (null == startTime))
            return null;
        ProgramSlot programSlot = null;
        for(ProgramSlot ps : programSlots){
            if (ps.getDateOfProgram().equals(startDate)){
                if (ps.getStartTime().equals(startTime)) {
                    programSlot = ps;
                    break;
                }
            }
        }
        return programSlot;
    }
    
    /**
     * Removes all the ProgramSlot contained by the WeeklySchedule
     */
    public void removeAllProgramSlots(){
        programSlots.clear();
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
