package br.com.fiap.orderparkingbr.service.impl;

import br.com.fiap.orderparkingbr.enums.PaymentType;
import br.com.fiap.orderparkingbr.model.Payment;
import br.com.fiap.orderparkingbr.repository.PaymentRepository;
import br.com.fiap.orderparkingbr.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    private static final BigDecimal valueByMinute = BigDecimal.valueOf(0.5);

    public Payment createPayment(Integer time, PaymentType paymentType) {
        Payment payment = new Payment();
        payment.setPaymentType(paymentType.name());
        payment.setTime(time);
        payment.setAmount(valueByMinute.multiply(BigDecimal.valueOf(time)));
        return paymentRepository.save(payment);
    }

}
