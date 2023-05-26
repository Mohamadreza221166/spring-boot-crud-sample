package com.example.springcrudsample.config;

/**
 * constants.
 */
public final class Constants {
    // برای فرمت درست نام کاربری
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";
    public static final String SYSTEM = "system";
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String APPLICATION_JSON = "application/json";
    public static final String ID_EXISTS = "idexists";
    public static final String ID_NULL = "idnull";
    public static final String ID_INVALID = "idinvalid";
    public static final String ID_NOT_FOUND = "idnotfound";

    private Constants() {}
}
