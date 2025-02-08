package com.muhammet.service;

import com.muhammet.dto.request.AddKategoriRequestDto;
import com.muhammet.entity.Kategori;
import com.muhammet.exception.ETicaretException;
import com.muhammet.exception.ErrorType;
import com.muhammet.repository.KategoriRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KategoriService {
    private final KategoriRepository repository;


    public Kategori addKategori(AddKategoriRequestDto dto) {
        /**
         * Eğer bir kategori aynı isimde tekrar edecek ise, başka bir ana kategorinin altında
         * yani parentId si farklı olarak kayıt edilmeli.
         */
        Boolean isExists = repository.existsByAdAndParentId(dto.ad(), dto.parentId());
        if(isExists) throw new ETicaretException(ErrorType.EXISTED_KATEGORI);
        Kategori kategori = Kategori.builder()
                .ad(dto.ad())
                .parentId(dto.parentId())
                .build();
        repository.save(kategori);
        return kategori;
    }

    public List<Kategori> getMainKategori() {
//        List<Kategori> result = repository.findAllByParentId(0L);
//        return result;
        return repository.findAllByParentId(0L);
    }

    public List<Kategori> getSubKategori(Long parentId) {
        List<Kategori> result = repository.findAllByParentId(parentId);
        return result;
    }
}
