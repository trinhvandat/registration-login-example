package com.spring.demo.auth.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Data
@AllArgsConstructor
public class ResourceNotFoundException  extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private Object fieldValue;



}
