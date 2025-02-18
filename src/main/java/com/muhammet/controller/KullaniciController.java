package com.muhammet.controller;

import com.muhammet.config.JwtManager;
import com.muhammet.dto.request.DoLoginRequestDto;
import com.muhammet.dto.request.DoRegisterRequestDto;
import com.muhammet.dto.response.BaseResponse;
import com.muhammet.entity.Kullanici;
import com.muhammet.exception.ETicaretException;
import com.muhammet.exception.ErrorType;
import com.muhammet.service.KullaniciService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.muhammet.config.RestApis.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(KULLANICI)
@CrossOrigin("*")
public class KullaniciController {
    private final KullaniciService kullaniciService;
    private final JwtManager jwtManager;
    @PostMapping(DOREGISTER)
    private ResponseEntity<BaseResponse<Boolean>> doRegister(@RequestBody @Valid DoRegisterRequestDto dto){
        // Eğer kullanıcının şifreleri eşleşmiyor ise kayıt yapılmaz direkt hata dönülür.
        if(!dto.password().equals(dto.rePassword()))
            throw new ETicaretException(ErrorType.SIFREHATASI);
        kullaniciService.doRegister(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .data(true)
                        .message("Üyelik başarılı şekilde oluşturuldu.")
                .build());
    }
    @PostMapping(LOGIN)
    private ResponseEntity<BaseResponse<String>> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        // email ve şifre yi vererek kullanıcını var olup olmadığını sorguluyorum.
        Optional<Kullanici> optionalKullanici = kullaniciService.findByEmailPassword(dto);
        if(optionalKullanici.isEmpty()) // böyle bir kullanıcı yok
            throw  new ETicaretException(ErrorType.EMAIL_SIFRE_HATASI);
        return ResponseEntity.ok(BaseResponse.<String>builder()
                        .code(200)
                        .data(jwtManager.createToken(optionalKullanici.get().getId()))
                        .message("Başralı şekilde giriş yapıldı.")
                .build());
    }
}
