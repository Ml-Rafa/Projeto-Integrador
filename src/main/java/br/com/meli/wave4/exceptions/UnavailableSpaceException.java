package br.com.meli.wave4.exceptions;

public class UnavailableSpaceException extends RuntimeException{
    public UnavailableSpaceException(String message) {
        super(message);
    }

    public UnavailableSpaceException(){

    }
}