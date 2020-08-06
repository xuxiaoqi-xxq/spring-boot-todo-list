package com.oocl.todo.constant;

public enum ExceptionMessage {

    NO_SUCH_DATA("No Such Data");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
