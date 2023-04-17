package com.project.payment.serviceImpl;

import com.project.payment.dto.Wallet;
import com.project.payment.repository.WalletRepository;
import com.project.payment.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private  WalletRepository walletRepository;
    @Override
    public String getWalletBalance(Long id) {
        Wallet wallet = walletRepository.findById(id).get();
        return wallet.getBalance().toString();
    }

}
