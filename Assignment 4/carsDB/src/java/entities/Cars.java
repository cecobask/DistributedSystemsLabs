/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BASK
 */
@Entity
@Table(name = "cars")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cars.findAll", query = "SELECT c FROM Cars c")
    , @NamedQuery(name = "Cars.findByCarID", query = "SELECT c FROM Cars c WHERE c.carID = :carID")
    , @NamedQuery(name = "Cars.fetchCarDetails", query = "SELECT c FROM Cars c WHERE c.cModel = :cModel AND c.cYear = :cYear")
    , @NamedQuery(name = "Cars.findByCMake", query = "SELECT c FROM Cars c WHERE c.cMake = :cMake")
    , @NamedQuery(name = "Cars.findByCModel", query = "SELECT c FROM Cars c WHERE c.cModel = :cModel")
    , @NamedQuery(name = "Cars.findByCYear", query = "SELECT c FROM Cars c WHERE c.cYear = :cYear")})
public class Cars implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "carID")
    private Integer carID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cMake")
    private String cMake;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cModel")
    private String cModel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cYear")
    private int cYear;

    public Cars() {
    }

    public Cars(Integer carID) {
        this.carID = carID;
    }

    public Cars(Integer carID, String cMake, String cModel, int cYear) {
        this.carID = carID;
        this.cMake = cMake;
        this.cModel = cModel;
        this.cYear = cYear;
    }

    public Integer getCarID() {
        return carID;
    }

    public void setCarID(Integer carID) {
        this.carID = carID;
    }

    public String getCMake() {
        return cMake;
    }

    public void setCMake(String cMake) {
        this.cMake = cMake;
    }

    public String getCModel() {
        return cModel;
    }

    public void setCModel(String cModel) {
        this.cModel = cModel;
    }

    public int getCYear() {
        return cYear;
    }

    public void setCYear(int cYear) {
        this.cYear = cYear;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carID != null ? carID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cars)) {
            return false;
        }
        Cars other = (Cars) object;
        if ((this.carID == null && other.carID != null) || (this.carID != null && !this.carID.equals(other.carID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cars[ carID=" + carID + " ]";
    }
    
}
