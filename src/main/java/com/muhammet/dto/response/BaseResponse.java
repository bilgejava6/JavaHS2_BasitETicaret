package com.muhammet.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BaseResponse<T> {
    String message;
    Integer code;
    T data;
}
