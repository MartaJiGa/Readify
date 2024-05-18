package com.svalero.readify.domain;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {
    private int code;
    private String message;

    public ErrorResponse(int errorCode, String errorMessage){
        code = errorCode;
        message = errorMessage;
    }
}
