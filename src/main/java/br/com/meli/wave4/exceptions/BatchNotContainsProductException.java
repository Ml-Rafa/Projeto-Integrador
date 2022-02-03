package br.com.meli.wave4.exceptions;

public class BatchNotContainsProductException extends RuntimeException{

    public BatchNotContainsProductException(String message){
        super(message);
    }

    public BatchNotContainsProductException(){}
}
