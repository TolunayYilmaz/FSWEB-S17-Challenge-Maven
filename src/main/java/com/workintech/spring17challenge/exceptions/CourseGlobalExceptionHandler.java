package com.workintech.spring17challenge.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Interceptor
@ControllerAdvice
public class CourseGlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleException(ApiException courseException){
        ApiErrorResponse errorResponse = new ApiErrorResponse(courseException.getHttpStatus().value(),courseException.getMessage(),System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse,courseException.getHttpStatus());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception){
        ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}