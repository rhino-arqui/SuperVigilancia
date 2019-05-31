/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entities.Client;
import entities.Property;
import entities.RentRecord;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import proxies.RentarProxy;

/**
 *
 * @author JuanPablo
 */
@Stateless
public class RentRecordFacade extends AbstractFacade<RentRecord> {

    @EJB
    private PropertyFacade propertyFacade;

    @EJB
    private ClientFacade clientFacade;

    @PersistenceContext(unitName = "SuperVigilanciaPU")
    private EntityManager em;
    private final RentarProxy rentarProxy;
    private final Gson gson;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RentRecordFacade() {
        super(RentRecord.class);
        this.rentarProxy = new RentarProxy();
        this.gson = new GsonBuilder().create();
    }
    
    private List<RentRecord> fetchRentRecords() {
        List<RentRecord> records = new ArrayList<RentRecord>();
        String response = this.rentarProxy.getJson();
        records = this.gson.fromJson(response, new TypeToken<List<RentRecord>>(){}.getType());
        return records;
    }
    
    public void persistRentRecords() {
        List<RentRecord> records = this.fetchRentRecords();
        for (RentRecord record : records) {
            RentRecord recordFind = this.find(record.getId());
            
            Client client = record.getClientId();
            Client clientFind = this.clientFacade.find(client.getId());
            
            
            Property property = record.getPropertyId();
            Property propertyFind = this.propertyFacade.find(property.getId());
            
            if(clientFind == null) this.clientFacade.create(client);
            if(propertyFind == null) this.propertyFacade.create(property);
            if(recordFind == null) this.create(record);
            
//System.out.println(record.getId());
        }
    }
    
    
    
    
    
}
