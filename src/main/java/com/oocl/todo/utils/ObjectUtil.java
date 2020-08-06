package com.oocl.todo.utils;

import com.oocl.todo.dto.TodoRequest;

public class ObjectUtil {

    public static Boolean isTodoEmpty(TodoRequest todoRequest) {
        if(todoRequest.getStatus() == null && todoRequest.getContent() == null){
            return true;
        }
        return false;
    }
}
