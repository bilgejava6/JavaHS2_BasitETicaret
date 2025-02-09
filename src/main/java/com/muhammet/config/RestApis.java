package com.muhammet.config;

public class RestApis {

    private static final String VERSION = "/v1";
    private static final String DEV = "/dev";
    private static final String BASE_URL = DEV + VERSION;

    public static final String KULLANICI = BASE_URL + "/kullanici";
    public static final String KATEGORI = BASE_URL + "/kategori";
    public static final String URUN = BASE_URL + "/urun";
    public static final String SEPET = BASE_URL + "/sepet";

    public static final String DOREGISTER = "/register";
    public static final String LOGIN = "/login";

    public static final String ADD_KATEGORI = "/add-kategori";
    public static final String UPDATE_KATEGORI = "/update-kategori";
    public static final String DELETE_KATEGORI = "/delete-kategori";
    public static final String GET_ALL_KATEGORI = "/get-all-kategori";
    public static final String FIND_KATEGORI_BY_NAME = "/find-kategori-by-name";
    public static final String GET_MAIN_KATEGORI = "/get-main-kategori";
    public static final String GET_SUB_KATEGORI = "/get-sub-kategori";
    public static final String ADD_URUN = "/add-urun";
    public static final String GET_ALL_URUN = "/get-all-urun";
    public static final String FIND_BY_URUN_ADI = "/find-by-urun-adi";
    public static final String DELETE_URUN = "/delete-urun";








}
