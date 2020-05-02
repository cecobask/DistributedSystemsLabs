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
 * @author      Tsvetoslav Dimov
 * @date        02/05/2020
 * @studentID   20077038
 * @description Model class for object type Car. It is used by the RESTful
 *              services to retrieve data from the MySQL database.
 */
@Entity
@Table(name = "cars")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cars.findAll", query = "SELECT c FROM Cars c")
    , @NamedQuery(name = "Cars.findByCarID", query = "SELECT c FROM Cars c WHERE c.carID = :carID")
    , @NamedQuery(name = "Cars.fetchCarDetailsByYear", query = "SELECT c FROM Cars c WHERE c.cModel = :cModel AND c.cYear = :cYear")
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

    // Empty constructor used for JSON responses.
    public Cars() {
    }

    /**
     * @description Car object constructor with ID parameter.
     * @param carID
     */
    public Cars(Integer carID) {
        this.carID = carID;
    }

    /**
     * @description Car object constructor with all required parameters.
     * @param carID
     * @param cMake
     * @param cModel
     * @param cYear 
     */
    public Cars(Integer carID, String cMake, String cModel, int cYear) {
        this.carID = carID;
        this.cMake = cMake;
        this.cModel = cModel;
        this.cYear = cYear;
    }

    // Getters and setters.
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

    /**
     * Integer hash code representation of the Car object.
     * @return integer hash code
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carID != null ? carID.hashCode() : 0);
        return hash;
    }

    /**
     * @description Compare Car objects.
     * @param object
     * @return true - objects match, false - objects do not match.
     */
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

    /**
     * @description Converts a Car object into its String representation.
     * @return String representation of a Car object.
     */
    @Override
    public String toString() {
        return "entities.Cars[ carID=" + carID + " ]";
    }
    
}
