package com.project.payment.repository;

import com.project.payment.dto.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<TransactionDetails, Long> {
}
