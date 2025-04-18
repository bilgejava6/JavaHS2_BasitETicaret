package com.muhammet.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddSepetRequestDto(
        @NotNull
        String token,
        @NotNull
        @Min(0)
        Long urunId
) {
}
