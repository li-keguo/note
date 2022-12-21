
package com.example.springvalidatordemo.advice;

import com.example.springvalidatordemo.model.entity.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ControllerMethodResolver.
 *
 * @see <a href="https://dzone.com/articles/global-exception-handling-with-controlleradvice">global-exception-handling-with-controlleradvice</a>
 */
@ResponseBody
@ControllerAdvice
public class ExceptionHandlers {
    
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlers.class);
    
    @ExceptionHandler(Exception.class)
    protected ApiResult<Object> handleExceptionHandler(final Exception exception) {
        LOG.error(exception.getMessage(), exception);
        return ApiResult.error(exception.getMessage());
    }
    
    
    @ExceptionHandler(NullPointerException.class)
    protected ApiResult<Object> handleNullPointException(final NullPointerException exception) {
        LOG.error("null pointer exception ", exception);
        return ApiResult.error("system error");
    }
    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ApiResult<Object> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        LOG.warn("http request method not supported", e);
        StringBuilder sb = new StringBuilder();
        sb.append(e.getMethod());
        sb.append(
                " method is not supported for this request. Supported methods are ");
        Objects.requireNonNull(e.getSupportedHttpMethods()).forEach(t -> sb.append(t).append(" "));
        return ApiResult.error(sb.toString());
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ApiResult<Object> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        LOG.warn("method argument not valid", e);
        BindingResult bindingResult = e.getBindingResult();
        String errorMsg = bindingResult.getFieldErrors().stream()
                .map(f -> f.getField().concat(": ").concat(Optional.ofNullable(f.getDefaultMessage()).orElse("")))
                .collect(Collectors.joining("| "));
        return ApiResult.error(String.format("Request error! invalid argument [%s]", errorMsg));
    }
    
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ApiResult<Object> handleMissingServletRequestParameterException(final MissingServletRequestParameterException e) {
        LOG.warn("missing servlet request parameter", e);
        return ApiResult.error(String.format("%s parameter is missing", e.getParameterName()));
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ApiResult<Object> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        LOG.warn("method argument type mismatch", e);
        return ApiResult.error(String.format("%s should be of type %s", e.getName(), Objects.requireNonNull(e.getRequiredType()).getName()));
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    protected ApiResult<Object> handleConstraintViolationException(final ConstraintViolationException e) {
        LOG.warn("constraint violation exception", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        return ApiResult.error(violations.stream()
                .map(v -> v.getPropertyPath().toString().concat(": ").concat(v.getMessage()))
                .collect(Collectors.joining("| ")));
    }
    
}
