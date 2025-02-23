package com.muhammet.service;

import com.muhammet.entity.Urun;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SatisService implements ISatisService{
    @Override
    public boolean sepetAkatar(List<Urun> urunList) {
        return false;
    }
}
