/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
 * @author eshahov
 */
@Entity
@Table(name = "ORDERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o")
    , @NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.id = :id")
    , @NamedQuery(name = "Orders.findByDescription", query = "SELECT o FROM Orders o WHERE o.description = :description")
    , @NamedQuery(name = "Orders.findByOrderid", query = "SELECT o FROM Orders o WHERE o.orderid = :orderid")
    , @NamedQuery(name = "Orders.findByPrice", query = "SELECT o FROM Orders o WHERE o.price = :price")
    , @NamedQuery(name = "Orders.findByTitle", query = "SELECT o FROM Orders o WHERE o.title = :title")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ORDERID")
    private BigInteger orderid;
    @Column(name = "PRICE")
    private BigInteger price;
    @Size(max = 255)
    @Column(name = "TITLE")
    private String title;
//    @JoinTable(name = "PERSON_ORDERS", joinColumns = {
//        @JoinColumn(name = "ORDERS_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
//        @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")})
//    @ManyToMany
//    private List<Person> personList;
//    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
//    @ManyToOne
//    private Person personId;
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="ID")
//    private Person person;    
    // CascadeType.ALL все операции изменения в коллекции (добавление/изменение) отражает в базе данных   
    // FetchType.EAGER – Предусматривает получение полной связи между сущностями, и последующих обращениях к связям не будет выполнять запрос на получение данных, тк данные изначально получены полностью.
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //  @JoinColumn указывает, что этот объект является владельцем отношения (то есть: соответствующая таблица имеет столбец с внешним ключом в ссылочной таблице)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    //чтобы не было рекурсии по персонам - ордерам -  персонам
    @JsonBackReference
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    public Orders() {
    }

    public Orders(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getOrderid() {
        return orderid;
    }

    public void setOrderid(BigInteger orderid) {
        this.orderid = orderid;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
//    public Person getPerson() {
//        return person;
//    }
//
//    public void setPerson(Person person) {
//        this.person = person;
//    }
//    @XmlTransient
//    public List<Person> getPersonList() {
//        return personList;
//    }
//
//    public void setPersonList(List<Person> personList) {
//        this.personList = personList;
//    }
//
//    public Person getPersonId() {
//        return personId;
//    }
//
//    public void setPersonId(Person personId) {
//        this.personId = personId;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Orders[ id=" + id + " ]";
    }
    
}
