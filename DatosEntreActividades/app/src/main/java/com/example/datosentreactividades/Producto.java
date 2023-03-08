package com.example.datosentreactividades;

public class Producto {
    private String name;
    private float price;
    private float weight;

    public Producto(String name,float price,float weight){
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public float getWeight() {
        return weight;
    }
}
