package com.muhammet.controller;

import com.muhammet.dto.request.AddKategoriRequestDto;
import com.muhammet.dto.response.BaseResponse;
import com.muhammet.entity.Kategori;
import com.muhammet.service.KategoriService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.muhammet.config.RestApis.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(KATEGORI)
@CrossOrigin("*")
public class KategoriController {
    private final KategoriService kategoriService;

    @PostMapping(ADD_KATEGORI)
    public ResponseEntity<BaseResponse<Kategori>> addKategori(@RequestBody @Valid AddKategoriRequestDto dto){
        return ResponseEntity.ok(BaseResponse.<Kategori>builder()
                        .message("Kayıt başarılı")
                        .code(200)
                        .data(kategoriService.addKategori(dto))
                .build());
    }

    @GetMapping(GET_MAIN_KATEGORI)
    public ResponseEntity<BaseResponse<List<Kategori>>> getMainKategori(){
        return ResponseEntity.ok(BaseResponse.<List<Kategori>>builder()
                        .message("Ana Kategoriler getirildi.")
                        .code(200)
                        .data(kategoriService.getMainKategori())
                .build());
    }

    @GetMapping(GET_SUB_KATEGORI+"/{parentId}")
    public ResponseEntity<BaseResponse<List<Kategori>>> getSubKategori(@PathVariable Long parentId){
        return ResponseEntity.ok(BaseResponse.<List<Kategori>>builder()
                        .data(kategoriService.getSubKategori(parentId))
                        .code(200)
                        .message("Alt kategoriler getirildi.")
                .build());
    }
}
