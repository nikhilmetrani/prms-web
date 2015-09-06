/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author jayavignesh
 */
public class AnnualScheduleList {
    private List<AnnualSchedule> scheduleList = new ArrayList<AnnualSchedule>();
    
    public AnnualScheduleList(){
        
    }
    
    public void addAnnualSchedule(AnnualSchedule asch){
        if(!this.scheduleList.contains(asch)){
            this.scheduleList.add(asch);
        }
    }

    public AnnualSchedule findAnnualSchedule(int year){
        int idx = scheduleList.indexOf(new AnnualSchedule(year, null);
        if(idx>=0)
            return scheduleList.get(idx));
        return null;
    }
    
}
