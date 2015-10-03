/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.exception;

/**
 * Exception object for ProgramSlotException.
 * 
 * @author Rushabh Shah
 */
public class ProgramSlotException extends Exception {
   
    /**
    * Exception object for ProgramSlotException.
    * 
    * @param program_Slots_are_overlapping program slot overlapping exception
    */
    public ProgramSlotException(String program_Slots_are_overlapping) {
        super(program_Slots_are_overlapping);
    }
    
}
