package com.ibank.payment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PAYMENTS")
public class Payment {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;

		@Column(nullable = false)
	    private String type;

		@Column(nullable = false)
	    private String currency;
		
		@Column(nullable = false, precision=10, scale=2)
	    private Double amount;
		
		@Column(nullable = false)
	    private String debtorIban;
		
		@Column(nullable = false)
	    private String creditorIban;
		
		@Column(nullable = true)
		private String details;
		
		@Column(nullable = true)
		private String bicCode;
		
		@Column(nullable = false)
		private String status;
		
		@Column(nullable = false)
	    private LocalDateTime transactionDate;
		
		@Column(nullable = true)
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
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
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

		public String getDetails() {
			return details;
		}

		public void setDetails(String details) {
			this.details = details;
		}

		public Double getAmount() {
			return amount;
		}

		public void setAmount(Double amount) {
			this.amount = amount;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
	    
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		
	    public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}
	 


}
