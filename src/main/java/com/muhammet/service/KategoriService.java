package com.muhammet.service;

import com.muhammet.repository.KategoriRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KategoriService {
    private final KategoriRepository repository;
}
