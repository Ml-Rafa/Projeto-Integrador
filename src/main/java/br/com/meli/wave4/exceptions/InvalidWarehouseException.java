package br.com.meli.wave4.exceptions;

public class InvalidWarehouseException extends RuntimeException{
    public InvalidWarehouseException(String message) {
        super(message);
    }

    public InvalidWarehouseException(){

    }
}