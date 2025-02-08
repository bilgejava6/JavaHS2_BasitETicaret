package com.muhammet.exception;

import lombok.Getter;

@Getter
public class ETicaretException extends RuntimeException{
    private ErrorType errorType;
    public ETicaretException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

}
