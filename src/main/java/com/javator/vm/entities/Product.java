package com.javator.vm.entities;

import lombok.Data;

import javax.persistence.*;

@Data
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
    private Integer pamount;

    @Override
    public String toString() {
        return
                pid + " " + pname +  " " + pamount;
    }
}