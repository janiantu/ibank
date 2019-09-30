package com.ibank.payment.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ibank.payment.dto.PaymentDto;
import com.ibank.payment.handlers.PaymentException;
import com.querydsl.core.types.Predicate;

public interface PaymentService {
	
	public PaymentDto createPayment(PaymentDto paymentDto);
	
	public PaymentDto getPaymentById(Long paymentId);

	public List<PaymentDto> getAllPayments(Predicate predicate, Pageable pageable);
	
	public PaymentDto cancelPaymentById(Long paymentId) throws PaymentException;

}
