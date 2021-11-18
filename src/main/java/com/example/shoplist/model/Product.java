package com.example.shoplist.model;

import lombok.Data;

import javax.persistence.*;

@Table(name = "product")
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private int id;

    private String productname;

    private String unitprice;

    private int quantity;

    private int selledquantity;

    private int idshop;
}
