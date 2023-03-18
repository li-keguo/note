package com.example.i18n.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * ApiResult.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> {
    
    private int status;
    
    private String message;
    
    private T data;
    
    
    public static <T> ApiResult<T> ok() {
        return new ApiResult<>(HttpStatus.OK.value(), HttpStatus.OK.name(), null);
    }
    
    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult<>(HttpStatus.OK.value(), HttpStatus.OK.name(), data);
    }
    
    public static <T> ApiResult<T> ok(T data, String message) {
        return new ApiResult<>(HttpStatus.OK.value(), message, data);
    }
    
    public static <T> ApiResult<T> error() {
        return new ApiResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), null);
    }
    
    public static <T> ApiResult<T> error(String message) {
        return new ApiResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
    }
}
