/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;

/**
 *
 * @author Ganapathy Rajan Jaya Vignesh
 * @author Rushabh Shah
 */
public class ProgramSlot {
    private String duration;
    private String dateOfProgram; 
    private String startTime;
    private String programName;
    private String producer;
    private String presenter;

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * @return the dateOfProgram
     */
    public String getDateOfProgram() {
        return dateOfProgram;
    }

    /**
     * @param dateOfProgram the dateOfProgram to set
     */
    public void setDateOfProgram(String dateOfProgram) {
        this.dateOfProgram = dateOfProgram;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the programName
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * @param programName the programName to set
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

     /**
     * @return the producer user id
     */
    public String getProducer() {
        return producer;
    }
    
     /**
     * @param producer id
     */ 
    public void setProducer(String producer) {
        this.producer = producer;
    }

     /**
     * @return the presenter user id
     */
    public String getPresenter() {
        return presenter;
    }
    
     /**
     * @param presenter user id
     */ 
    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }
    
    
    
    public ProgramSlot copy(){
        ProgramSlot copy = new ProgramSlot();
        copy.setDuration(this.getDuration());
        copy.setDateOfProgram(this.getDateOfProgram());
        copy.setProgramName(this.getProgramName());
        copy.setStartTime(this.getStartTime());
        copy.setProducer(this.getProducer());
        copy.setPresenter(this.getPresenter());
        return copy;
    }
    
    
    
    
}
