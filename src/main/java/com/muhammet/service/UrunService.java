package com.muhammet.service;

import com.muhammet.dto.request.AddUrunRequestDto;
import com.muhammet.entity.Urun;
import com.muhammet.exception.ETicaretException;
import com.muhammet.exception.ErrorType;
import com.muhammet.repository.UrunRepository;
import com.muhammet.view.VwUrunList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrunService {
    private final UrunRepository repository;

    public void addUrun(AddUrunRequestDto dto) {
        repository.save(Urun.builder()
                        .ad(dto.ad())
                        .birim(dto.birim())
                        .fiyat(dto.fiyat())
                        .kategoriAdi(dto.kategoriAdi())
                        .kategoriId(dto.kategoriId())
                        .kdv(dto.kdv())
                        .resim(dto.resim())
                        .stok(dto.stok())
                        .uyariMiktari(dto.uyariMiktari())
                .build());
    }

    /**
     * DİKKAT!!!!!!
     * Respoistory den ürün listesini yani entity yi direkt dönmek doğru değildir.
     * @return
     */
    public List<Urun> getAll() {
        return repository.findAll();
    }

    public List<VwUrunList> getVwUrunList() {
        return repository.getAllUrunList();
    }

    public List<Urun> findAllByAd(String urunAdi) {
        return repository.findAllByAdContaining(urunAdi);
    }

    /**
     * DİKKAT!!!!
     * silme işlemleri gerekli olmadıkça DB den veriyi tamamen silmek şeklinde olmamalıdır.
     * durumunu silinmişe getirip gizleme şeklinde olması daha doğrudur. DB den veri silinmez
     * @param urunId
     */
    public void deleteUrun(Long urunId) {
        repository.deleteById(urunId);
    }

    public void stokAzaltma(Long urunId, Integer miktar){
        Optional<Urun> urunOptional = repository.findById(urunId);
        // eğer ürün yok ise,
        if(urunOptional.isEmpty()) throw new ETicaretException(ErrorType.URUN_NOTFOUND);
        Urun urun = urunOptional.get();
        urun.setStok(urun.getStok()-miktar);
        repository.save(urun); // update
    }


    public Optional<Urun> findOptionalById(Long id) {
        return repository.findById(id);
    }
}
