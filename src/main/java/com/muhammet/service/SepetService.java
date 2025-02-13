package com.muhammet.service;

import com.muhammet.dto.request.AddSepetRequestDto;
import com.muhammet.dto.request.ArttirAzaltRequestDto;
import com.muhammet.dto.request.RemoveAllSepetRequestDto;
import com.muhammet.dto.request.RemoveInSepetRequestDto;
import com.muhammet.dto.response.SepetUrunResponseDto;
import com.muhammet.entity.Sepet;
import com.muhammet.entity.SepetUrunleri;
import com.muhammet.entity.Urun;
import com.muhammet.exception.ETicaretException;
import com.muhammet.exception.ErrorType;
import com.muhammet.repository.SepetRepository;
import com.muhammet.repository.SepetUrunleriRepository;
import com.muhammet.utility.enums.SepetDegisim;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    public void ArttirAzalt(ArttirAzaltRequestDto dto) {
        Long sepetId = getSepetIdFromUserId(dto.kullaniciId());
        Optional<SepetUrunleri> sepetUrunleri = sepetUrunleriRepository.findOptionalBySepetIdAndUrunId(sepetId,dto.urunId());
        if(sepetUrunleri.isEmpty()) throw new ETicaretException(ErrorType.SEPET_URUN_NOTFOUND);
        SepetUrunleri urun = sepetUrunleri.get();
        if(dto.degisim().equals(SepetDegisim.ARTTIR)){ // sayıyı arttır
            urun.setAdet(urun.getAdet()+1);
            sepetUrunleriRepository.save(urun);
        }else { // azaltma işlemi
            // DİKKAT!! ürün adedi 2 ve üzeinde ise arttırma yap, altında ise sil
            if(urun.getAdet()>1){
                urun.setAdet(urun.getAdet()-1);
                sepetUrunleriRepository.save(urun);
            }else {
                sepetUrunleriRepository.delete(urun);
            }
        }
    }

    public List<SepetUrunResponseDto> getAllSepet(Long userId) {
        List<SepetUrunResponseDto> result = new ArrayList<>();
        Long sepetId = getSepetIdFromUserId(userId);
        List<SepetUrunleri> sepetList = sepetUrunleriRepository.findAllBySepetId(sepetId);
        sepetList.forEach(s->{
            Optional<Urun> urun = urunService.findOptionalById(s.getUrunId());
            if(urun.isPresent()){
                SepetUrunResponseDto dto = new SepetUrunResponseDto(
                        s.getId(),
                        s.getUrunId(),
                        urun.get().getAd(),
                        urun.get().getResim(),
                        s.getAdet(),
                        s.getFiyat(),
                        s.getToplamFiyat()
                );
                result.add(dto);
            }
        });
        return result;
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
