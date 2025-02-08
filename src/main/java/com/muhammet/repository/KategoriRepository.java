package com.muhammet.repository;

import com.muhammet.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KategoriRepository  extends JpaRepository<Kategori,Long> {
    /**
     * parentId si ve Ad ı sorguladığımda böyle bir kayıt var mı?
     *
     * select count(*)>0 from tblkategori where ad=? and parentId=?
     */
    Boolean existsByAdAndParentId(String ad, Long parentId);

    List<Kategori> findAllByParentId(Long parentId);
}
