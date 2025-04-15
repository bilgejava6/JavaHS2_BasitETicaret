package com.muhammet.controller;

import com.muhammet.config.JwtManager;
import com.muhammet.dto.request.AddRoleRequestDto;
import com.muhammet.dto.request.DoLoginRequestDto;
import com.muhammet.dto.request.DoRegisterRequestDto;
import com.muhammet.dto.response.BaseResponse;
import com.muhammet.entity.Kullanici;
import com.muhammet.exception.ETicaretException;
import com.muhammet.exception.ErrorType;
import com.muhammet.service.KullaniciService;
import com.muhammet.service.UserRoleService;
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
    private final UserRoleService userRoleService;
    private final JwtManager jwtManager;
    @PostMapping(DOREGISTER)
    public ResponseEntity<BaseResponse<Boolean>> doRegister(@RequestBody @Valid DoRegisterRequestDto dto){
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
    public ResponseEntity<BaseResponse<String>> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
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
    @PostMapping("/add-role")
    public ResponseEntity<BaseResponse<Boolean>> addRole(@RequestBody AddRoleRequestDto dto){
        userRoleService.addRole(dto.roleName(), dto.userId());
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .code(200)
                        .message("Ok")
                        .data(true)
                .build());
    }
    @GetMapping("/get-user-name/{token}")
    public ResponseEntity<BaseResponse<String>> getUserName(@PathVariable String token){
      Optional<Long> optionalUserId = jwtManager.validateToken(token);
      if(optionalUserId.isEmpty()) throw new ETicaretException(ErrorType.INVALID_TOKEN);
      Optional<Kullanici> optionalKullanici =  kullaniciService.findByUserId(optionalUserId.get());
      if(optionalKullanici.isEmpty()) throw new ETicaretException(ErrorType.USER_NOTFOUND);
      return  ResponseEntity.ok(BaseResponse.<String>builder()
                      .code(200)
                      .data(optionalKullanici.get().getAd())
                      .message("Kullanıcı adı başarı ile getirildi.")
              .build());
    }
}
