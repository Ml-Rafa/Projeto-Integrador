package br.com.meli.wave4.exceptions;

public class UnavailableDateException extends RuntimeException{
    public UnavailableDateException(String message) {
        super(message);
    }

    public UnavailableDateException(){

    }
}
