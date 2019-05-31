/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JuanPablo
 */
@Entity
@Table(name = "PROPERTY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Property.findAll", query = "SELECT p FROM Property p")
    , @NamedQuery(name = "Property.findByIsDelete", query = "SELECT p FROM Property p WHERE p.isDelete = :isDelete")
    , @NamedQuery(name = "Property.findByAdress", query = "SELECT p FROM Property p WHERE p.adress = :adress")
    , @NamedQuery(name = "Property.findByIsAvailable", query = "SELECT p FROM Property p WHERE p.isAvailable = :isAvailable")
    , @NamedQuery(name = "Property.findByNumberRooms", query = "SELECT p FROM Property p WHERE p.numberRooms = :numberRooms")
    , @NamedQuery(name = "Property.findByRent", query = "SELECT p FROM Property p WHERE p.rent = :rent")
    , @NamedQuery(name = "Property.findById", query = "SELECT p FROM Property p WHERE p.id = :id")})
public class Property implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "IS_DELETE")
    private Short isDelete;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ADRESS")
    private String adress;
    @Column(name = "IS_AVAILABLE")
    private Short isAvailable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMBER_ROOMS")
    private BigInteger numberRooms;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "RENT")
    private BigDecimal rent;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propertyId")
    private Collection<RentRecord> rentRecordCollection;
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Client clientId;

    public Property() {
    }

    public Property(BigDecimal id) {
        this.id = id;
    }

    public Property(BigDecimal id, String adress, BigInteger numberRooms, BigDecimal rent) {
        this.id = id;
        this.adress = adress;
        this.numberRooms = numberRooms;
        this.rent = rent;
    }

    public Short getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Short isDelete) {
        this.isDelete = isDelete;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Short getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Short isAvailable) {
        this.isAvailable = isAvailable;
    }

    public BigInteger getNumberRooms() {
        return numberRooms;
    }

    public void setNumberRooms(BigInteger numberRooms) {
        this.numberRooms = numberRooms;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<RentRecord> getRentRecordCollection() {
        return rentRecordCollection;
    }

    public void setRentRecordCollection(Collection<RentRecord> rentRecordCollection) {
        this.rentRecordCollection = rentRecordCollection;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
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
        if (!(object instanceof Property)) {
            return false;
        }
        Property other = (Property) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Property[ id=" + id + " ]";
    }
    
}
