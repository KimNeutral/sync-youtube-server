package com.neutral.sync_youtube.common.support;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestResponse<T> {
    private String message;
    private T data;
    private List<Error> errors;

    private RestResponse() {
    }

    private RestResponse(List<Error> errors) {
        this.errors = errors;
    }

    private RestResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public static <T> RestResponse<T> success(String message, T data) {
        return new RestResponse<>(message, data);
    }

    public static <T> RestResponse<T> success(T data) {
        return new RestResponse<>("", data);
    }

    public static <T> RestResponse<T> success() {
        return new RestResponse<>();
    }

    public static <T> ErrorResponseBuilder error() {
        return new ErrorResponseBuilder();
    }

    public static <T> ErrorResponseBuilder error(String message) {
        return new ErrorResponseBuilder(new Error(null, message));
    }

    public static <T> ErrorResponseBuilder error(String field, String message) {
        return new ErrorResponseBuilder(new Error(field, message));
    }

    public static class ErrorResponseBuilder {
        private List<Error> errors = new ArrayList<>();

        private ErrorResponseBuilder() {
        }

        private ErrorResponseBuilder(Error error) {
            this.errors.add(error);
        }

        public ErrorResponseBuilder appendError(String field, String errorMessage) {
            errors.add(new Error(field, errorMessage));
            return this;
        }

        public ErrorResponseBuilder appendError(String errorMessage) {
            return appendError(null, errorMessage);
        }

        public RestResponse<?> build() {
            return new RestResponse(this.errors);
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private static class Error {
        private String field;
        private String message;
    }
}
