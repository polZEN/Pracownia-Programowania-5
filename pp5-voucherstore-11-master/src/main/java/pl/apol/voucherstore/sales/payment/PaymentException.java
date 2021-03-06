package pl.apol.voucherstore.sales.payment;

import pl.apol.payu.exceptions.PayUException;

public class PaymentException extends IllegalStateException {
    public PaymentException(PayUException e) {
        super(e);
    }
}
