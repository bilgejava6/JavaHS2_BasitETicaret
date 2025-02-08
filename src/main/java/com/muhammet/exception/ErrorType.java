package com.muhammet.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
@Getter
@AllArgsConstructor
public enum ErrorType {


    BADREQUEST(4002, "Girilen parametereler hatalıdır.",BAD_REQUEST),
    SIFREHATASI(4003, "Girişlen şifreler bir biri ile uyuşumamaktadır.",BAD_REQUEST),
    EMAIL_SIFRE_HATASI(4004, "Kullanıcı adı ya da şifre hatalısır", BAD_REQUEST),
    EXISTED_KATEGORI(4005, "Bu kategori zaten kayıtlıdır.", BAD_REQUEST),
    INTERNAL_SERVER(5000, "Sunucuda beklenmeyen bir hata",INTERNAL_SERVER_ERROR);

    int code;
    String message;
    HttpStatus httpStatus;

}
