/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "PERSON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
    , @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id")
    , @NamedQuery(name = "Person.findByFullname", query = "SELECT p FROM Person p WHERE p.fullname = :fullname")
    , @NamedQuery(name = "Person.findByDateOfBirth", query = "SELECT p FROM Person p WHERE p.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "Person.findByInn", query = "SELECT p FROM Person p WHERE p.inn = :inn")
    , @NamedQuery(name = "Person.findByPfr", query = "SELECT p FROM Person p WHERE p.pfr = :pfr")
    , @NamedQuery(name = "Person.findBySex", query = "SELECT p FROM Person p WHERE p.sex = :sex")
    , @NamedQuery(name = "Person.findByAddres", query = "SELECT p FROM Person p WHERE p.addres = :addres")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID")
    private Integer id;
    
    @Size(max = 255)
//    @NotNull
    @Column(name = "FULLNAME")
    private String fullname;
    
    @Column(name = "DATE_OF_BIRTH")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    
    @Column(name = "INN")
    private BigInteger inn;
    
    @Size(max = 255)
    @Column(name = "PFR")
    private String pfr;
    
    @Size(max = 26)
    @Column(name = "SEX")
    private String sex;
    
    @Size(max = 1999)
    @Column(name = "ADDRES")
    private String addres;

    public Person() {
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

    public Person(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
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
