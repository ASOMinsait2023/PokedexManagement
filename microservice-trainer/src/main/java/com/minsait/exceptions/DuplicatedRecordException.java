package com.minsait.exceptions;

public class DuplicatedRecordException extends RuntimeException {
    public DuplicatedRecordException(String message){
        super(message);
    }
}
