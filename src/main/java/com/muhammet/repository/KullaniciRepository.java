package com.muhammet.repository;

import com.muhammet.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KullaniciRepository  extends JpaRepository<Kullanici,Long> {
    Optional<Kullanici> findOptionalByEmailAndSifre(String email, String password);
}
