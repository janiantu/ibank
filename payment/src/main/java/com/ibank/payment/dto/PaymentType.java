package com.ibank.payment.dto;

public enum PaymentType {

	TYPE1("TYPE1"), TYPE2("TYPE2"), TYPE3("TYPE3");

	private String value;

	private PaymentType(String value) {
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
