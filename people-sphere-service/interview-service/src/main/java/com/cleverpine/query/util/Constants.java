package com.cleverpine.query.util;

public final class Constants {

    // Security
    public static final String BEARER_PREFIX = "Bearer ";
    // Error Messages
    public static final String INVALID_JWT_TOKEN_ERROR_MESSAGE = "Invalid JWT token";

    private Constants() {
        throw new AssertionError("Cannot create instances of this class");
    }
}
