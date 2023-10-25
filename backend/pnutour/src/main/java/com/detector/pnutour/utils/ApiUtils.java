package com.detector.pnutour.utils;

import com.detector.pnutour.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class ApiUtils {

    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> error(String message, ErrorCode errorCode, HttpStatus status) {
        return new ApiResult<>(false, null, new ApiError(message,errorCode, status.value()));
    }

    @Getter @Setter @AllArgsConstructor
    public static class ApiResult<T> {
        private final boolean success;
        private final T response;
        private final ApiError error;
    }

    @Getter @Setter @AllArgsConstructor
    public static class ApiError {
        private final String message;
        private final ErrorCode errorCode;
        private final int status;
    }
}
