/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author JuanPablo
 */
@Stateless
public class TimerConsultarRentRecord {

    @EJB
    private RentRecordFacade rentRecordFacade;

    @Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "*/3", persistent = false)
    
    public void myTimer() {
        System.out.println("Timer event: " + new Date());
        this.rentRecordFacade.persistRentRecords();
        
    }
    
    
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
