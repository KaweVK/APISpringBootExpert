package com.github.kawevk.libraryapi.exception;

public class OperationNotAllowedException extends  RuntimeException {
    public OperationNotAllowedException(String message) {
        super(message);
    }
}
