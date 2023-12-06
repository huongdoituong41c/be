package com.spring.security.enumeration;

import com.spring.security.dto.ErrorExceptionDto;

public enum EApplicationError implements ErrorObjectInterface{

    ROOM_NOT_FOUND("R01", "Room Not Found"),
    HOTEL_NOT_FOUND("H01","Hotel Not Found"),
    IMAGE_NOT_NULL("H02","Image Not Empty"),
    INVALID_PARAM("CE001", "Invalid Param");
    private String code;
    private String message;

    EApplicationError(String code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public ErrorExceptionDto getErrorObject() {
        return new ErrorExceptionDto(this.code, this.message);
    }
    public ErrorExceptionDto getErrorObjectWithMessage(String message) {
        return new ErrorExceptionDto(this.code, message);
    }
}
