package com.muhammet.controller;

import com.muhammet.dto.response.BaseResponse;
import com.muhammet.service.SepetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.muhammet.config.RestApis.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(SEPET)
@CrossOrigin("*")
public class SepetController {
    private final SepetService sepetService;
    /**
     * Her kullanıcı için 1 adet olan bir olgu.
     * - bunun içerisine kullanıcı ekleme yapabiliyor
     * - ürün çıkartabiliyor
     * - sepeti boşaltabiliyor.
     * - sepetteki ürünün sayısını arttır / azalt
     */

    // 1- Sepete Ürün Ekleme

    /**
     * Ürün bilgisi, Kullanıcı bilgisi, adet bilgisi
     * sepet e ürün ekleyebilmek için sepet e sahip olmak gerekli, bu nedenle sepet var mı bak yok ise oluştur
     * sepet ürün listesine ürünleri ekle
     *
     */
    public ResponseEntity<BaseResponse<Boolean>> addSepet(){
        return null;
    }

    // 2- sepetten ürünü sil
    /**
     * ürün bilgisi, kullanıcı bilgi
     * sepette o ürün varmı?
     */

    // 3- sepeti boşalt
    /**
     * kullanıcı bilgisi yeterli
     */

    // 4- sepette ki ürünün saısını bir arttır ya da azalt
    /**
     * ürün bilgisi, kullanıcı bilgisi, arttırma mı azaltma mı bilgisinie ihtiyaç var
     */

}
