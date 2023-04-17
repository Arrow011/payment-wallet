package com.project.payment.controller;

import com.project.payment.model.PaymentRequest;
import com.project.payment.model.PaymentResponse;
import com.project.payment.service.PaymentService;
import com.project.payment.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    WalletService walletService;

    @Autowired
    PaymentService paymentService;

    @GetMapping("/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable("orderId") Long id){
        return paymentService.getPaymentDetailsByOrderId(id);
    }

    @PostMapping("/do")
    public ResponseEntity<String> makePayment(@RequestBody PaymentRequest paymentRequest){
        return paymentService.makePayment(paymentRequest);
    }

    @GetMapping("/walletBalance/{id}")
    public String getWalletBalance(@PathVariable("id") Long id){
        return walletService.getWalletBalance(id);
    }
}
