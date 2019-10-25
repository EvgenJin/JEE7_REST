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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "PRODUCTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p")
    , @NamedQuery(name = "Products.findById", query = "SELECT p FROM Products p WHERE p.id = :id")
    , @NamedQuery(name = "Products.findByMainCategory", query = "SELECT p FROM Products p WHERE p.mainCategory = :mainCategory")
    , @NamedQuery(name = "Products.findByCategory", query = "SELECT p FROM Products p WHERE p.category = :category")
    , @NamedQuery(name = "Products.findBySubCategory", query = "SELECT p FROM Products p WHERE p.subCategory = :subCategory")
    , @NamedQuery(name = "Products.findByPrice", query = "SELECT p FROM Products p WHERE p.price = :price")
    , @NamedQuery(name = "Products.findByArtNumber", query = "SELECT p FROM Products p WHERE p.artNumber = :artNumber")
    , @NamedQuery(name = "Products.findByName", query = "SELECT p FROM Products p WHERE p.name = :name")
    , @NamedQuery(name = "Products.findByManufacturer", query = "SELECT p FROM Products p WHERE p.manufacturer = :manufacturer")
    , @NamedQuery(name = "Products.findByColor", query = "SELECT p FROM Products p WHERE p.color = :color")
    , @NamedQuery(name = "Products.findByPrStr", query = "SELECT p FROM Products p WHERE p.prStr = :prStr")
    , @NamedQuery(name = "Products.findByDescr", query = "SELECT p FROM Products p WHERE p.descr = :descr")
    , @NamedQuery(name = "Products.findByImg", query = "SELECT p FROM Products p WHERE p.img = :img")})
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 100)
    @Column(name = "MAIN_CATEGORY")
    private String mainCategory;
    @Size(max = 100)
    @Column(name = "CATEGORY")
    private String category;
    @Size(max = 100)
    @Column(name = "SUB_CATEGORY")
    private String subCategory;
    @Column(name = "PRICE")
    private BigInteger price;
    @Column(name = "ART_NUMBER")
    private BigInteger artNumber;
    @Size(max = 1999)
    @Column(name = "NAME")
    private String name;
    @Size(max = 100)
    @Column(name = "MANUFACTURER")
    private String manufacturer;
    @Size(max = 100)
    @Column(name = "COLOR")
    private String color;
    @Size(max = 100)
    @Column(name = "PR_STR")
    private String prStr;
    @Size(max = 4000)
    @Column(name = "DESCR")
    private String descr;
    @Size(max = 4000)
    @Column(name = "IMG")
    private String img;
    @Transient
    private byte[] qrcode;    

    public Products() {
    }

    public Products(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public BigInteger getArtNumber() {
        return artNumber;
    }

    public void setArtNumber(BigInteger artNumber) {
        this.artNumber = artNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrStr() {
        return prStr;
    }

    public void setPrStr(String prStr) {
        this.prStr = prStr;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    public byte[] getQrcode() {
        return qrcode;
    }

    public void setQrcode(byte[] qrcode) {
        this.qrcode = qrcode;
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Entity.Products[ id=" + id + " ]";
    }
    
}
