package com.trio.challenge.handler.dto;

public enum ErrorCode {
    //If we add ErrorCode to

    BAD_REQUEST("The server could not understand the request."),
    NOT_FOUND("Item not found."),
    CONFLICT("The request is in conflict with db"),
    FORBIDDEN("User doesn't have permission."),
    INTERNAL_ERROR("There was an unexpected error");

    private String description;

    ErrorCode(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
