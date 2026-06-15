package com.springboot;

public class Product {

    public Long id;
    public String name;
    public Double price;
    public Integer amount;

    public Product(Long id, String name, Double price, Integer amount){
        this.id=id;
        this.name=name;
        this.price=price;
        this.amount=amount;
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
