package com.muhammet.controller;

import com.muhammet.config.JwtManager;
import com.muhammet.dto.request.AddSepetRequestDto;
import com.muhammet.dto.request.ArttirAzaltRequestDto;
import com.muhammet.dto.request.RemoveAllSepetRequestDto;
import com.muhammet.dto.request.RemoveInSepetRequestDto;
import com.muhammet.dto.response.BaseResponse;
import com.muhammet.dto.response.SepetUrunResponseDto;
import com.muhammet.service.SepetService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.muhammet.config.RestApis.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(SEPET)
@CrossOrigin("*")
@SecurityRequirement(name = "bearerAuth")
public class SepetController {
    private final SepetService sepetService;
    private final JwtManager jwtManager;
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
        Optional<Long> optionalUserId = jwtManager.validateToken(dto.token());
        sepetService.AddSepet(dto, optionalUserId.get());
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
