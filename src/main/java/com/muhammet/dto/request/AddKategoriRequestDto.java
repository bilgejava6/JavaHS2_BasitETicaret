package com.muhammet.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddKategoriRequestDto(
        @NotNull
        @Min(0)
        Long parentId,
        @NotNull
        @NotEmpty
        @Size(min = 3, max = 50)
        String ad
) {
}
