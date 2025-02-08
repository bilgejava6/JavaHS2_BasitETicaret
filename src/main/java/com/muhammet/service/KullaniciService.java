package com.muhammet.service;

import com.muhammet.dto.request.DoLoginRequestDto;
import com.muhammet.dto.request.DoRegisterRequestDto;
import com.muhammet.entity.Kullanici;
import com.muhammet.repository.KullaniciRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KullaniciService {
    private final KullaniciRepository repository;

    public void doRegister(DoRegisterRequestDto dto) {
        repository.save(Kullanici.builder()
                        .ad(dto.ad())
                        .telefon(dto.telefon())
                        .email(dto.email())
                        .sifre(dto.password())
                .build());
    }

    public Optional<Kullanici> findByEmailPassword(DoLoginRequestDto dto) {
        return repository.findOptionalByEmailAndSifre(dto.email(), dto.password());
    }
}
