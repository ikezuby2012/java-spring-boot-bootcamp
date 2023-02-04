package com.example.basicApp.model.Response;

public enum ErrorMessage {
    MISSING_REQUIRED_FIELD("missing required field. please check documentation for required field"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    INTERNAL_SERVER_ERROR("internal server error"),
    NO_RECORD_FOUND("Record with provided id is not provided"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORDS("could not update records"),
    COULD_NOT_DELETE_RECORDS("could not delete records"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified");

    private String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage=errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
