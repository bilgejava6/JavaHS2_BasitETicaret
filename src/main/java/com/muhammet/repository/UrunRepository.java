package com.muhammet.repository;

import com.muhammet.entity.Urun;
import com.muhammet.view.VwUrunList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UrunRepository extends JpaRepository<Urun,Long> {

    @Query("select new com.muhammet.view.VwUrunList(u.id,u.ad,u.kategoriAdi,u.resim,u.fiyat) from Urun u")
    List<VwUrunList> getAllUrunList();

    List<Urun> findAllByAdContaining(String urunAdi);
}
