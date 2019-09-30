package com.ibank.payment.handlers;

public class PaymentNotFoundException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3355580334691870939L;

	public PaymentNotFoundException(Long id) {
        super("Payment id not found : " + id);
    }

}
