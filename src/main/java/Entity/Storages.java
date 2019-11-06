package Entity;

import java.io.Serializable;
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
@Table(name = "STORAGES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Storages.findAll", query = "SELECT s FROM Storages s")
   ,@NamedQuery(name = "Storages.findById", query = "SELECT s FROM Storages s WHERE s.id = :id")
   ,@NamedQuery(name = "Storages.findByCode", query = "SELECT s FROM Storages s WHERE s.code = :code")
})        
public class Storages implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 100)
    @Column(name = "NAME")
    private String name;
    @Size(max = 100)
    @Column(name = "CODE")
    private String code;
    @Size(max = 100)
    @Column(name = "ADDRESS")
    private String address;    
       
    public Storages() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    
}
