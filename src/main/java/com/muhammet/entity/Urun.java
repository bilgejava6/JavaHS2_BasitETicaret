package com.muhammet.entity;

import com.muhammet.entity.enums.Birim;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tblurun")
public class Urun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long kategoriId;
    String kategoriAdi;
    String ad;
    String resim;
    BigDecimal fiyat;
    int stok;
    Integer kdv;
    Birim birim;
    Integer uyariMiktari;

}
