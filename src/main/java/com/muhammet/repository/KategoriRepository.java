package com.muhammet.repository;

import com.muhammet.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategoriRepository  extends JpaRepository<Kategori,Long> {
}
