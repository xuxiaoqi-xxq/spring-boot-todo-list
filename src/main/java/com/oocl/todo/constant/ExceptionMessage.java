package com.oocl.todo.constant;

public enum ExceptionMessage {

    NO_SUCH_DATA("No Such Data"),

    ERROR_PARAMETER("Error parameter");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
