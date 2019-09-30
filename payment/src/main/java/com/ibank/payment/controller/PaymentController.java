package com.ibank.payment.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import com.ibank.payment.dto.PaymentDto;
import com.ibank.payment.handlers.PaymentException;
import com.ibank.payment.json.View;
import com.ibank.payment.model.Payment;
import com.ibank.payment.service.interfaces.PaymentService;
import com.querydsl.core.types.Predicate;

@RestController
public class PaymentController {
	
    @Autowired
    private PaymentService paymentService;
	
    @GetMapping("/payment/{paymentId}")
    @JsonView(View.PaymentResponseCancellationFee.class)
	public ResponseEntity<PaymentDto> getPaymentById(@PathVariable("paymentId") Long paymentId) {

       PaymentDto paymentDto = paymentService.getPaymentById(paymentId);
            	
       return new ResponseEntity<PaymentDto>(paymentDto, HttpStatus.OK);
    
    }
    
    @GetMapping("/payments")
    @JsonView(View.PaymentResponse.class)
	public ResponseEntity<List<PaymentDto>> getPayments(@QuerydslPredicate(root = Payment.class) Predicate predicate, Pageable pageable) {
       
    	 List<PaymentDto> paymentList = paymentService.getAllPayments(predicate, pageable);
         
    	 return new ResponseEntity<List<PaymentDto>>(paymentList, HttpStatus.OK);
  
    }
    
    @PostMapping("/payment")
    @JsonView(View.PaymentResponse.class)
    public ResponseEntity<PaymentDto> createPayment(@Valid @RequestBody PaymentDto paymentDto) {
    	        
    	paymentDto = paymentService.createPayment(paymentDto);
    
        return new ResponseEntity<PaymentDto>(paymentDto, HttpStatus.CREATED);
    }
    
    @PutMapping("/payment/{paymentId}")
    @JsonView(View.PaymentResponse.class)
    public ResponseEntity<PaymentDto> cancelPayment(@PathVariable("paymentId") Long paymentId) throws PaymentException {
    	        
    	PaymentDto paymentDto = paymentService.cancelPaymentById(paymentId);
    
        return new ResponseEntity<PaymentDto>(paymentDto, HttpStatus.OK);
    }

}
