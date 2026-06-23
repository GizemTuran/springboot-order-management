package com.springboot;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotBlank
    public String name;
    @NotNull
    @Positive
    public Double price;
    @NotNull
    @Positive
    public Integer amount;


    public Product(Long id, String name, Double price, Integer amount){
        this.id=id;
        this.name=name;
        this.price=price;
        this.amount=amount;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public Double getPrice(){
        return price;
    }

    public Integer getAmount(){
        return amount;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setPrice(Double price){
        this.price=price;
    }

    public void setAmount(Integer amount){
        this.amount=amount;
    }
}
