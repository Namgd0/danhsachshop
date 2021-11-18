package com.example.shoplist.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idshop")
    private int id;

    private String shopname;

    private String shopaddress;

    private String shoptel;

    private String shopdesc;

    private int totalselledproduct;

    private int userid;
}
