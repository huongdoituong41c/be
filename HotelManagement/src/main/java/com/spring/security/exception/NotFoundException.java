package com.spring.security.exception;

import com.spring.security.dto.ErrorExceptionDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotFoundException extends Throwable {
    private ErrorExceptionDto errorObject;
}

