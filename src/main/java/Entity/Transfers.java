/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author admin
 */
@Entity
@Table(name = "TRANSFERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transfers.findAll", query = "SELECT t FROM Transfers t")
    , @NamedQuery(name = "Transfers.findById", query = "SELECT t FROM Transfers t WHERE t.id = :id")
    , @NamedQuery(name = "Transfers.findByStFrom", query = "SELECT t FROM Transfers t WHERE t.st_from = :st_from")
    , @NamedQuery(name = "Transfers.findByStTo", query = "SELECT t FROM Transfers t WHERE t.st_to = :st_to")
    , @NamedQuery(name = "Transfers.findByProductid", query = "SELECT t FROM Transfers t WHERE t.product_id = :product_id")
    , @NamedQuery(name = "Transfers.findByCount", query = "SELECT t FROM Transfers t WHERE t.count = :count")
    , @NamedQuery(name = "Transfers.findByTrUser", query = "SELECT t FROM Transfers t WHERE t.tr_user = :tr_user")
    , @NamedQuery(name = "Transfers.findByTrDate", query = "SELECT t FROM Transfers t WHERE t.tr_date = :tr_date")
    , @NamedQuery(name = "Transfers.getByStorageProduct", query = "select sum(case when trn.st_to = 1 then trn.count else 0 end) - sum(case when trn.st_from = 1 then trn.count else 0 end) from Transfers trn where trn.product_id = :product_id")
})
public class Transfers implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ST_FROM")
    private Long st_from;
    @Column(name = "ST_TO")
    private Long st_to;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "PRODUCT_ID")
    private Long product_id;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "COUNT")
    private Long count;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TR_USER")
    private String tr_user;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "TR_DATE")   
    private Date tr_date;

    public Transfers() {
    }

    public Transfers(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSt_from() {
        return st_from;
    }

    public void setSt_from(Long st_from) {
        this.st_from = st_from;
    }

    public Long getSt_to() {
        return st_to;
    }

    public void setSt_to(Long st_to) {
        this.st_to = st_to;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getTr_user() {
        return tr_user;
    }

    public void setTr_user(String tr_user) {
        this.tr_user = tr_user;
    }

    public Date getTr_date() {
        return tr_date;
    }

    public void setTr_date(Date tr_date) {
        this.tr_date = tr_date;
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
        if (!(object instanceof Transfers)) {
            return false;
        }
        Transfers other = (Transfers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Transfers[ id=" + id + " ]";
    }
    
}
