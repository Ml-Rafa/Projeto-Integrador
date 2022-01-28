package br.com.meli.wave4.exceptions;

public class UnregisteredProductException extends RuntimeException{
    public UnregisteredProductException(String message) {
        super(message);
    }

    public UnregisteredProductException(){

    }
}