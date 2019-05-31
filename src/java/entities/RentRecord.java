/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JuanPablo
 */
@Entity
@Table(name = "RENT_RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RentRecord.findAll", query = "SELECT r FROM RentRecord r")
    , @NamedQuery(name = "RentRecord.findById", query = "SELECT r FROM RentRecord r WHERE r.id = :id")
    , @NamedQuery(name = "RentRecord.findByLeasePath", query = "SELECT r FROM RentRecord r WHERE r.leasePath = :leasePath")
    , @NamedQuery(name = "RentRecord.findByRentalDate", query = "SELECT r FROM RentRecord r WHERE r.rentalDate = :rentalDate")
    , @NamedQuery(name = "RentRecord.findByConfirmedDate", query = "SELECT r FROM RentRecord r WHERE r.confirmedDate = :confirmedDate")})
public class RentRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 255)
    @Column(name = "LEASE_PATH")
    private String leasePath;
    @Column(name = "RENTAL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rentalDate;
    @Column(name = "CONFIRMED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmedDate;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Client clientId;
    @JoinColumn(name = "PROPERTY_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Property propertyId;

    public RentRecord() {
    }

    public RentRecord(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLeasePath() {
        return leasePath;
    }

    public void setLeasePath(String leasePath) {
        this.leasePath = leasePath;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Property getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Property propertyId) {
        this.propertyId = propertyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RentRecord)) {
            return false;
        }
        RentRecord other = (RentRecord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RentRecord[ id=" + id + " ]";
    }
    
}
