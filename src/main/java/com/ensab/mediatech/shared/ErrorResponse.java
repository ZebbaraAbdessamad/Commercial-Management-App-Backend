package com.ensab.mediatech.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder //create instances of the ErrorResponse class using a fluent API
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    protected LocalDateTime timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected  String  message;
    protected Map<?,?> errors;



}
