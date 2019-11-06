/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author admin
 */
@Entity
@Table(name = "STORAGES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Storages.findAll", query = "SELECT s FROM Storages s")
    , @NamedQuery(name = "Storages.findById", query = "SELECT s FROM Storages s WHERE s.id = :id")
    , @NamedQuery(name = "Storages.findByCode", query = "SELECT s FROM Storages s WHERE s.code = :code")
    , @NamedQuery(name = "Storages.findByAddress", query = "SELECT s FROM Storages s WHERE s.address = :address")
    , @NamedQuery(name = "Storages.findByName", query = "SELECT s FROM Storages s WHERE s.name = :name")})
public class Storages implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 200)
    @Column(name = "CODE")
    private String code;
    @Size(max = 1999)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 1000)
    @Column(name = "NAME")
    private String name;

    public Storages() {
    }

    public Storages(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Storages)) {
            return false;
        }
        Storages other = (Storages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Storages[ id=" + id + " ]";
    }
    
}
