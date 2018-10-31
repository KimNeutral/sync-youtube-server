package com.neutral.sync_youtube.common.advice;

import com.neutral.sync_youtube.common.exception.BadRequestException;
import com.neutral.sync_youtube.common.exception.ForbiddenException;
import com.neutral.sync_youtube.common.exception.ResourceExistsException;
import com.neutral.sync_youtube.common.exception.UnauthorizedException;
import com.neutral.sync_youtube.common.support.RestResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class APIControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse<?> handleValidationException(MethodArgumentNotValidException exception) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        RestResponse.ErrorResponseBuilder errorResponseBuilder = RestResponse.error();

        for(ObjectError error : errors) {
            FieldError fieldError = (FieldError) error;
            errorResponseBuilder.appendError(fieldError.getField(), getErrorMessage(fieldError));
        }
        return errorResponseBuilder.build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse<?> handleBadRequestException(BadRequestException exception) {
        return getErrorRestResponse(exception);
    }

    @ExceptionHandler(ResourceExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse<?> handleResourceExistsException(ResourceExistsException exception) {
        return getErrorRestResponse(exception);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResponse<?> handleUnauthorizedException(UnauthorizedException exception) {
        return getErrorRestResponse(exception);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public RestResponse<?> handleForbiddenException(ForbiddenException exception) {
        return getErrorRestResponse(exception);
    }

    private RestResponse<?> getErrorRestResponse(Exception exception) {
        return getErrorRestResponse(exception.getMessage());
    }

    private RestResponse<?> getErrorRestResponse(String message) {
        return getErrorRestResponse(null, message);
    }

    private RestResponse<?> getErrorRestResponse(String field, String message) {
        return RestResponse.error(field, message).build();
    }

    // TODO: Implement this method.
    private String getErrorMessage(FieldError fieldError) {
        return "";
    }

}
