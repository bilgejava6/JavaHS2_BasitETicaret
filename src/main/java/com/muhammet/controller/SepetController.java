package com.muhammet.controller;

import com.muhammet.dto.request.AddSepetRequestDto;
import com.muhammet.dto.request.ArttirAzaltRequestDto;
import com.muhammet.dto.request.RemoveAllSepetRequestDto;
import com.muhammet.dto.request.RemoveInSepetRequestDto;
import com.muhammet.dto.response.BaseResponse;
import com.muhammet.dto.response.SepetUrunResponseDto;
import com.muhammet.service.SepetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping(ADD_TO_SEPET)
    public ResponseEntity<BaseResponse<Boolean>> addSepet(@RequestBody @Valid AddSepetRequestDto dto){
        sepetService.AddSepet(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .message("Ürün sepete eklendi")
                        .data(true)
                .build());
    }

    // 2- sepetten ürünü sil
    /**
     * ürün bilgisi, kullanıcı bilgi
     * sepette o ürün varmı?
     */
    @DeleteMapping(REMOVE_IN_SEPET)
    public ResponseEntity<BaseResponse<Boolean>> removeInSepet(@RequestBody @Valid RemoveInSepetRequestDto dto){
        sepetService.removeUrunInSepet(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .message("Ürün sespetten çıkartıldı")
                        .data(true)
                .build());
    }
    // 3- sepeti boşalt
    /**
     * kullanıcı bilgisi yeterli
     */
    @DeleteMapping(CLEAR_SEPET)
    public ResponseEntity<BaseResponse<Boolean>> removeAllSepet(@RequestBody @Valid RemoveAllSepetRequestDto dto){
        sepetService.removeAllSepet(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .message("Tüm Ürünler sespetten çıkartıldı")
                .data(true)
                .build());
    }
    // 4- sepette ki ürünün saısını bir arttır ya da azalt
    /**
     * ürün bilgisi, kullanıcı bilgisi, arttırma mı azaltma mı bilgisinie ihtiyaç var
     */
    @PostMapping(UP_DOWN_SEPET)
    public ResponseEntity<BaseResponse<Boolean>> arttirAzalt(@RequestBody @Valid ArttirAzaltRequestDto dto){
        sepetService.ArttirAzalt(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .message("Ürün sepet değişikliği yapıldı")
                .data(true)
                .build());
    }

    @GetMapping("/get-all-sepet/{userId}")
    public ResponseEntity<BaseResponse<List<SepetUrunResponseDto>>> getAllSepet(@PathVariable Long userId){
        return ResponseEntity.ok(BaseResponse.<List<SepetUrunResponseDto>>builder()
                        .code(200)
                        .message("Sepet Getirildi.")
                        .data(sepetService.getAllSepet(userId))
                .build());
    }

}
