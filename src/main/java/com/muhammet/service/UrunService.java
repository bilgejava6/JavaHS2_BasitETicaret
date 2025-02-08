package com.muhammet.service;

import com.muhammet.repository.UrunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrunService {
    private final UrunRepository repository;
}
