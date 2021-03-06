package Entity;


import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author eshahov
 */
@Entity
@Table(name = "PERSON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
    , @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id")
    , @NamedQuery(name = "Person.findByAddres", query = "SELECT p FROM Person p WHERE lower(p.addres) like lower(:addres)")
    , @NamedQuery(name = "Person.findByDateOfBirth", query = "SELECT p FROM Person p WHERE p.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "Person.findByFullname", query = "SELECT p FROM Person p WHERE p.fullname = :fullname")
    , @NamedQuery(name = "Person.findByInn", query = "SELECT p FROM Person p WHERE p.inn = :inn")
    , @NamedQuery(name = "Person.findByPfr", query = "SELECT p FROM Person p WHERE p.pfr = :pfr")
    , @NamedQuery(name = "Person.findBySex", query = "SELECT p FROM Person p WHERE p.sex = :sex")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "ADDRES")
    private String addres;
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;
    @Size(max = 255)
    @Column(name = "FULLNAME")
    private String fullname;
    @Column(name = "INN")
    private BigInteger inn;
    @Size(max = 255)
    @Column(name = "PFR")
    private String pfr;
    @Size(max = 255)
    @Column(name = "SEX")
    private String sex;
    @Size(max = 255)
    @Column(name = "F_NAME")
    private String fname;
    @Size(max = 255)
    @Column(name = "S_NAME")
    private String sname;
    @Size(max = 255)
    @Column(name = "T_NAME")
    private String tname;
    @Transient
    private byte[] qrcode;   
    @Transient
    private List<Orders> orders_list;


/* массив с заказами у персона - тормохит запрос    
    //mappedBy указывает, что объект в этом сторонe является обратной зависимостью, а владелец находится в "другой" сущности.
    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //чтобы не было рекурсии по ордерам - персонам - ордерам
    @JsonManagedReference
    private List<Orders> orders;

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }  
*/


    public Person() {
    }

    public Person(Long id) {
        this.id = id;
    }
    
    public void setPerson(Person person) {
        this.addres = person.getAddres();
        this.dateOfBirth = person.getDateOfBirth();
        this.fullname = person.getFullname();
        this.id = person.getId();
        this.inn = person.getInn();
        this.pfr = person.getPfr();
        this.sex = person.getSex();
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public BigInteger getInn() {
        return inn;
    }

    public void setInn(BigInteger inn) {
        this.inn = inn;
    }

    public String getPfr() {
        return pfr;
    }

    public void setPfr(String pfr) {
        this.pfr = pfr;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
    
    public byte[] getQrcode() {
        return qrcode;
    }

    public void setQrcode(byte[] qrcode) {
        this.qrcode = qrcode;
    }

    public List<Orders> getOrders_list() {
        return orders_list;
    }

    public void setOrders_list(List<Orders> orders_list) {
        this.orders_list = orders_list;
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
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Person[ id=" + id + " ]";
    }
    
}
