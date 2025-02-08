package com.muhammet.config;

public class RestApis {

    private static final String VERSION = "/v1";
    private static final String DEV = "/dev";
    private static final String BASE_URL = DEV + VERSION;

    public static final String KULLANICI = BASE_URL + "/kullanici";

    public static final String DOREGISTER = "/register";
    public static final String LOGIN = "/login";

}
