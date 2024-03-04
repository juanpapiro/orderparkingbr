package br.com.fiap.orderparkingbr.repository;

import br.com.fiap.orderparkingbr.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
}
