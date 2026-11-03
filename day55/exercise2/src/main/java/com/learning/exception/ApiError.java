package com.learning.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;

// The body of every error answer. Already written. Empty parts are left out of the JSON:
// {"status":404,"message":"No member with id 9"} or {"status":400,"errors":{"phone":"..."}}
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private final int status;
    private final String message;
    private final Map<String, String> errors;

    private ApiError(int status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public static ApiError message(int status, String message) {
        return new ApiError(status, message, null);
    }

    public static ApiError fields(int status, Map<String, String> errors) {
        return new ApiError(status, null, errors);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
