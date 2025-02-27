package com.muhammet.dto.request;

public record AddRoleRequestDto(
        String roleName,
        Long userId
) {
}
