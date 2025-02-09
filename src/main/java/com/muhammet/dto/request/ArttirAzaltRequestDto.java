package com.muhammet.dto.request;

import com.muhammet.utility.enums.SepetDegisim;

public record ArttirAzaltRequestDto(
        Long kullaniciId,
        Long urunId,
        SepetDegisim degisim
) {
}
