package com.detector.pnutour.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    UNKNOWN_SERVER_ERROR(HttpStatus.CONFLICT,"");


    private HttpStatus httpStatus;
    private String message;
}
