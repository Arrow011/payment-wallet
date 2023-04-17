package com.project.payment;

import com.project.payment.dto.Wallet;
import com.project.payment.repository.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(WalletRepository walletRepository) {
		return args ->{
			Wallet wallet = new Wallet();
			wallet.setId(1L);
			wallet.setBalance(BigDecimal.valueOf(10000));
			walletRepository.save(wallet);
		};
	}
}
