package com.trio.challenge.handler;

import com.trio.challenge.exception.CrossIntegrityException;
import com.trio.challenge.exception.ItemNotFoundException;
import com.trio.challenge.handler.dto.ErrorCode;
import com.trio.challenge.handler.dto.ErrorDetail;
import com.trio.challenge.handler.dto.ErrorDetailCode;
import com.trio.challenge.handler.dto.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorHandler {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(ItemNotFoundException exception){
        return buildResponse(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND, exception.getMessage(), Arrays.asList());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(CrossIntegrityException exception){
        return buildResponse(HttpStatus.BAD_REQUEST, ErrorCode.NOT_FOUND, exception.getMessage(), Arrays.asList());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(ResponseStatusException exception){
        return this.buildResponse(HttpStatus.BAD_REQUEST, ErrorResponse.builder()
                .message(exception.getMessage()).build());
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleRequestBodyValidationErrors(MethodArgumentNotValidException exception) {
        List<ErrorDetail> errorDetails = exception.getBindingResult().getFieldErrors().stream()
                .map(validationError ->
                        new ErrorDetail(
                                ErrorDetailCode.INVALID_FIELD,
                                validationError.getDefaultMessage()
                        )
                ).toList();
        return buildBadRequestResponse(errorDetails);
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<ErrorResponse> handleRequestBodyValidationErrors(BindException exception) {
        List<ErrorDetail> errorDetails = exception.getBindingResult().getFieldErrors().stream()
                .map(validationError ->
                        new ErrorDetail(
                                ErrorDetailCode.INVALID_FIELD,
                                validationError.getDefaultMessage()
                        )
                ).toList();
        return buildBadRequestResponse(errorDetails);
    }

    private ResponseEntity<ErrorResponse> buildBadRequestResponse(List<ErrorDetail> details) {
        return buildResponse(HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getDescription(), details);
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus statusCode, ErrorCode errorCode, String message, List<ErrorDetail> details) {
        return this.buildResponse(statusCode, new ErrorResponse(errorCode, message, details));
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus statusCode, ErrorResponse errorResponse) {
        return ResponseEntity.status(statusCode).body(errorResponse);
    }

}
