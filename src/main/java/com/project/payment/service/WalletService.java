package com.project.payment.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface WalletService {
    public String getWalletBalance(Long id);
}
