package com.javator.vm.entities;

import javax.persistence.*;

@Entity
@Table(name="Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pid")
    private Integer pid;

    @Column(name = "pname")
    private String pname;

    @Column(name = "pamount")
    private String pamount;
}