package com.ibank.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ibank.payment.annotations.RequiredIf;
import com.ibank.payment.json.View;
import com.ibank.payment.utils.CurrencyJsonSerializer;


@RequiredIf(field="type", value="TYPE1", requiredField="details")
@RequiredIf(field="type", value="TYPE3", requiredField="bicCode")
@RequiredIf(field="type", value="TYPE1", requiredField="currency", requiredValue="EUR")
@RequiredIf(field="type", value="TYPE2", requiredField="currency", requiredValue="USD")
public class PaymentDto {
    
	@JsonView(View.PaymentResponse.class)
	private Long id;

	@JsonView(View.PaymentResponse.class)
	@NotNull(message= "type field is required")
	private PaymentType type;
	
	@JsonView(View.PaymentResponse.class)
	@NotNull(message= "currency field is required")
	private PaymentCurrency currency;
	
	@JsonView(View.PaymentResponse.class)
	@NotNull(message= "amount field is required")
	@DecimalMin(value = "0.01", inclusive = true, message ="amount should be at least 0.01")
	@JsonSerialize(using=CurrencyJsonSerializer.class)
	private BigDecimal amount;
	
	@JsonView(View.PaymentResponse.class)
	@NotNull(message= "debtor_iban field is required")
	@JsonProperty("debtor_iban")
    private String debtorIban;
	
	@JsonView(View.PaymentResponse.class)
	@NotNull(message= "creditor_iban field is required")
	@JsonProperty("creditor_iban")
    private String creditorIban;
	
	@JsonView(View.PaymentResponse.class)
	private String details;
	
	@JsonView(View.PaymentResponse.class)
	@JsonProperty("bic_code")
	@Size(min = 8, max = 11,  message ="BIC code should be between 8 and 11 characters long")
	private String bicCode;
	
	@JsonView(View.PaymentResponse.class)
	private PaymentStatus status;
	
	@JsonView(View.PaymentResponse.class)
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime transactionDate;
	
	@JsonView(View.PaymentResponseCancellationFee.class)
	@JsonSerialize(using=CurrencyJsonSerializer.class)
	private BigDecimal cancellationFee;
	
	public BigDecimal getCancellationFee() {
		return cancellationFee;
	}

	public void setCancellationFee(BigDecimal cancellationFee) {
		this.cancellationFee = cancellationFee;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getStatus() {
		return status.toString();
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public String getDebtorIban() {
		return debtorIban;
	}

	public void setDebtorIban(String debtorIban) {
		this.debtorIban = debtorIban;
	}

	public String getCreditorIban() {
		return creditorIban;
	}

	public void setCreditorIban(String creditorIban) {
		this.creditorIban = creditorIban;
	}

	public String getBicCode() {
		return bicCode;
	}

	public void setBicCode(String bicCode) {
		this.bicCode = bicCode;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public PaymentType getType() {
		return type;
	}
	
	public void setType(PaymentType type) {
		this.type = type;
	}
	
	public PaymentCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(PaymentCurrency currency) {
		this.currency = currency;
	}
	

}
