package com.prunny.bookstore.core.handler;

import com.prunny.bookstore.core.BlankCredentialsException;
import com.prunny.bookstore.core.DuplicateCredentialsException;
import com.prunny.bookstore.core.InvalidCredentialsException;
import com.prunny.bookstore.core.model.ExceptionModel;
import com.prunny.bookstore.dto.response.ResponseDto;
import com.prunny.bookstore.enums.ResponseCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ResponseDto<ExceptionModel>> handleInvalidCredentialsException(InvalidCredentialsException invalidCredentialsException,
                                                                                   HttpServletRequest httpServletRequest){
        ResponseDto<ExceptionModel> resp = new ResponseDto<>();
        ExceptionModel exceptionModel = ExceptionModel.builder()
                .path(httpServletRequest.getRequestURI())
                .message(invalidCredentialsException.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .time(LocalDateTime.now())
                .build();
        resp.setData(exceptionModel);
        resp.setStatusCode(ResponseCode.FAILED.getCode());
        resp.setStatusMessage(ResponseCode.FAILED.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Map<String, String>>> invalidException(MethodArgumentNotValidException e, HttpServletRequest request){
        ResponseDto<Map<String, String>> resp = new ResponseDto<>();
        Map<String, String> invalidErrors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(error ->{
                    String fieldError = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    invalidErrors.put(fieldError, message);
                });
        invalidErrors.put("path", request.getRequestURI());
        invalidErrors.put("time", LocalDateTime.now().toString());
        invalidErrors.put("statusCode", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        resp.setData(invalidErrors);
        resp.setStatusCode(ResponseCode.FAILED.getCode());
        resp.setStatusMessage(ResponseCode.FAILED.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateCredentialsException.class)
    public ResponseEntity<ResponseDto<ExceptionModel>> handleDuplicateCredentialsException(DuplicateCredentialsException duplicateCredentialsException,
                                                                                   HttpServletRequest httpServletRequest){
        ResponseDto<ExceptionModel> resp = new ResponseDto<>();
        ExceptionModel exceptionModel = ExceptionModel.builder()
                .path(httpServletRequest.getRequestURI())
                .message(duplicateCredentialsException.getMessage())
                .statusCode(HttpStatus.CONFLICT.value())
                .time(LocalDateTime.now())
                .build();
        resp.setData(exceptionModel);
        resp.setStatusCode(ResponseCode.FAILED.getCode());
        resp.setStatusMessage(ResponseCode.FAILED.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BlankCredentialsException.class)
    public ResponseEntity<ResponseDto<ExceptionModel>> handleBlankCredentialsException(BlankCredentialsException blankCredentialsException,
                                                                                           HttpServletRequest httpServletRequest){
        ResponseDto<ExceptionModel> resp = new ResponseDto<>();
        ExceptionModel exceptionModel = ExceptionModel.builder()
                .path(httpServletRequest.getRequestURI())
                .message(blankCredentialsException.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .time(LocalDateTime.now())
                .build();
        resp.setData(exceptionModel);
        resp.setStatusCode(ResponseCode.FAILED.getCode());
        resp.setStatusMessage(ResponseCode.FAILED.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

}
