package com.example.springcrudsample.exceptions;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class BadRequestAlertException extends Exception {

    private String entityName;
    private String errorKey;

    public BadRequestAlertException(String message) {
        super(message);
    }

    public BadRequestAlertException(String defaultMessage, String entityName, String errorKey) {
        super(defaultMessage);
    }

    public BadRequestAlertException(URI type, String defaultMessage, String entityName, String errorKey) {
        super(type +":"+ defaultMessage +":"+ entityName +":"+ errorKey);
        this.entityName = entityName;
        this.errorKey = errorKey;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getErrorKey() {
        return errorKey;
    }

    private static Map<String, Object> getAlertParameters(String entityName, String errorKey) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", "error." + errorKey);
        parameters.put("params", entityName);
        return parameters;
    }
}
