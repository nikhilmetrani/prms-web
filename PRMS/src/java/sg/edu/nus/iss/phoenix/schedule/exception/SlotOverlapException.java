/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.exception;

/**
 *
 * @author Rushabh Shah
 */
public class SlotOverlapException extends Exception {
   

    public SlotOverlapException(String program_Slots_are_overlapping) {
        super(program_Slots_are_overlapping);
    }
    
}
