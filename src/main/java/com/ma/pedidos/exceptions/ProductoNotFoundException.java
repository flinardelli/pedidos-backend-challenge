package com.ma.pedidos.exceptions;

public class ProductoNotFoundException extends RuntimeException{

    public ProductoNotFoundException(){
        super("El producto no existe");
    }

}
