package com.muhammet.service;

import com.muhammet.dto.request.AddSepetRequestDto;
import com.muhammet.dto.request.RemoveAllSepetRequestDto;
import com.muhammet.dto.request.RemoveInSepetRequestDto;
import com.muhammet.entity.Sepet;
import com.muhammet.entity.SepetUrunleri;
import com.muhammet.entity.Urun;
import com.muhammet.exception.ETicaretException;
import com.muhammet.exception.ErrorType;
import com.muhammet.repository.SepetRepository;
import com.muhammet.repository.SepetUrunleriRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SepetService {
    private final SepetRepository sepetRepository;
    private final SepetUrunleriRepository sepetUrunleriRepository;
    private final UrunService urunService;

    /**
     * ** kullanıcıId için,
     *    - bu id ile bir kullanıcı var mı?
     *    - Bu kullanıcıya ait bir sepet var mı?
     * ** urunId için,
     *    - bu id ile bir ürün var mıdır?
     *    - bu üründen satılacak kadar stok var mıdır?
     *    - eğer bu ürün zaten sepet e eklenmiş ise tekrar ekleme yapamazsın, sayısını arttırıresın
     */
    public void AddSepet(AddSepetRequestDto dto) {
        // 1. bu kişiye ait bir sepet var mıdır?
        Sepet sepet;
        Optional<Sepet> sepetOptional = sepetRepository.findOptionalByUserId(dto.kullaniciId());
        if(sepetOptional.isEmpty()){ // böyle bir sepet yok ise
            sepet = Sepet.builder().userId(dto.kullaniciId()).build();
            sepetRepository.save(sepet);
        }else {
            sepet = sepetOptional.get();
        }
        // buradan itibaren artık ürünü ilgili sepet için sepet listesine ekliyoruz.
        Optional<Urun> urunOptional = urunService.findOptionalById(dto.urunId());
        if(urunOptional.isEmpty()) throw new ETicaretException(ErrorType.URUN_NOTFOUND);
        Urun urun = urunOptional.get();
        SepetUrunleri sepetUrunleri = SepetUrunleri.builder()
                .sepetId(sepet.getId())
                .urunId(dto.urunId())
                .adet(1)
                .fiyat(urun.getFiyat())
                .toplamFiyat(urun.getFiyat())
                .build();
        sepetUrunleriRepository.save(sepetUrunleri);
    }

    public void removeUrunInSepet(RemoveInSepetRequestDto dto) {
        Long sepetId = getSepetIdFromUserId(dto.kullaniciId());
        Optional<SepetUrunleri> sepetUrunleri = sepetUrunleriRepository.findOptionalBySepetIdAndUrunId(sepetId, dto.urunId());
        if (sepetUrunleri.isEmpty()) throw new ETicaretException(ErrorType.SEPET_URUN_NOTFOUND);
        sepetUrunleriRepository.delete(sepetUrunleri.get());
    }


    public void removeAllSepet(RemoveAllSepetRequestDto dto) {
        Long sepetId = getSepetIdFromUserId(dto.kullaniciId());
        List<SepetUrunleri> sepetListesi = sepetUrunleriRepository.findAllBySepetId(sepetId);
        sepetUrunleriRepository.deleteAll(sepetListesi);
    }


    /**
     * Kullanıcı Id si verilerek sepet id bilgisi alınmaktadır.
     * @param userId
     * @return
     */
    private Long getSepetIdFromUserId(Long userId) {
        Optional<Sepet> sepetOptional = sepetRepository.findOptionalByUserId(userId);
        if(sepetOptional.isEmpty()) throw new ETicaretException(ErrorType.SEPET_NOTFOUND);
        Long sepetId = sepetOptional.get().getId();
        return sepetId;
    }

}
