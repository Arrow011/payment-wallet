package com.project.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private long orderId;
    private BigDecimal amount;
    private String referenceNo;
    private PaymentMode paymentMode;
}
