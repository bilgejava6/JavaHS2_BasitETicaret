package com.muhammet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tblkullanici")
public class Kullanici {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false,length = 80)
    String ad;
    String adres;
    @Column(nullable = false,length = 20)
    String telefon;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false, length = 128)
    String sifre;
    String avatar;
}
