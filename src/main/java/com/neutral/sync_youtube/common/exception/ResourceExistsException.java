package com.neutral.sync_youtube.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceExistsException extends RuntimeException {
    // TODO: 메세지관리하는 클래스 하나 만들고 삭제.
    public ResourceExistsException() {
    }

    public ResourceExistsException(String message) {
        super(message);
    }
}
