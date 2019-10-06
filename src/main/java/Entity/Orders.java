/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
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
 * @author eshahov
 */
@Entity
@Table(name = "ORDERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o")
    , @NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.id = :id")
    , @NamedQuery(name = "Orders.findByPerson", query = "SELECT o FROM Orders o WHERE o.personid = :personid")    
    , @NamedQuery(name = "Orders.findByDescription", query = "SELECT o FROM Orders o WHERE o.description = :description")
    , @NamedQuery(name = "Orders.findByTitle", query = "SELECT o FROM Orders o WHERE o.title = :title")})


public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "AMOUNT")
    private BigInteger amount;
    @Size(max = 255)
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DATE_IN")
    private Date datein;
    @Column(name = "DATE_OUT")
    private Date dateout;
    @Size(max = 255)
    @Column(name = "COMMENTS")
    private String comment;     
    // CascadeType.ALL все операции изменения в коллекции (добавление/изменение) отражает в базе данных   
    // FetchType.EAGER – Предусматривает получение полной связи между сущностями, и последующих обращениях к связям не будет выполнять запрос на получение данных, тк данные изначально получены полностью.
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //  @JoinColumn указывает, что этот объект является владельцем отношения (то есть: соответствующая таблица имеет столбец с внешним ключом в ссылочной таблице)
//    @JoinColumn(name = "PERSON_ID", nullable = false)
    //чтобы не было рекурсии по персонам - ордерам -  персонам
//    @JsonBackReference
    @NotNull
    @Column(name = "PERSON_ID")
    private Long personid;

    public Orders() {
    }
    
    public void setOrders(Orders order) {
        this.amount = order.getAmount();
        this.comment = order.getComment();
        this.datein = order.getDatein();
        this.dateout = order.getDateout();
        this.description = order.getDescription();
        this.id = order.getId();
        this.personid = order.getPersonid();
        this.title = order.getTitle();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public Date getDatein() {
        return datein;
    }

    public void setDatein(Date datein) {
        this.datein = datein;
    }

    public Date getDateout() {
        return dateout;
    }

    public void setDateout(Date dateout) {
        this.dateout = dateout;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public Long getPersonid() {
        return personid;
    }

    public void setPersonid(Long personid) {
        this.personid = personid;
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
