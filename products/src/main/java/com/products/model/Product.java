
package com.products.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

/* @author sgtomar
* Product Entity mapped to Product table
*/
@Entity
@Table(name = "Product")
@Data
public class Product {

    @Id
    @Column(name = "id", columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(50)")
    private String name;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "brand", columnDefinition = "varchar(50)")
    private String brand;

    @Column(name = "onsale", columnDefinition = "Boolean")
    private Boolean onSale;

    public Product() {

    }

    public Product(Integer id, String name, BigDecimal price, String brand, Boolean onSale) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.onSale = onSale;
    }

}
