package com.muhammet.dto.request;

import com.muhammet.entity.enums.Birim;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AddUrunRequestDto(
        @NotNull
        Long kategoriId,
        @NotNull
        String kategoriAdi,
        @NotNull
        String ad,
        @NotNull
        String resim,
        @NotNull
        BigDecimal fiyat,
        int stok,
        @NotNull
        Integer kdv,
        Birim birim,
        @NotNull
        Integer uyariMiktari
) {
}
