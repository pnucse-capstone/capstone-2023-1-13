package com.detector.pnutour.exception;
import com.detector.pnutour.utils.ApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionManager {
//    private final ErrorLogRepository errorLogRepository;

//    public ExceptionManager(ErrorLogRepository errorLogRepository) {
//        this.errorLogRepository = errorLogRepository;
//    }


    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> appExceptionHandler(AppException e){
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ApiUtils.error(e.getMessage(),e.getErrorCode(),e.getErrorCode().getHttpStatus()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e,HttpServletRequest request){
//        ErrorLog errorLog = ErrorLog.builder()
//                .message(e.getMessage())
//                .userAgent(request.getHeader("User-Agent"))
//                .userIp(request.getRemoteAddr())
//                .userId(user.getId())
//                .build();
//        errorLogRepository.save(errorLog);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiUtils.error(e.getMessage(),ErrorCode.UNKNOWN_SERVER_ERROR,HttpStatus.CONFLICT));
    }
}
