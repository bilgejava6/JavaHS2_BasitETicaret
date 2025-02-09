package com.muhammet.view;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class VwUrunList {
    Long id;
    String ad;
    String kategoriAdi;
    String resim;
    BigDecimal fiyat;
}
