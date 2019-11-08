/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eshahov
 */
@XmlRootElement
public class ProductsInStorages {
    private String address;
    private String code;
    private String name;
    private BigDecimal ost;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOst() {
        return ost;
    }

    public void setOst(BigDecimal ost) {
        this.ost = ost;
    }
    
    
}
