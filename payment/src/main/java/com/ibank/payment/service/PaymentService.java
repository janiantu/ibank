package com.ibank.payment.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ibank.payment.dto.PaymentDto;
import com.ibank.payment.dto.PaymentStatus;
import com.ibank.payment.dto.PaymentType;
import com.ibank.payment.handlers.PaymentException;
import com.ibank.payment.handlers.PaymentNotFoundException;
import com.ibank.payment.model.Payment;
import com.ibank.payment.repository.PaymentRepository;
import com.querydsl.core.types.Predicate;

@Service("PaymentService")
public class PaymentService implements com.ibank.payment.service.interfaces.PaymentService {
	
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    
    public PaymentService(ModelMapper modelMapper) { 
    	@SuppressWarnings("rawtypes")
		Converter<String, Enum> statusConverter =
                ctx -> ctx.getSource() == null ? null : PaymentStatus.fromValue(ctx.getSource());
        modelMapper.typeMap(Payment.class, PaymentDto.class)
                .addMappings(mapper -> mapper.using(statusConverter).map(Payment::getStatus, PaymentDto::setStatus));  
        this.modelMapper = modelMapper;
     }

	@Override
	public PaymentDto createPayment(PaymentDto paymentDto) {
		
		paymentDto.setStatus(PaymentStatus.IN_PROGRESS);
		paymentDto.setTransactionDate(LocalDateTime.now());
		Payment payment = modelMapper.map(paymentDto, Payment.class);
    	payment = paymentRepository.save(payment);
    	paymentDto = modelMapper.map(payment, PaymentDto.class);
    	
		return paymentDto;
		
	}
	
	@Override
	public PaymentDto getPaymentById(Long paymentId) {
		
		Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException(paymentId));
	    PaymentDto paymentDto = modelMapper.map(payment, PaymentDto.class);
	    
	    return paymentDto;
	}

	@Override
	public List<PaymentDto> getAllPayments(Predicate predicate, Pageable pageable) {
		
        Page<Payment> payments = paymentRepository.findAll(predicate, pageable);
        
        List<PaymentDto> paymentList = 
        		payments
        		.getContent()
                .stream()
                .map(payment -> modelMapper.map(payment, PaymentDto.class))
                .collect(Collectors.toList());
		
		return paymentList;
	}

	@Override
	public PaymentDto cancelPaymentById(Long paymentId) throws PaymentException {
		
		Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException(paymentId));
		LocalDateTime transactionDate = payment.getTransactionDate();
		LocalDateTime currentDate = LocalDate.now().atTime(0, 0, 0);
		
		if (currentDate.isBefore(transactionDate) && payment.getStatus().equals(PaymentStatus.IN_PROGRESS.getValue()) ) {
			payment.setStatus(PaymentStatus.CANCELLED.getValue());
			BigDecimal cancellationFee = calculateCancellationFee(payment);
			payment.setCancellationFee(cancellationFee.doubleValue());
			paymentRepository.save(payment);
		} else {
			throw new PaymentException("Payment cancellation is not possible.");
		}
		
	    PaymentDto paymentDto = modelMapper.map(payment, PaymentDto.class);
	    
	    return paymentDto;
	}

	private BigDecimal calculateCancellationFee(Payment payment) {
		
		int hours = LocalDateTime.now().getHour() -  payment.getTransactionDate().getHour();
		BigDecimal cancellationFee = new BigDecimal(0.00).setScale(2);
		
		switch (PaymentType.valueOf(payment.getType())) {
			case TYPE1 : cancellationFee = new BigDecimal(hours*0.05).setScale(2); break;
			case TYPE2 : cancellationFee = new BigDecimal(hours*0.10).setScale(2); break;
			case TYPE3 : cancellationFee = new BigDecimal(hours*0.15).setScale(2); break;
		}
		
		return cancellationFee;
	}



}
