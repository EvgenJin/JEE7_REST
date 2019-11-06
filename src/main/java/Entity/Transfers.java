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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TRANSFERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transfers.findAll", query = "SELECT s FROM Transfers s")
   ,@NamedQuery(name = "Transfers.findById", query = "SELECT s FROM Transfers s WHERE s.id = :id")
}) 
public class Transfers implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 100)
    @Column(name = "DATE_TRN")
    private Date date_trn;
    @Column(name = "PRODUCT_ID")
    private Long product_id;
    @Column(name = "STORAGE_IN")
    private Long storage_in;
    @Column(name = "STORAGE_OUT")
    private Long storage_out;
    @Column(name = "COUN")
    private Long coun;
    @Size(max = 100)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 600)
    @Column(name = "COMMENT")
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date_trn;
    }

    public void setDate(Date date_trn) {
        this.date_trn = date_trn;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getStorage_in() {
        return storage_in;
    }

    public void setStorage_in(Long storage_in) {
        this.storage_in = storage_in;
    }

    public Long getStorage_out() {
        return storage_out;
    }

    public void setStorage_out(Long storage_out) {
        this.storage_out = storage_out;
    }

    public Long getCoun() {
        return coun;
    }

    public void setCoun(Long coun) {
        this.coun = coun;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    

}
