package com.project.payment.serviceImpl;

import com.project.payment.dto.TransactionDetails;
import com.project.payment.dto.Wallet;
import com.project.payment.model.PaymentMode;
import com.project.payment.model.PaymentRequest;
import com.project.payment.model.PaymentResponse;
import com.project.payment.repository.PaymentRepository;
import com.project.payment.repository.WalletRepository;
import com.project.payment.service.PaymentService;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    WalletRepository walletRepository;

    @Override
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(Long orderId) {
        TransactionDetails transactionDetails = paymentRepository.findById(orderId).get();
        PaymentResponse paymentResponse = PaymentResponse.builder().paymentId(transactionDetails.getId())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .paymentDate(transactionDetails.getPaymentDate())
                .amount(transactionDetails.getAmount())
                .status(transactionDetails.getPaymentStatus())
                .build();
        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> makePayment(PaymentRequest paymentRequest) {
        Wallet wallet = walletRepository.findById(paymentRequest.getOrderId()).get();
        BigDecimal curBalance = wallet.getBalance();
        int compare = curBalance.compareTo(paymentRequest.getAmount());
        if(compare == -1){
            return new ResponseEntity<>("Insufficient Balance in Wallet!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            BigDecimal finalBal = curBalance.subtract(paymentRequest.getAmount());
            wallet.setBalance(finalBal);
            walletRepository.save(wallet);
            TransactionDetails transactionDetails = TransactionDetails.builder().paymentStatus("Success")
                    .amount(paymentRequest.getAmount())
                    .paymentMode(paymentRequest.getPaymentMode().toString())
                    .paymentDate(Instant.now())
                    .referenceNo(paymentRequest.getReferenceNo())
                    .orderId(paymentRequest.getOrderId())
                    .build();
            transactionDetails = paymentRepository.save(transactionDetails);
            return new ResponseEntity<>("Payment Successful with payment id: "+transactionDetails.getId(),HttpStatus.OK);
        }

    }
}
