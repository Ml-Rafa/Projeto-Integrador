package br.com.meli.wave4.exceptions;

public class InvalidSectionException extends RuntimeException{
    public InvalidSectionException(String message) {
        super(message);
    }

    public InvalidSectionException(){

    }
}
