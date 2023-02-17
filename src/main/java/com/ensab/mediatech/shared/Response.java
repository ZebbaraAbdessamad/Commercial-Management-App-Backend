package com.ensab.mediatech.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder  //which generates a builder pattern for the class | by create instances of the Response class using a fluent API
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    protected LocalDateTime timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected  String  reason;
    protected  String  message;
    protected  String  developerMessage;
    protected Map<?,?> data;

}

/* reason: In HTTP, each status code is accompanied by a reason
   phrase that provides a brief explanation of the status code.
   Ex:.reason("Success")
*/

// developerMessage: This field can be used to provide additional information about the response that may not be appropriate
// for a general audience, or to provide technical details.
//Ex :  .developerMessage("An exception was thrown: java.lang.NullPointerException")
