package com.muhammet.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RemoveInSepetRequestDto(
        @NotNull
        @Min(0)
        Long kullaniciId,
        @NotNull
        @Min(0)
        Long urunId
) {

}
