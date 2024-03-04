package br.com.fiap.orderparkingbr.service;

import br.com.fiap.orderparkingbr.enums.PaymentType;
import br.com.fiap.orderparkingbr.model.Payment;

public interface PaymentService {

    Payment createPayment(Integer time, PaymentType paymentType);

}
