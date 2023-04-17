package com.project.payment.service;

import com.project.payment.model.PaymentRequest;
import com.project.payment.model.PaymentResponse;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(Long orderId);
    public ResponseEntity<String> makePayment(PaymentRequest paymentRequest);
}
