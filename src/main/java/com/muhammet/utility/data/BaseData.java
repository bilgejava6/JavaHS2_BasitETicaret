package com.muhammet.utility.data;

import com.muhammet.entity.Kullanici;
import com.muhammet.repository.KullaniciRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BaseData {
    private final KullaniciRepository kullaniciRepository;

    @PostConstruct
    public void init(){
        kullaniciEkle();
    }

    private void kullaniciEkle(){
        Kullanici kullanici = Kullanici.builder()
                .ad("demet")
                .email("demet@gmail.com")
                .sifre("12345678")
                .telefon("0 666 999 8877")
                .adres("İzmir")
                .avatar("https://resim.png")
                .build();
        kullaniciRepository.save(kullanici);
    }

    private void kategoriEkle(){}

    private void urunEkle(){}
}
