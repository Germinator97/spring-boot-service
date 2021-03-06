package com.cinetpay.billing.infrastructure.exception;

import java.util.List;
import java.util.stream.Collectors;

import com.cinetpay.billing.application.response.ResponseHandler;

import java.util.Set;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {

      List<String> errors = ex.getBindingResult()
          .getFieldErrors()
          .stream()
          .map(DefaultMessageSourceResolvable::getDefaultMessage)
          .collect(Collectors.toList());
  
      return ResponseHandler.generateResponse(status.value(), false, status.name(), errors, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
        MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

      return ResponseHandler.generateResponse(status.value(), false, ex.getMessage(),null, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

          Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();

          return ResponseHandler.generateResponse(status.value(), false, status.name(), supportedMethods, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();

        int indexValueStart = message.indexOf("[") + 1;
        int indexValueEnd = message.indexOf("];");
        CharSequence value = message.subSequence(indexValueStart, indexValueEnd);

        int indexKeyStart = message.indexOf("Dto[") + 5;
        int indexKeyEnd = message.indexOf("])") - 1;
        CharSequence key = message.subSequence(indexKeyStart, indexKeyEnd);

        String result = "The " + key + " can be : " + value;

      return ResponseHandler.generateResponse(status.value(), false, status.name(), result, status);
    }

}
