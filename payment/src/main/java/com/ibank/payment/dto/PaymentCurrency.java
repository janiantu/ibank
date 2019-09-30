package com.ibank.payment.dto;

public enum PaymentCurrency {
	
	EUR("EUR"), USD("USD");

	private String value;

	private PaymentCurrency(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
    @Override
    public String toString() {
      return String.valueOf(value);
    }

}
