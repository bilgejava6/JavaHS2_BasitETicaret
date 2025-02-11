package com.muhammet.controller;

import com.muhammet.dto.request.AddUrunRequestDto;
import com.muhammet.dto.response.BaseResponse;
import com.muhammet.entity.Urun;
import com.muhammet.service.UrunService;
import com.muhammet.view.VwUrunList;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.muhammet.config.RestApis.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(URUN)
@CrossOrigin("*")
public class UrunController {
    // ürün ekleme  -> muhammet hoca  / muhm
    // ürün listeleme
    // ürün adına göre arama
    private final UrunService urunService;

    /**
     * Application.yml içerisindeki bilgileri okumak
     *
     * bu işlem için @Value kullanılır.
     *
     */
    @Value("${benim-uygulamam.urun-baslik}")
    private String urunBaslik;

    @GetMapping("/yml-okuma")
    public ResponseEntity<Void> ymlOkuma(){
        System.out.println("Ürün adı.......: "+ urunBaslik);
        return ResponseEntity.ok().build();
    }

    @PostMapping(ADD_URUN)
    public ResponseEntity<BaseResponse<Boolean>> addUrun(@RequestBody @Valid AddUrunRequestDto dto){
        urunService.addUrun(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .message("Ürün başarıyla eklendi.")
                        .code(200)
                        .data(true)
                .build());
    }

    @GetMapping(GET_ALL_URUN)
    public ResponseEntity<BaseResponse<List<VwUrunList>>> getAllUrun(){
        return ResponseEntity.ok(BaseResponse.<List<VwUrunList>>builder()
                        .code(200)
                        .message("ÜRün Listesi getirildi.")
                        .data(urunService.getVwUrunList())
                .build());
    }


    @GetMapping(FIND_BY_URUN_ADI+"/{urunAdi}")
    public ResponseEntity<BaseResponse<List<Urun>>> findByAd(@PathVariable String urunAdi){
        return ResponseEntity.ok(BaseResponse.<List<Urun>>builder()
                        .code(200)
                        .message("Aranılan ürünler getirildi.")
                        .data(urunService.findAllByAd(urunAdi))
                .build());
    }

    @DeleteMapping(DELETE_URUN+"/{urunId}")
    public ResponseEntity<BaseResponse<Boolean>> deleteUrunById(@PathVariable Long urunId){
        urunService.deleteUrun(urunId);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .message("Ürün başarı ile silindi.")
                        .data(true)
                .build());
    }


}
