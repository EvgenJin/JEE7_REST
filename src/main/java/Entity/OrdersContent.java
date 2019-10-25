/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "ORDERS_CONTENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdersContent.findAll", query = "SELECT o FROM OrdersContent o")
    , @NamedQuery(name = "OrdersContent.findById", query = "SELECT o FROM OrdersContent o WHERE o.id = :id")
    , @NamedQuery(name = "OrdersContent.findByProductid", query = "SELECT o FROM OrdersContent o WHERE o.productid = :productid")
    , @NamedQuery(name = "OrdersContent.findByTitle", query = "SELECT o FROM OrdersContent o WHERE o.title = :title")
    , @NamedQuery(name = "OrdersContent.findByDescription", query = "SELECT o FROM OrdersContent o WHERE o.description = :description")
    , @NamedQuery(name = "OrdersContent.findByType", query = "SELECT o FROM OrdersContent o WHERE o.type = :type")
    , @NamedQuery(name = "OrdersContent.findByCategory", query = "SELECT o FROM OrdersContent o WHERE o.category = :category")
    , @NamedQuery(name = "OrdersContent.findByOrderid", query = "SELECT o FROM OrdersContent o WHERE o.orderid = :orderid")
    , @NamedQuery(name = "OrdersContent.findByPrice", query = "SELECT o FROM OrdersContent o WHERE o.price = :price")})
public class OrdersContent implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "PRODUCTID")
    private BigInteger productid;
    @Size(max = 255)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 255)
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "PRICE")
    private Integer price;
    @Column(name = "ORDERID")
    private Long orderid;


    

    public OrdersContent() {
    }

    public OrdersContent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getProductid() {
        return productid;
    }

    public void setProductid(BigInteger productid) {
        this.productid = productid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }    



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Controller.OrdersContent[ id=" + id + " ]";
    }
    
}
