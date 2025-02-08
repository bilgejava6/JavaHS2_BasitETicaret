package com.muhammet.service;

import com.muhammet.repository.SepetRepository;
import com.muhammet.repository.SepetUrunleriRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SepetService {
    private final SepetRepository sepetRepository;
    private final SepetUrunleriRepository sepetUrunleriRepository;
}
