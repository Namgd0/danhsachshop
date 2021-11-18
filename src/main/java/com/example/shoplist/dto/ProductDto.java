package com.example.shoplist.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productname;

    private String unitprice;

    private int quantity;

    private int selledquantity;

    private int idshop;

}
