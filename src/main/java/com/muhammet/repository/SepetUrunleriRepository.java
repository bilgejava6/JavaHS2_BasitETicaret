package com.muhammet.repository;

import com.muhammet.entity.SepetUrunleri;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SepetUrunleriRepository  extends JpaRepository<SepetUrunleri,Long> {
    Optional<SepetUrunleri> findOptionalBySepetIdAndUrunId(Long sepetId, Long urunId);

    List<SepetUrunleri> findAllBySepetId(Long sepetId);
}
