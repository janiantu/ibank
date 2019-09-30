package com.ibank.payment.dto;

public enum PaymentStatus {
    IN_PROGRESS("In progress"),
    
    DONE("Done"),
    
    CANCELLED("Cancelled");

    private String value;

    private PaymentStatus(String value) {
      this.value = value;
    }
    
	public String getValue() {
		return value;
	}
	
    @Override
    public String toString() {
      return String.valueOf(value);
    }
    
    public static PaymentStatus fromValue(String text) {
        for (PaymentStatus b : PaymentStatus.values()) {
          if (String.valueOf(b.value).equals(text)) {
            return b;
          }
        }
        return null;
     }

	
}
